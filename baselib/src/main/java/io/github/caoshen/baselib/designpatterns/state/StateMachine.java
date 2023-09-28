package io.github.caoshen.baselib.designpatterns.state;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * https://cs.android.com/android/platform/superproject/main/+/main:frameworks/libs/modules-utils/java/com/android/internal/util/StateMachine.java;drc=b14b5b8d12bf7f7a6dbf25cb275e247440450fce;l=1220
 */
public class StateMachine {
    private String mName;

    /**
     * Message.what value when quitting
     */
    private static final int SM_QUIT_CMD = -1;

    /**
     * Message.what value when initializing
     */
    private static final int SM_INIT_CMD = -2;

    /**
     * Convenience constant that maybe returned by processMessage
     * to indicate the message was processed and is not to be
     * processed by parent states
     */
    public static final boolean HANDLED = true;

    /**
     * Convenience constant that maybe returned by processMessage
     * to indicate the message was NOT processed and is to be
     * processed by parent states
     */
    public static final boolean NOT_HANDLED = false;

    private static class SmHandler extends Handler {

        private boolean mHasQuit = false;

        private boolean mDbg = false;

        /**
         * The SmHandler object, identifies that message is internal
         */
        private static final Object mSmHandlerObj = new Object();

        private Message mMsg;

        private boolean mIsConstructionCompleted;

        private StateInfo[] mStateStack;

        private int mStateStackTopIndex = -1;

        private StateInfo[] mTempStateStack;

        private int mTempStateStackCount;

        private final HaltingState mHaltingState = new HaltingState();

        private final QuittingState mQuittingState = new QuittingState();

        private StateMachine mSm;

        private final HashMap<State, StateInfo> mStateInfo = new HashMap<>();

        private State mInitialState;

        private State mDestState;

        /**
         * Indicates if a transition is in process.
         */
        private boolean mTransitionInProcess = false;

        private final ArrayList<Message> mDeferredMessages = new ArrayList<>();

        private class HaltingState extends State {
            @Override
            public boolean processMessage(Message msg) {
                return super.processMessage(msg);
            }
        }

        private static class QuittingState extends State {
            @Override
            public boolean processMessage(Message msg) {
                return NOT_HANDLED;
            }
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            if (mHasQuit) {
                return;
            }
            if (mSm != null && msg.what != SM_INIT_CMD && msg.what != SM_QUIT_CMD) {
                mSm.onPreHandleMessage(msg);
            }
        }

        /**
         * Information about a state.
         * Used to maintain the hierarchy.
         */
        private static class StateInfo {
            final State state;

            final StateInfo parentStateInfo;

            boolean active = false;

            StateInfo(final State state, final StateInfo parent) {
                this.state = state;
                this.parentStateInfo = parent;
            }

            @Override
            public String toString() {
                return "StateInfo{" +
                        "state=" + state.getName() +
                        ", parentStateInfo=" + (parentStateInfo == null ? "null" : parentStateInfo.state.getName()) +
                        ", active=" + active +
                        '}';
            }
        }

        private SmHandler(Looper looper, StateMachine sm) {
            super(looper);
            mSm = sm;

            addState(mHaltingState, null);
            addState(mQuittingState, null);
        }

        private final StateInfo addState(State state, State parent) {
            StateInfo parentStateInfo = null;
            if (parent != null) {
                parentStateInfo = mStateInfo.get(parent);
                if (parentStateInfo == null) {
                    // Recursively add our parent as it's not been added yet.
                    parentStateInfo = addState(parent, null);
                }
            }

            StateInfo stateInfo = mStateInfo.get(state);
            if (stateInfo == null) {
                stateInfo = new StateInfo(state, parentStateInfo);
                mStateInfo.put(state, stateInfo);
            }

            if ((stateInfo.parentStateInfo != null) && (stateInfo.parentStateInfo != parentStateInfo)) {
                throw new RuntimeException("state already added.");
            }
            return stateInfo;
        }
    }

    private SmHandler mSmHandler;
    private HandlerThread mSmThread;

    private void initStateMachine(String name, Looper looper) {
        mName = name;
        mSmHandler = new SmHandler(looper, this);
    }

    protected StateMachine(String name) {
        mSmThread = new HandlerThread(name);
        mSmThread.start();
        Looper looper = mSmThread.getLooper();
        initStateMachine(name, looper);
    }

    protected StateMachine(String name, Looper looper) {
        initStateMachine(name, looper);
    }

    protected void onPreHandleMessage(Message msg) {

    }

    protected void onPostHandleMessage(Message msg) {

    }

    public final void addState(State state, State parent) {
        mSmHandler.addState(state, parent);
    }
}
