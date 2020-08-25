package com.example.androidefficientadvanced;

import android.app.Application;

import com.example.androidefficientadvanced.chap1.LayoutInflaterHook;

/**
 * @author caoshen
 * @date 2020/8/25
 */
public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        try {
            LayoutInflaterHook.hookLayoutInflater();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
