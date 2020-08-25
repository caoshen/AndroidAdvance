package com.example.androidefficientadvanced.chap1;

import android.content.Context;
import android.view.LayoutInflater;

/**
 * @author caoshen
 * @date 2020/8/18
 */
public class CustomLayoutInflater extends LayoutInflater {
    protected CustomLayoutInflater(Context context) {
        super(context);
    }

    public CustomLayoutInflater(LayoutInflater original, Context newContext) {
        super(original, newContext);
    }

    @Override
    public LayoutInflater cloneInContext(Context newContext) {
        return new CustomLayoutInflater(this, newContext);
    }

}
