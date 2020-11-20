package com.example.androidadvance.track.hook;

import android.view.View;

/**
 * @author caoshen
 * @date 2020/10/9
 */
public class ProxyManager {

    public static void sendLog(View view) {

    }

    public static class ProxyListener implements View.OnClickListener {

        View.OnClickListener mOriginalListener;

        public ProxyListener(View.OnClickListener listener) {
            mOriginalListener = listener;
        }

        @Override
        public void onClick(View v) {
            // send log
            sendLog(v);
            if (mOriginalListener != null) {
                mOriginalListener.onClick(v);
            }
        }
    }
}
