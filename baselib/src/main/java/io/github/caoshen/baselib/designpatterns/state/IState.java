package io.github.caoshen.baselib.designpatterns.state;

import android.os.Message;

public interface IState {

    /**
     * Returned by processMessage to indicate the message was processed.
     */
    boolean HANDLED = true;

    /**
     * Returned by processMessage to indicate the message was NOT processed.
     */
    boolean NOT_HANDLED = false;

    /**
     * Called when a state is entered.
     */
    void enter();

    /**
     * Called when a state is exited.
     */
    void exit();

    /**
     * Called when a message is to be processed by the state machine.
     *
     * @param msg to process
     * @return HANDLED if processing has completed and NOT_HANDLED if the message wasn't processed.
     */
    boolean processMessage(Message msg);

    String getName();
}
