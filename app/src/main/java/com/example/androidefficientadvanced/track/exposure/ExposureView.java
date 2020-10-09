package com.example.androidefficientadvanced.track.exposure;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import java.lang.ref.WeakReference;

/**
 * @author caoshen
 * @date 2020/10/9
 */
public class ExposureView extends View {

    public static final int INITIAL = 0;

    public static final int SEEN = 1;

    public static final int UNSEEN = 2;

    public View view;

    public int lastState;

    public long beginTime;

    public long endTime;

    public Object tag;

    private Handler mHandler = new Handler(Looper.getMainLooper());

    private boolean mActive = false;

    private boolean mDying = false;

    private WeakReference<View> mViewRoot;

    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            if (!mActive) {
                // 如果进程关闭，移除 runnable
                mHandler.removeCallbacks(this);
                return;
            }

            // 判断当前页是否关闭，是否需要清除 runnable
            final View viewRoot = mViewRoot.get();
            if (null == viewRoot || mDying) {
                cleanUp();
                return;
            }

            // 寻找目标控件
//            pathFinder.findTargetView(viewRoot, viewVisitorMap, dataTrackSet);
            // 移出已有的 runnable
            mHandler.removeCallbacks(this);
            // 新建定时器
            mHandler.postDelayed(this, 500);
        }
    };

    private void cleanUp() {

    }

    public ExposureView(Context context) {
        super(context);
        mViewRoot = new WeakReference<>(getRootView());
    }

    public boolean isSatisfyTimeRequired() {
        return false;
    }
}
