package com.example.yumplanner.utiles.connectivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NetworkChangeListener extends BroadcastReceiver {
    private NetworkStatusListener listener;



    public void setNetworkStatusListener(NetworkStatusListener listener) {
        this.listener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Common.isConnectedToInternet(context)) {
            if (listener != null) {
                listener.onNetworkConnected();
            }
        } else {
            if (listener != null) {
                listener.onNetworkDisconnected();
            }
        }
    }


}
