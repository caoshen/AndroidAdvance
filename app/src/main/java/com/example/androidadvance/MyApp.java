package com.example.androidadvance;

import android.app.Application;
import android.util.Log;

import com.example.androidadvance.chap1.LayoutInflaterHook;
import com.example.androidadvance.utils.BaseLibrary;

/**
 * @author caoshen
 * @date 2020/8/25
 */
public class MyApp extends Application {
    private static final String TAG = "MyApp";
    private static MyApp INSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
        BaseLibrary.INSTANCE.init(this);
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
