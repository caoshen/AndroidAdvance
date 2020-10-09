package com.example.androidefficientadvanced.track.accessibilitydelegate;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityEvent;

/**
 * @author caoshen
 * @date 2020/10/9
 */
public class ClickDelegate extends View.AccessibilityDelegate {

    public ClickDelegate() {
    }

    public ClickDelegate(final View rootView) {
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                setDelegate(rootView);
            }
        });
    }

    public void setDelegate(View rootView) {
        if (rootView instanceof ViewGroup) {
            int count = ((ViewGroup) rootView).getChildCount();
            for (int i = 0; i < count; i++) {
                View child = ((ViewGroup) rootView).getChildAt(i);
                setDelegate(child);
            }
        } else {
            if (rootView.isClickable()) {
                // 设置无障碍代理
                rootView.setAccessibilityDelegate(this);
            }
        }
    }

    @Override
    public void sendAccessibilityEvent(View host, int eventType) {
        super.sendAccessibilityEvent(host, eventType);
        if (AccessibilityEvent.TYPE_VIEW_CLICKED == eventType) {
            sendLog();
        }
    }

    public void sendLog() {
    }


}
