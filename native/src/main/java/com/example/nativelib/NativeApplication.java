package com.example.nativelib;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.bytedance.android.bytehook.ByteHook;

public class NativeApplication extends Application {
    private static final String TAG = "NativeApplication";

    @Override
    public void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        int ret = ByteHook.init(new ByteHook.ConfigBuilder()
                .setMode(ByteHook.Mode.AUTOMATIC)
                .setDebug(true)
                .build());
        Log.i(TAG, "bytehook init, return: " + ret);

        System.loadLibrary("hacker");
    }
}
