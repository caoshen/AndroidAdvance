package io.github.caoshen.baselib.network.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;

import androidx.annotation.NonNull;

import java.util.Arrays;

public class NetworkObserver implements INetworkObserver {
    private final ConnectivityManager mConnectivityManager;
    private final Listener mListener;
    private ConnectivityManager.NetworkCallback mNetworkCallback = new ConnectivityManager.NetworkCallback() {
        @Override
        public void onAvailable(@NonNull Network network) {
            onConnectivityChange(network, true);
        }

        @Override
        public void onLost(@NonNull Network network) {
            onConnectivityChange(network, false);
        }
    };

    public NetworkObserver(Context context, INetworkObserver.Listener listener) {
        mConnectivityManager = context.getSystemService(ConnectivityManager.class);
        mListener = listener;
    }

    public void init() {
        NetworkRequest request = new NetworkRequest.Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .build();
        mConnectivityManager.registerNetworkCallback(request, mNetworkCallback);
    }

    @Override
    public boolean isOnline() {
        return Arrays.stream(mConnectivityManager.getAllNetworks()).anyMatch(this::isOnline);
    }

    @Override
    public void shutdown() {
        mConnectivityManager.unregisterNetworkCallback(mNetworkCallback);
    }

    private boolean isOnline(Network network) {
        NetworkCapabilities capabilities = mConnectivityManager.getNetworkCapabilities(network);
        return capabilities != null && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET);
    }

    private void onConnectivityChange(Network network, boolean isOnline) {
        boolean isAnyOnline = Arrays.stream(mConnectivityManager.getAllNetworks()).anyMatch(it -> {
            if (it.equals(network)) {
                return isOnline;
            } else {
                return isOnline(network);
            }
        });
        if (mListener != null) {
            mListener.onConnectivityChange(isAnyOnline);
        }
    }
}
