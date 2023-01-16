package com.example.nativelib;

public class NativeHacker {

    public static void hook(int type) {
        nativeHook(type);
    }

    public static void unhook() {
        nativeUnhook();
    }

    public static void dumpRecords(String path) {
        nativeDumpRecords(path);
    }

    private static native void nativeDumpRecords(String pathName);

    private static native int nativeUnhook();

    private static native int nativeHook(int type);
}
