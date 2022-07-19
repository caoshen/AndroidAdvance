package com.example.androidadvance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.androidadvance.track.hook.HookView;
import com.example.androidadvance.track.hook.ProxyManager;
import com.example.androidadvance.track.proxylisenter.ProxyListener;

import io.github.caoshen.androidadvance.jetpack.ISimpleService;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private ISimpleService mBinder;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e(TAG, "onServiceConnected: " + name + ", service=" + service);
            mBinder = ISimpleService.Stub.asInterface(service);

            try {
                mBinder.asBinder().linkToDeath(mDeathRecipient, 0);
            } catch (RemoteException e) {
                Log.e(TAG, "linkToDeath: " + e);
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(TAG, "onServiceDisconnected: " + name);
        }
    };

    private IBinder.DeathRecipient mDeathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            Log.e(TAG, "binderDied: ");
            mBinder.asBinder().unlinkToDeath(mDeathRecipient, 0);

            // 重新绑定
            Log.e(TAG, "bindService restarted");
            bindService();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bindService();
//                Toast.makeText(MainActivity.this, "this is a button! "
//                        + v.getTag().toString(), Toast.LENGTH_LONG).show();
            }
        });

        // use proxy listener
//        button.setOnClickListener(new ProxyListener() {
//            @Override
//            protected void doOnClick(View v) {
//                Toast.makeText(MainActivity.this, "use proxy listener! "
//                        + v.getTag().toString(), Toast.LENGTH_LONG).show();
//            }
//        });

//        hookViews(findViewById(android.R.id.content));
    }

    private void bindService() {
        Intent intent = new Intent();
        intent.setPackage("io.github.caoshen.androidadvance.jetpack");
        intent.setClassName("io.github.caoshen.androidadvance.jetpack",
                "io.github.caoshen.androidadvance.jetpack.service.SimpleService");
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    public void hookViews(View view) {
        try {
            if (view.getVisibility() == View.VISIBLE) {
                if (view instanceof ViewGroup) {
                    int count = ((ViewGroup) view).getChildCount();
                    for (int i = 0; i < count; ++i) {
                        View child = ((ViewGroup) view).getChildAt(i);
                        hookViews(child);
                    }
                } else {
                    if (view.isClickable()) {
                        HookView hookView = new HookView(view);
                        Object listenInfo = hookView.mHookMethod.invoke(view);
                        Object originalListener = hookView.mHookField.get(listenInfo);
                        ProxyManager.ProxyListener listener = new ProxyManager.ProxyListener(
                                (View.OnClickListener) originalListener);
                        hookView.mHookField.set(listenInfo, listener);
                    }
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}