package com.example.androidadvance.chap1;

import android.content.Context;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author caoshen
 * @date 2020/8/25
 */
public class LayoutInflaterHook {
    private static final String TAG = "LayoutInflaterHook";

    public static void hookLayoutInflater() throws Exception {
        Class<?> serviceFetcher = Class.forName("android.app.SystemServiceRegistry$ServiceFetcher");
        // 获取 ServiceFetcher 的实例 serviceFetcherImpl
        Object serviceFetcherImpl = Proxy.newProxyInstance(LayoutInflaterHook.class.getClassLoader(),
                new Class<?>[]{serviceFetcher},
                new ServiceFetcherHandler());

        // 获取 SystemServiceRegistry 的 registerService 方法
        Class<?> systemServiceRegistry = Class.forName("android.app.SystemServiceRegistry");

        // 无法反射调用 registerService，registerService 在 Android 10 版本以上都是 blacklist 级别的 api，
        // 反射调用会被系统拒绝，抛出 NoSuchMethodException。
        Method registerService = systemServiceRegistry.getDeclaredMethod("registerService",
                String.class,
                CustomLayoutInflater.class.getClass(),
                serviceFetcher);
        registerService.setAccessible(true);

        // 调用 registerService 方法，将自定义的 CustomLayoutInflater 设置到 SystemServiceRegistry
        registerService.invoke(systemServiceRegistry,
                Context.LAYOUT_INFLATER_SERVICE, CustomLayoutInflater.class, serviceFetcherImpl);

        // 测试
        Field systemServiceFetchers = systemServiceRegistry.getDeclaredField("SYSTEM_SERVICE_FETCHERS");
        systemServiceFetchers.setAccessible(true);
        Map systemServiceFetchersField = (Map) systemServiceFetchers.get(null);
        Set set = systemServiceFetchersField.keySet();
        Object service = systemServiceFetchersField.get(Context.LAYOUT_INFLATER_SERVICE);
        Log.w(TAG, "find layout inflater:" + service);
        for (Object next : set) {
            Object value = systemServiceFetchersField.get(next);
            Log.d(TAG, "key:" + next);
            Log.d(TAG,"value:" + value);
            if (Context.LAYOUT_INFLATER_SERVICE.equals(next)) {
                Log.e(TAG, "find Service for layout inflater:" + value);
            }
        }
    }

}
