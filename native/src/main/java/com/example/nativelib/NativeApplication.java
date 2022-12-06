package com.example.nativelib;

import android.app.Application;

import com.bytedance.android.bytehook.ByteHook;

public class NativeApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ByteHook.init();
    }
}
