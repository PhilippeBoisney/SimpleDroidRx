package com.cookminute.simpledroidrx.Fragments;

import android.os.Bundle;
import android.support.percent.PercentRelativeLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cookminute.simpledroidrx.API.GithubMemberObserverHelper;
import com.cookminute.simpledroidrx.R;
import com.cookminute.simpledroidrx.Utils.Rx.RxUtils;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import fr.castorflex.android.smoothprogressbar.SmoothProgressBar;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Philippe on 21/07/16.
 */
public class Android_REST_Sample_Fragment extends Fragment {

    @BindView(R.id.root_view) PercentRelativeLayout rootView;
    @BindView(R.id.progress) SmoothProgressBar progress;
    @BindView(R.id.txtView) TextView txtView;


    public Android_REST_Sample_Fragment() { }

    private Unbinder unbinder;
    private Subscription mSubscription;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        RxUtils.unsubscribeIfNotNull(mSubscription);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_android_rest_sample, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    //--------------------------------------------------
    // ACTIONS
    //--------------------------------------------------

    @OnClick(R.id.btn_start)
    public void onClickStart(final View v){
        this.startAnimation();
        this.myStream();
    }

    @OnClick(R.id.btn_reset)
    public void onClickReset(final View v){
        txtView.setText("Mattt followers: 0\nMojombo followers: 0\nJakeWharton followers: 0");
    }

    //--------------------------------------------------
    // Stream that handle user's flow
    //--------------------------------------------------

    private void myStream(){

        mSubscription = getObservable()

                .flatMap(GithubMemberObserverHelper.observeEachItem())
                .flatMap(GithubMemberObserverHelper.getGithubMember())
                .map(GithubMemberObserverHelper.getNumberOfFollowers())
                .reduce(GithubMemberObserverHelper.aggregateString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());
    }

    //--------------------------------------------------
    // Observable & Observer
    //--------------------------------------------------

    Observable<List<String>> getObservable() {
        return Observable.just(Arrays.asList("mojombo", "JakeWharton", "mattt"));
    }

    private Subscriber<String> getObserver(){
        return new Subscriber<String>() {
            @Override
            public void onNext(String s) { updateDesign(s);  }

            @Override
            public void onCompleted() { stopAnimation(); }

            @Override
            public void onError(Throwable e) {Log.e("TAG", "Error called : "+e.getMessage()); }
        };
    }

    // -------------------------------------------------------------
    // Private funcs that handle design updates
    // -------------------------------------------------------------

    private void startAnimation(){
        progress.setVisibility(View.VISIBLE);
    }

    private void stopAnimation(){
        progress.setVisibility(View.INVISIBLE);
        Log.e("TAG", "onComplete called !");
    }

    private void updateDesign(String s){
        Log.e("TAG", "onNext called !"+s);
        txtView.setText(s);
    }

}
