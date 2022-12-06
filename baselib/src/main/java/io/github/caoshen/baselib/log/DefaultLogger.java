package io.github.caoshen.baselib.log;

import android.util.Log;

public class DefaultLogger extends Logger {

    private final String mCategory;

    public DefaultLogger(String category) {
        mCategory = category;
    }

    @Override
    public boolean isDebugEnabled() {
        return true;
    }

    @Override
    public void debug(String message) {
        Log.d(mCategory, message);
    }

    @Override
    public void debug(String message, Throwable t) {
        Log.d(mCategory, message, t);
    }

    @Override
    public void warn(String message, Throwable t) {
        Log.w(mCategory, message, t);
    }
}
