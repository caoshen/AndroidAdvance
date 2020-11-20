package com.example.androidadvance.track.hook;

import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author caoshen
 * @date 2020/10/9
 */
public class HookView {
    public Method mHookMethod;

    public Field mHookField;

    public HookView(View view) {
        try {
            Class<?> viewClass = Class.forName("android.view.View");
            mHookMethod = viewClass.getDeclaredMethod("getListenerInfo");
            mHookMethod.setAccessible(true);

            Class<?> listenerInfoClass = Class.forName("android.view.View$ListenerInfo");
            mHookField = listenerInfoClass.getDeclaredField("mOnClickListener");
            mHookField.setAccessible(true);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

}
