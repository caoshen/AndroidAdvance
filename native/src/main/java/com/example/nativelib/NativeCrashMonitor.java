package com.example.nativelib;

public class NativeCrashMonitor {

    private static volatile boolean isInit = false;

    public void init(CrashHandlerListener callback) {
        if (isInit) {
            return;
        }
        isInit = true;
    }

    public native void initNative(CrashHandlerListener calback);


}
