package io.github.caoshen.baselib.network.utils;

public interface INetworkObserver {
    boolean isOnline();

    void shutdown();

    interface Listener {
        void onConnectivityChange(boolean isOnline);
    }
}
