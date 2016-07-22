package com.cookminute.simpledroidrx.Utils.Rx;

import rx.Subscription;

/**
 * Created by Philippe on 20/07/16.
 */
public class RxUtils {

    public static void unsubscribeIfNotNull(Subscription subscription) {
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }
}
