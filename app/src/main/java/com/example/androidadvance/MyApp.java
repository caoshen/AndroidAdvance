package com.example.androidadvance;

import android.app.Application;

import com.example.androidadvance.chap1.LayoutInflaterHook;

/**
 * @author caoshen
 * @date 2020/8/25
 */
public class MyApp extends Application {
    private static MyApp INSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        try {
            LayoutInflaterHook.hookLayoutInflater();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static MyApp getInstance() {
        return INSTANCE;
    }
}
