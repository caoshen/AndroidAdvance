package com.example.androidadvance.view;

import android.content.Context;
import android.os.Build;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

/**
 * https://blog.csdn.net/caoshen2014/article/details/99774749
 *
 * 1. 绘制流程
 * 2. 触摸反馈与事件分发
 * 3. 自定义View
 * 4. postSyncBarrier
 * 5. Choreographer
 */
public class AView extends View {

    public AView(Context context) {
        super(context);
    }

    public AView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public AView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private int mNextBarrierToken;

    private Message mMessages;

    /**
     * 设置同步屏障
     *
     * 本质：向消息队列插入一个没有target的消息形成屏障
     * 正常的消息都有target，target是发送消息的handler
     *
     * @param when 设置时间
     * @return token
     */
    private int postSyncBarrier(long when) {
        // Enqueue a new sync barrier token.
        // We don't need to wake the queue because the purpose of a barrier is to stall it.
        synchronized (this) {
            final int token = mNextBarrierToken++;
            final Message msg = Message.obtain();
//            msg.markInUse();
//            msg.when = when;
            msg.arg1 = token;

            Message prev = null;
            Message p = mMessages;
//            if (when != 0) {
////                while (p != null && p.when <= when) {
//                    prev = p;
////                    p = p.next;
//                }
//            }
//            if (prev != null) { // invariant: p == prev.next
//                msg.next = p;
//                prev.next = msg;
//            } else {
//                msg.next = p;
//                mMessages = msg;
//            }
            return token;
        }
    }
}
