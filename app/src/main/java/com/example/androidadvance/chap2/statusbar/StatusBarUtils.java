package com.example.androidadvance.chap2.statusbar;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * @author caoshen
 * @date 2020/9/4
 */
public class StatusBarUtils {
    /**
     * set status bar translucent
     * Android 4.4
     *
     * @param activity activity
     */
    public static void setStatusBarTranslucentV19(Activity activity) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        winParams.flags |= bits;
        win.setAttributes(winParams);
    }

    /**
     * set status bar color
     * Android 5.0
     *
     * @param activity activity
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void setStatusBarColorV21(Activity activity, int color) {
        Window window = activity.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        int vis = window.getDecorView().getSystemUiVisibility();
        vis |= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
        vis |= View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        window.getDecorView().setSystemUiVisibility(vis);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(color);
    }

    /**
     * set status bar light or dark mode
     * Android 6.0
     *
     * @param activity activity
     */
    @TargetApi(Build.VERSION_CODES.M)
    public static void setStatusBarColorV23(Activity activity) {
        View decorView = activity.getWindow().getDecorView();
        int vis = decorView.getSystemUiVisibility();
        vis |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        decorView.setSystemUiVisibility(vis);
        vis &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        decorView.setSystemUiVisibility(vis);
    }

}
