package com.example.androidefficientadvanced.track.proxylisenter;

import android.view.View;

/**
 * @author caoshen
 * @date 2020/10/9
 */
public abstract class ProxyListener implements View.OnClickListener {

    @Override
    public void onClick(View v) {
        // doOnClick 为业务方控件点击事件的逻辑实现
        doOnClick(v);
        sendLog(v);
    }

    private void sendLog(View view) {
        // detail of sendLog(), based on Thread
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // do send log
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    protected abstract void doOnClick(View v);
}
