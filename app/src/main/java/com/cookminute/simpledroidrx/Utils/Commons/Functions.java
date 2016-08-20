package com.cookminute.simpledroidrx.Utils.Commons;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import java.util.Random;

import rx.Scheduler;

/**
 * Created by Philippe on 19/07/16.
 */
public class Functions {

    public static Boolean normalRunningOperation() {
        simulateBigTraitement(4000L);
        return false;
    }

    public static Boolean longRunningOperation(Boolean isCombinedTask) {
        simulateBigTraitement(8000);
        return isCombinedTask;
    }

    private static void simulateBigTraitement(long time){
        long now = System.currentTimeMillis();
        long now2 = System.currentTimeMillis();
        while(now < (now2+time)){
            now = System.currentTimeMillis();
        }
    }

    public static void getInternetConnexion(Intent intent, Context context, View rootView){

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) { // connected to the internet
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                // connected to wifi
                Log.e("TAG", "Wi-Fi is connected !");
                Snackbar.make(rootView, "Wi-Fi is connected !", Snackbar.LENGTH_LONG).show();
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                // connected to the mobile provider's data plan
                Log.e("TAG", "Mobile is connected !");
                Snackbar.make(rootView, "Mobile is connected !", Snackbar.LENGTH_LONG).show();
            }
        } else {
            // not connected to the internet
            Log.e("TAG", "You're not connected to internet !");
            Snackbar.make(rootView, "You're not connected to internet !", Snackbar.LENGTH_LONG).show();
        }
    }
}
