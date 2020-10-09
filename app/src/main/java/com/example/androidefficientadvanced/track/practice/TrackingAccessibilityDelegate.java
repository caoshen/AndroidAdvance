package com.example.androidefficientadvanced.track.practice;

import android.os.SystemClock;
import android.view.View;

import com.example.androidefficientadvanced.track.exposure.ExposureView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author caoshen
 * @date 2020/10/9
 */
public class TrackingAccessibilityDelegate extends View.AccessibilityDelegate {

    private final View.AccessibilityDelegate mRealDelegate;

    private int mEventType;

    private String mEventName;
    private List currentViews = new ArrayList<Object>();

    public TrackingAccessibilityDelegate(View.AccessibilityDelegate delegate) {
        mRealDelegate = delegate;
    }

    @Override
    public void sendAccessibilityEvent(View host, int eventType) {
        try {
            if (eventType == mEventType) {
                fireEvent(host);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (mRealDelegate != null) {
                mRealDelegate.sendAccessibilityEvent(host, eventType);
            }
        }
    }

    private void fireEvent(View host) {

    }

    /**
     * 返回当前 AccessibilityDelegate 是否为可视化埋点的 delegate 对象
     *
     * @param eventName event name
     * @return is delegate
     */
    public boolean willFireEvent(final String eventName) {
        if (getEventName().equals(eventName)) {
            return true;
        } else if (mRealDelegate instanceof TrackingAccessibilityDelegate) {
            return ((TrackingAccessibilityDelegate) mRealDelegate).willFireEvent(eventName);
        } else {
            return false;
        }
    }

    public int getEventType() {
        return mEventType;
    }

    public void setEventType(int eventType) {
        mEventType = eventType;
    }

    public String getEventName() {
        return mEventName;
    }

    public void setEventName(String eventName) {
        mEventName = eventName;
    }

    private void checkViewState(ExposureView exposureView, boolean status) {
        boolean needExposureProcess = isSatisfySize(exposureView.view);
        if (needExposureProcess) {
            switch (exposureView.lastState) {
                case ExposureView.INITIAL: {
                    // 初始状态需要处理，view 的状态初始化
                    exposureView.lastState = ExposureView.SEEN;
                    exposureView.beginTime = System.currentTimeMillis();
                    break;
                }
                case ExposureView.SEEN: {
                    // 当前控件依然可见，仅更新可见态控件当前的结束时间
                    exposureView.endTime = System.currentTimeMillis();
                    break;
                }
                case ExposureView.UNSEEN: {
                    // 不可见态，符合曝光条件，则初始化处理
                    exposureView.lastState = ExposureView.SEEN;
                    exposureView.beginTime = System.currentTimeMillis();
                    break;
                }
                default: {
                    break;
                }
            }
        } else {
            switch (exposureView.lastState) {
                case ExposureView.INITIAL: {
                    break;
                }
                case ExposureView.SEEN: {
                    // 可见态，不符合界面曝光规则计算，则证明由可见态变为不可见，需要提交曝光数据
                    exposureView.lastState = ExposureView.UNSEEN;
                    exposureView.endTime = System.currentTimeMillis();
                    break;
                }
                case ExposureView.UNSEEN: {
                    break;
                }
                default: {
                    break;
                }
            }
        }

        if (exposureView.isSatisfyTimeRequired()) {
            if (status) {
                // 页面切换，提交满足曝光条件的控件
                addToCommit(exposureView);
                currentViews.remove(exposureView.tag);
                return;
            }
            if (exposureView.lastState == ExposureView.SEEN) {
                return;
            } else if (exposureView.lastState == ExposureView.UNSEEN) {
                // 状态变为不可见
                addToCommit(exposureView);
                currentViews.remove(exposureView.tag);
            }
        } else if (exposureView.lastState == ExposureView.UNSEEN) {
            currentViews.remove(exposureView.tag);
        }
    }

    private void addToCommit(ExposureView exposureView) {

    }

    private boolean isSatisfySize(View view) {
        return false;
    }
}
