package com.cookminute.simpledroidrx.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
 * Created by Philippe on 20/07/16.
 */
public class Hello_World_Fragment extends Fragment {

    public Hello_World_Fragment() { }

    @BindView(R.id.btn_start) Button btnStart;
    @BindView(R.id.btn_stop) Button btnStop;
    @BindView(R.id.txtViewHelloWorld) TextView txtview;

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
        View rootView = inflater.inflate(R.layout.fragment_hello_world, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    //---------------------------

    @OnClick(R.id.btn_start)
    public void onClickStart(final View v){

        btnStop.setEnabled(false);

        getMyFirstObservable()
                .map(StringObserverHelper.removeStringOfBadPattern())
                .map(StringObserverHelper.removeStringOfBadCallBack())
                .map(StringObserverHelper.removeStringAsyncTask())
                .map(StringObserverHelper.addABunchOfLove())
                .subscribe(getMyFirstObserver());

    }

    @OnClick(R.id.btn_stop)
    public void onClickStop(final View v){
        txtview.setText("I love Massive View Controller, Hell Callback and AsyncTask !");
        btnStop.setEnabled(false);
    }

    //--------------------------------------------------
    // Observer & Observable definition
    //--------------------------------------------------

    /**
     * This observer emits what is on textView, then completes
     * @return
     */
    private Observable<String> getMyFirstObservable(){
        return Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> sub) {
                        sub.onNext(txtview.getText().toString());
                        sub.onCompleted();
                    }
                }
        );
    }

    /**
     * This observer consume the data
     * Print each String emitted by the observable
     * @return
     */
    private Subscriber<String> getMyFirstObserver(){
        return new Subscriber<String>() {
            @Override
            public void onNext(String s) { txtview.setText(s); }

            @Override
            public void onCompleted() { btnStop.setEnabled(true); }

            @Override
            public void onError(Throwable e) { }
        };
    }
}
