package com.cookminute.simpledroidrx.Fragments;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.percent.PercentRelativeLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.cantrowitz.rxbroadcast.RxBroadcast;
import com.cookminute.simpledroidrx.R;
import com.cookminute.simpledroidrx.Utils.Functions;
import com.jakewharton.rxbinding.view.RxView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by Philippe on 21/07/16.
 */
public class Android_Simple_Sample_Fragment extends Fragment {

    @BindView(R.id.root_view) PercentRelativeLayout rootView;
    @BindView(R.id.txtView) TextView txtView;
    @BindView(R.id.btn_start) Button btn;

    private IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);


    public Android_Simple_Sample_Fragment() { }

    private Unbinder unbinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getObservable().subscribe(getObserver());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_android_simple_sample, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        this.handleClickWithObservable();
        return rootView;
    }

    private void handleClickWithObservable(){
        RxView.clicks(btn)
                .subscribe(getActionForButton(rootView));
    }

    //--------------------------------------------------
    // Observer & Observable definition
    //--------------------------------------------------

    private Observable<Intent> getObservable() {
        return RxBroadcast.fromBroadcast(getActivity(), filter);
    }

    private Subscriber<Intent> getObserver(){
        return new Subscriber<Intent>() {
            @Override
            public void onNext(Intent intent) { Functions.getInternetConnexion(intent, getActivity(), rootView); }

            @Override
            public void onCompleted() {  }

            @Override
            public void onError(Throwable e) { }
        };
    }

    public static Action1<Void> getActionForButton(final PercentRelativeLayout rootView){
        return new Action1<Void>() {
            @Override
            public void call(Void s) {
                Snackbar.make(rootView, "A snackbar showed with an observer on button !", Snackbar.LENGTH_LONG).show();
            }
        };
    }
}
