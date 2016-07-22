package com.cookminute.simpledroidrx.Fragments;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.percent.PercentRelativeLayout;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cookminute.simpledroidrx.R;
import com.cookminute.simpledroidrx.Utils.Helpers.StringObserverHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by Philippe on 21/07/16.
 */
public class Error_Handling_Fragment extends Fragment {

    @BindView(R.id.root_view) PercentRelativeLayout rootView;
    @BindView(R.id.txtViewPolite) TextView txtviewPolite;
    @BindView(R.id.txtViewRude) TextView txtviewRude;
    @BindView(R.id.txtViewHelp) TextView txtViewHelp;

    public Error_Handling_Fragment() { }

    private Unbinder unbinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_error_handling, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        txtViewHelp.setMovementMethod(new ScrollingMovementMethod());
        return rootView;
    }


    //--------------------------------------------------
    // Actions
    //--------------------------------------------------

    @OnClick(R.id.btn_start)
    public void onClickButtonPolite(final View v){
        this.myStreamThatHandleError(txtviewPolite.getText().toString());
    }

    @OnClick(R.id.btn_stop)
    public void onClickButtonRude(final View v){
        this.myStreamThatHandleError(txtviewRude.getText().toString());
    }

    //--------------------------------------------------
    // My Stream
    //--------------------------------------------------

    public void myStreamThatHandleError(String txtViewText){

        getObservable(txtViewText)
                .map(StringObserverHelper.testMyWords())
                .subscribe(getObserver());
    }

    //--------------------------------------------------
    // Observer & Observable definition
    //--------------------------------------------------

    Observable<String> getObservable(final String message) {
        return Observable.just(message);
    }

    private Subscriber<String> getObserver(){
        return new Subscriber<String>() {
            @Override
            public void onNext(String s) { Log.e("TAG","on Next"); }

            @Override
            public void onCompleted() { Snackbar.make(rootView, "This is a very polite TextView !", Snackbar.LENGTH_LONG).show(); }

            @Override
            public void onError(Throwable e) { Snackbar.make(rootView, "Oh ! This TextView is so rude...", Snackbar.LENGTH_LONG).show();}
        };
    }
}
