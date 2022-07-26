package io.github.caoshen.androidadvance.keepalive;

import android.util.Log;

public class NativeLeoric {
    private static final String TAG = "NativeLeoric";

    static {
        try {
            System.loadLibrary("leoric");
        } catch (Exception e) {
            Log.e(TAG, "exception:" + e);
        }
    }


}
