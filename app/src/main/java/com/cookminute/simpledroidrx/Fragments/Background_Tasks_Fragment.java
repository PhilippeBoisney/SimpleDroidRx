package com.cookminute.simpledroidrx.Fragments;

import android.os.Bundle;
import android.support.percent.PercentRelativeLayout;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cookminute.simpledroidrx.R;
import com.cookminute.simpledroidrx.Utils.Functions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import fr.castorflex.android.smoothprogressbar.SmoothProgressBar;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;

/**
 * Created by Philippe on 20/07/16.
 */
public class Background_Tasks_Fragment extends Fragment {

    @BindView(R.id.root_view) PercentRelativeLayout rootView;
    @BindView(R.id.progressNormal)  SmoothProgressBar progressNormal;
    @BindView(R.id.progressLong)  SmoothProgressBar progressLong;
    @BindView(R.id.txtView) TextView title;
    @BindView(R.id.txtViewCounter) TextView txtViewCounter;
    @BindView(R.id.txtStateLong) TextView txtViewStateLong;
    @BindView(R.id.txtStateNormal) TextView txtViewStateNormal;
    @BindView(R.id.txtViewHelp) TextView txtViewHelp;

    private Unbinder unbinder;

    private Subscription subscription;

    private BehaviorSubject<Integer> counterRunningTasks = BehaviorSubject.<Integer>create(0);
    private int counterLongRunningTask = 0;
    private int counterNormalRunningTask = 0;

    public Background_Tasks_Fragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Subscribe our counter of tasks to its observer
        subscription = counterRunningTasks
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(getObserverCounter());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_background_tasks, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        txtViewHelp.setMovementMethod(new ScrollingMovementMethod());
        return rootView;
    }


    // -------------------------------------------------------------
    // ACTIONS
    // -------------------------------------------------------------

    @OnClick(R.id.btn_start1)
    public void onClickStart1(final View v){
        this.streamNormalTask();
    }

    @OnClick(R.id.btn_start2)
    public void onClickStart2(final View v){
        this.streamLongTask();
    }

    //--------------------------------------------------
    // My Streams
    //--------------------------------------------------

    public void streamNormalTask(){

        this.startRunningNormalTask();

        subscription = getObservableOnNormalTask()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserverNormalTask());
    }

    public void streamLongTask(){

        this.startRunningLongTask();

        subscription = getObservableOnNormalTask()
                .startWith(getObservableOnLongTask(true))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserverLongTask());
    }


    // -------------------------------------------------------------
    // RXJava: Observable & Observer
    // -------------------------------------------------------------

    private Observable<Boolean> getObservableOnNormalTask() {

        return Observable.create(
                new Observable.OnSubscribe<Boolean>() {
                    @Override
                    public void call(Subscriber<? super Boolean> sub) {
                        sub.onNext(Functions.normalRunningOperation());
                        sub.onCompleted();
                    }
                }
        );
    }

    private Observable<Boolean> getObservableOnLongTask(final Boolean isCombinedTask) {
        return Observable.create(
                new Observable.OnSubscribe<Boolean>() {
                    @Override
                    public void call(Subscriber<? super Boolean> sub) {
                        sub.onNext(Functions.longRunningOperation(isCombinedTask));
                        sub.onCompleted();
                    }
                }
        );
    }

    //-------

    private Observer<Integer> getObserverCounter() {
        return new Observer<Integer>() {

            @Override
            public void onCompleted() { }

            @Override
            public void onError(Throwable e) { Log.e("TAG", "Error ! "+e.getMessage()); }

            @Override
            public void onNext(Integer value) { updateCounterDesign(value); }
        };
    }

    private Observer<Boolean> getObserverNormalTask() {
        return new Observer<Boolean>() {

            @Override
            public void onCompleted() { stopRunningNormalTask(); }

            @Override
            public void onError(Throwable e) { Log.e("TAG", "Error ! Man "+e.getMessage()); }

            @Override
            public void onNext(Boolean value) { }
        };
    }

    private Observer<Boolean> getObserverLongTask() {
        return new Observer<Boolean>() {

            @Override
            public void onCompleted() { }

            @Override
            public void onError(Throwable e) { Log.e("TAG", "Error ! Man "+e.getMessage()); }

            @Override
            public void onNext(Boolean haveToStartNormalTask) { updateLongTaskDesign(haveToStartNormalTask); }
        };
    }

    // -------------------------------------------------------------
    // Private funcs that handle design updates
    // -------------------------------------------------------------

    private void startRunningNormalTask(){
        Log.e("TAG", "Start normal task...");
        counterRunningTasks.onNext(counterRunningTasks.getValue()+1);
        counterNormalRunningTask++;
        if (progressNormal != null){
            progressNormal.setVisibility(View.VISIBLE);
        }
    }

    private void stopRunningNormalTask(){
        Log.e("TAG", "Stop normal task...");
        counterRunningTasks.onNext(counterRunningTasks.getValue()-1);
        counterNormalRunningTask--;
        if (counterNormalRunningTask == 0 ){
            if (progressNormal != null){
                progressNormal.setVisibility(View.GONE);
            }
        }
    }

    // ----------------

    private void startRunningLongTask(){
        Log.e("TAG", "Start long task...");
        counterRunningTasks.onNext(counterRunningTasks.getValue()+1);
        counterLongRunningTask++;
        if (progressLong != null){
            progressLong.setVisibility(View.VISIBLE);
        }
    }

    private void stopRunningLongTask(){
        Log.e("TAG", "Stop long task...");
        counterRunningTasks.onNext(counterRunningTasks.getValue()-1);
        counterLongRunningTask--;
        if (counterLongRunningTask == 0 ) {
            if (progressLong != null){
                progressLong.setVisibility(View.GONE);
            }
        }
    }

    // --------------

    private void updateLongTaskDesign(Boolean haveToStartNormalTask){
        if (haveToStartNormalTask){
            stopRunningLongTask();
            startRunningNormalTask();
        } else {
            stopRunningNormalTask();
        }
    }

    private void updateCounterDesign(Integer value){
        if (rootView != null){
            txtViewStateLong.setText("LONG TASK ("+counterLongRunningTask+")");
            txtViewStateNormal.setText("NORMAL TASK ("+counterNormalRunningTask+")");
            txtViewCounter.setText("Task(s) running: "+value.toString());
            if (value == 0){
                title.setText("Let's run the world ! Or at least tasks...");
            } else if (value == 1){
                title.setText("A task is running...");
            } else {
                title.setText(value+" tasks are running...");
            }
        }
    }
}
