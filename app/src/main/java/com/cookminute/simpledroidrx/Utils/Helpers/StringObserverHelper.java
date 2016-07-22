package com.cookminute.simpledroidrx.Utils.Helpers;

import android.util.Log;

import java.io.IOException;
import java.util.List;

import rx.Observable;
import rx.exceptions.Exceptions;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Philippe on 20/07/16.
 */
public class StringObserverHelper {

    //--------------------------------------------------
    // Functions used by Hello World Fragment
    //--------------------------------------------------

    public static Func1<String, String> removeStringOfBadPattern(){
        return new Func1<String, String> () {
            @Override
            public String call(String s) {
                return s.replace("Massive View Controller","MVVM");
            }
        };
    }

    public static Func1<String, String> removeStringOfBadCallBack(){
        return new Func1<String, String> () {
            @Override
            public String call(String s) {
                return s.replace("Hell Callback","ReactiveX");
            }
        };
    }

    public static Func1<String, String> removeStringAsyncTask(){
        return new Func1<String, String> () {
            @Override
            public String call(String s) {
                return s.replace("AsyncTask","RxJava");
            }
        };
    }

    public static Func1<String, String> addABunchOfLove(){
        return new Func1<String, String> () {
            @Override
            public String call(String s) {
                return s+" <3 <3 <3";
            }
        };
    }

    //--------------------------------------------------
    // Functions used by Hello Operator Fragment
    //--------------------------------------------------

    /**
     * This method, that uses Observable.from(), allows to take a collection of items and emits each them one at a time
     * @return
     */
    public static Func1<List<String>, Observable<String>> observeEachItem(){
        return new Func1<List<String>, Observable<String>>() {
            @Override
            public Observable<String> call(List<String> item) {
                return Observable.from(item);
            }
        };
    }

    public static Func1<String, Observable<String>> setCarriotReturnToItem(){
        return new Func1<String, Observable<String>>() {
            @Override
            public Observable<String> call(String name) {
                return addTextCarriotReturn(name);
            }
        };
    }

    public static Func1<String, Observable<String>> setSmileyToItem(final Boolean isHappy){
        return new Func1<String, Observable<String>>() {
            @Override
            public Observable<String> call(String name) {
                return addSmileyToItem(isHappy, name);
            }
        };
    }

    public static Func1<String, Boolean> filterVersionAndroidThatSucks(){
        return new Func1<String, Boolean>() {
            @Override
            public Boolean call(String item) {
                return !(item.contains("Cupcake") || item.contains("Donut"));
            }
        };
    }

    public static Action1<String> logOnConsoleEachItem(){
        return new Action1<String>() {
            @Override
            public void call(String s) {
                Log.e("TAG", "Item '"+s+"'was handled !");
            }
        };
    }


    //--------------------------------------------------
    // Functions used by Hello Error Fragment
    //--------------------------------------------------

    public static Func1<String, String> testMyWords(){
        return new Func1<String, String> () {
            @Override
            public String call(String s) {
                try {
                    isContainsBadWord(s);
                    return s;
                } catch (Throwable t) {
                    throw Exceptions.propagate(t);
                }
            }
        };
    }

    //--------------------------------------------------
    // Private functions used internally
    //--------------------------------------------------

    private static void isContainsBadWord(String s) throws Exception{
        if (s.contains("callback") && s.contains("hell")){
            throw new IOException(); //Because too much bad words
        }
    }


    private static Observable<String> addTextCarriotReturn(String text) {
        return Observable.just(text+"\n");
    }

    private static Observable<String> addSmileyToItem(Boolean isHappy, String text) {
        if (isHappy){
            return Observable.just(text+" :) ");
        } else {
            return Observable.just(text+" :( ");
        }
    }
}
