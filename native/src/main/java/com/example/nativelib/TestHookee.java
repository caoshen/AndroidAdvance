package com.example.nativelib;

public class TestHookee {

    public static void testMalloc() {
        nativeTestMalloc();
    }

    private static native void nativeTestMalloc();
}
