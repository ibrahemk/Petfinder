package com.example.petfinder.Helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by mio on 12/31/2014.
 */
   public class CheckInternetConnection {


    private final Context applicationContext;
    ConnectivityManager conManager;

    public CheckInternetConnection(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    public boolean haveNetworkConnection() {
        boolean wifiAvailable = false;
        boolean mobileAvailable = false;
        if (applicationContext!=null){
            conManager = (ConnectivityManager) applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        }
        if (conManager!=null ) {
            NetworkInfo[] networkInfo = conManager.getAllNetworkInfo();
            for (NetworkInfo netInfo : networkInfo) {
                if (netInfo.getTypeName().equalsIgnoreCase("WIFI"))
                    if (netInfo.isConnected())
                        wifiAvailable = true;
                if (netInfo.getTypeName().equalsIgnoreCase("MOBILE"))
                    if (netInfo.isConnected())
                        mobileAvailable = true;
            }
        }

        //  Log.e("Check_wifi_mobile", wifiAvailable + "======" + mobileAvailable);

        return wifiAvailable || mobileAvailable;
    }

}
