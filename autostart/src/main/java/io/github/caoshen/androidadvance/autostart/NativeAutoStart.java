package io.github.caoshen.androidadvance.autostart;

import android.util.Log;

public class NativeAutoStart {
    private static final String TAG = "NativeAutoStart";

    static {
        try {
            System.loadLibrary("autostart");
        } catch (Exception e) {
            Log.e(TAG, "load lib failed:" + e);
        }
    }

    public native void doDaemon(String indicatorSelfPath, String indicatorDaemonPath, String observerSelfPath,
                                String observerDaemonPath);

    public void onDaemonDead() {
        IAutoStartProcess.Fetcher.fetch().onDaemonDead();
    }
}
