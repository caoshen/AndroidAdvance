package com.example.androidefficientadvanced.chap1;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author caoshen
 * @date 2020/8/18
 */
public class CustomLayoutInflater extends LayoutInflater {

    private static final String[] sClassPrefixList = {
            "android.widget.",
            "android.webkit."
    };

    private static int VIEW_TAG = 0x10000000;

    public CustomLayoutInflater(Context context) {
        super(context);
    }

    public CustomLayoutInflater(LayoutInflater original, Context newContext) {
        super(original, newContext);
    }

    @Override
    protected View onCreateView(String name, AttributeSet attrs) throws ClassNotFoundException {
        for (String prefix : sClassPrefixList) {
            try {
                View view = createView(name, prefix, attrs);
                if (view != null) {
                    return view;
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InflateException e) {
                e.printStackTrace();
            }
        }
        return super.onCreateView(name, attrs);
    }

    @Override
    public LayoutInflater cloneInContext(Context newContext) {
        return new CustomLayoutInflater(this, newContext);
    }

    @Override
    public View inflate(int resource, @Nullable ViewGroup root, boolean attachToRoot) {
        View viewGroup = super.inflate(resource, root, attachToRoot);
        View rootView = viewGroup;
        View tempView = viewGroup;
        // 向上遍历得到根 View
        while (tempView != null) {
            rootView = viewGroup;
            tempView = ((ViewGroup) tempView.getParent());
        }
        traversalViewGroup(rootView);
        return viewGroup;
    }

    private void traversalViewGroup(View rootView) {
        if (rootView == null || !(rootView instanceof ViewGroup)) {
            return;
        }
        // 如果 rootView 没有 tag，设置它的 view 值为 VIEW_TAG 计数值
        if (rootView.getTag() == null) {
            rootView.setTag(getViewTag());
        }
        ViewGroup viewGroup = (ViewGroup) rootView;
        int childCount = ((ViewGroup) rootView).getChildCount();
        for (int i = 0; i < childCount; ++i) {
            View childView = viewGroup.getChildAt(i);
            if (childView.getTag() == null) {
                childView.setTag(combineTag(getViewTag(), rootView.getTag().toString()));
            }
            Log.e("Hooker", "childView name=" + childView.getClass().getName()
                    + ", id = " + childView.getTag().toString());
            if (childView instanceof ViewGroup) {
                // 深度优先遍历
                traversalViewGroup(childView);
            }
        }
    }

    private static String combineTag(String tag1, String tag2) {
        return getMd5(getMd5(tag1) + getMd5(tag2));
    }

    private static String getViewTag() {
        return String.valueOf(VIEW_TAG++);
    }

    public static String getMd5(String str) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(str.getBytes());
            return new BigInteger(1, md5.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "null";
    }
}
