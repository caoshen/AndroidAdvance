package com.example.androidadvance.internal.util;

import android.os.Message;

public interface IState {
    static final boolean HANDLED = true;

    static final boolean NOT_HANDLED = false;

    void enter();

    void exit();

    boolean processMessage(Message message);

    String getName();
}
