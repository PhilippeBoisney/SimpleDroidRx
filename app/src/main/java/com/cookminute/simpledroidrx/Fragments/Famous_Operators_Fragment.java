package com.cookminute.simpledroidrx.Fragments;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.percent.PercentRelativeLayout;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.cookminute.simpledroidrx.R;
import com.cookminute.simpledroidrx.Utils.Helpers.StringObserverHelper;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by Philippe on 20/07/16.
 */
public class Famous_Operators_Fragment extends Fragment {

    public Famous_Operators_Fragment() { }

    @BindView(R.id.root_view) PercentRelativeLayout rootView;
    @BindView(R.id.txtViewHelloWorld) TextView txtview;
    @BindView(R.id.txtViewSeekBar) TextView txtviewSeekBar;
    @BindView(R.id.seek_bar) SeekBar seekBar;
    @BindView(R.id.txtViewHelp) TextView txtViewHelp;


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
        View rootView = inflater.inflate(R.layout.fragment_famous_operators, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        this.configureSeekBar();
        txtViewHelp.setMovementMethod(new ScrollingMovementMethod());
        return rootView;
    }

    //--------------------------------------------------
    // ACTIONS
    //--------------------------------------------------

    @OnClick(R.id.btn_start)
    public void onClickPlayHappy(final View v){
        this.myStream(true);
    }

    @OnClick(R.id.btn_stop)
    public void onClickPlayUnhappy(final View v){
        this.myStream(false);
    }

    private void configureSeekBar(){
        seekBar.setMax(7);
        seekBar.setOnSeekBarChangeListener(
            new SeekBar.OnSeekBarChangeListener() {

              @Override
              public void onStopTrackingTouch(SeekBar seekBar) { }

              @Override
              public void onStartTrackingTouch(SeekBar seekBar) { }

              @Override
              public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                  txtviewSeekBar.setText("Result max: "+seekBar.getProgress());
                  myStream(true);
              }
            });
    }

    //--------------------------------------------------
    // Stream that handle user's flow
    //--------------------------------------------------

    private void myStream(Boolean isHappy){
        txtview.setText("");
        getObservable()
                .flatMap(StringObserverHelper.observeEachItem())
                .flatMap(StringObserverHelper.setSmileyToItem(isHappy))
                .flatMap(StringObserverHelper.setCarriotReturnToItem())
                .filter(StringObserverHelper.filterVersionAndroidThatSucks())
                .take(seekBar.getProgress())
                .doOnNext(StringObserverHelper.logOnConsoleEachItem())
                .subscribe(getObserver());
    }

    //--------------------------------------------------
    // Observer & Observable definition
    //--------------------------------------------------

    Observable<List<String>> getObservable() {
        return Observable.just(Arrays.asList("Cupcake","Froyo", "Gingerbread", "Honeycomb", "Donut","Ice Cream Sandwich", "Jelly Bean", "KitKat", "Lollipop"));
    }

    private Subscriber<String> getObserver(){
        return new Subscriber<String>() {
            @Override
            public void onNext(String s) { txtview.setText(txtview.getText().toString()+s); }

            @Override
            public void onCompleted() { Snackbar.make(rootView, "Stream completed successfully !", Snackbar.LENGTH_LONG).show(); }

            @Override
            public void onError(Throwable e) { }
        };
    }
}
