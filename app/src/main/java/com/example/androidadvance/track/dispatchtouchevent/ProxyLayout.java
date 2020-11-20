package com.example.androidadvance.track.dispatchtouchevent;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.example.androidadvance.track.accessibilitydelegate.ClickDelegate;

/**
 * @author caoshen
 * @date 2020/10/9
 */
public class ProxyLayout extends FrameLayout {
    public ProxyLayout(@NonNull Context context) {
        super(context);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            View rootView = getRootView();
            ClickDelegate clickDelegate = new ClickDelegate();
            clickDelegate.setDelegate(rootView);
        }
        return super.dispatchTouchEvent(ev);
    }
}
