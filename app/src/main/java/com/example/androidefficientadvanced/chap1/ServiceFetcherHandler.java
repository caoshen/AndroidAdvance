package com.example.androidefficientadvanced.chap1;

import android.content.Context;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author caoshen
 * @date 2020/8/18
 */
public class ServiceFetcherHandler implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 当调用 ServiceFetcherImpl 的 getService 的时候，会返回自定义的 LayoutInflater
        if ("toString".equals(method.getName())) {
            return "ServiceFetcherHandler";
        }
        return new CustomLayoutInflater((Context) args[0]);
    }
}
