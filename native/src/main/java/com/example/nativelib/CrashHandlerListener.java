package com.example.nativelib;

public interface CrashHandlerListener {
    void onCrash(String threadName, Error error);
}
