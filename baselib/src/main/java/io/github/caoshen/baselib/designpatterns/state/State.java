package io.github.caoshen.baselib.designpatterns.state;

import android.os.Message;

public class State implements IState {

    protected State() {
    }

    @Override
    public void enter() {

    }

    @Override
    public void exit() {

    }

    @Override
    public boolean processMessage(Message msg) {
        return false;
    }

    @Override
    public String getName() {
        String name = getClass().getName();
        int lastDollar = name.lastIndexOf('$');
        return name.substring(lastDollar + 1);
    }
}
