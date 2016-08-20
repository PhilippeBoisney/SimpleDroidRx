package com.cookminute.simpledroidrx.UI.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cookminute.simpledroidrx.R;
import com.cookminute.simpledroidrx.SimpleDroidRxApp;
import com.cookminute.simpledroidrx.Utils.Rx.RxUtils;
import com.cookminute.simpledroidrx.Utils.Rx.Streams.StreamsManager;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Subscription;

/**
 * Created by Philippe on 20/08/16.
 */
public abstract class BaseFragment extends Fragment {

    //Inject dependency (With dagger2) of a stream manager that will handle all app's streams.
    @Inject protected StreamsManager streamManager;

    private Unbinder unbinder;
    protected Subscription mSubscription;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.configureDependenciesInjection();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getFragmentLayout(), container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.configureViewsInjection(view);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        RxUtils.unsubscribeIfNotNull(mSubscription);
    }

    //----------------------------
    // CONFIGURATIONS
    //----------------------------

    private void configureDependenciesInjection(){
        ((SimpleDroidRxApp) getActivity().getApplication()).getApplicationComponent().inject(this); //Utils injection
    }

    private void configureViewsInjection(View view){
        ButterKnife.bind(this, view); //Views injection
    }

    protected abstract int getFragmentLayout();
}
