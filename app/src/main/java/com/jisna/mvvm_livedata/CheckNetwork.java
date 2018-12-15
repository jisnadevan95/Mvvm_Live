package com.jisna.mvvm_livedata;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Jisna on 12/12/2017.
 */

public class CheckNetwork {
    public static boolean isInternetconnected(Context ct)
    {
        boolean connected = false;
        //get the connectivity manager object to identify the network state.
        ConnectivityManager connectivityManager = (ConnectivityManager)ct.getSystemService(Context.CONNECTIVITY_SERVICE);
        //Check if the manager object is NULL, this check is required. to prevent crashes in few devices.
        if(connectivityManager != null)
        {
            //Check Mobile data or Wifi net is present
            //we are connected to a network
            connected = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                    connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;
            return connected;
        }
        else
        {
            return false;
        }
    }
}
