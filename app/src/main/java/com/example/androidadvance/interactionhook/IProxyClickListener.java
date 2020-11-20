package com.example.androidadvance.interactionhook;

import android.view.View;

/**
 * @author caoshen
 * @date 2020/10/10
 */
public interface IProxyClickListener {

    boolean onProxyClick(WrapClickListener wrap, View view);

    class WrapClickListener implements View.OnClickListener {

        IProxyClickListener mProxyListener;

        View.OnClickListener mBaseListener;

        public WrapClickListener(IProxyClickListener proxyListener, View.OnClickListener baseListener) {
            mProxyListener = proxyListener;
            mBaseListener = baseListener;
        }

        @Override
        public void onClick(View v) {
            boolean handled = false;
            if (mProxyListener != null) {
                handled = mProxyListener.onProxyClick(this, v);
            }
            if (!handled && mBaseListener != null) {
                mBaseListener.onClick(v);
            }
        }
    }
}
