package com.example.androidefficientadvanced;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.androidefficientadvanced.track.hook.HookView;
import com.example.androidefficientadvanced.track.hook.ProxyManager;
import com.example.androidefficientadvanced.track.proxylisenter.ProxyListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "this is a button! "
                        + v.getTag().toString(), Toast.LENGTH_LONG).show();
            }
        });

        // use proxy listener
        button.setOnClickListener(new ProxyListener() {
            @Override
            protected void doOnClick(View v) {
                Toast.makeText(MainActivity.this, "use proxy listener! "
                        + v.getTag().toString(), Toast.LENGTH_LONG).show();
            }
        });

        hookViews(findViewById(android.R.id.content));
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