package com.example.androidadvance.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

/**
 * https://blog.csdn.net/caoshen2014/article/details/99774909
 *
 * 1. 生命周期
 * 2. onStartCommand 返回值
 * 3. 停止Service
 * 4. ServiceConnection 所在线程
 * 5. onCreate 运行在哪个线程
 */
public class AService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
