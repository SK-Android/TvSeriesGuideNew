package com.androidapp.mcs.tvseriesguide.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class MyNetworkHelper {
    public static boolean hasNetworkStatus(Context context) {

        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);       //To check connectivity use a class named Connectivity manager
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();                                                       // Its part of the package android.net. It returns an object
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting();                                   //Cast it to return connectivity manager
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
