package com.cookminute.simpledroidrx;

import android.app.Application;

import com.cookminute.simpledroidrx.DependencyInjection.ApplicationComponent;
import com.cookminute.simpledroidrx.DependencyInjection.DaggerApplicationComponent;
import com.cookminute.simpledroidrx.DependencyInjection.Modules.AppModule;
import com.cookminute.simpledroidrx.DependencyInjection.Modules.NetworkModule;
import com.cookminute.simpledroidrx.DependencyInjection.Modules.StreamsModule;
import com.cookminute.simpledroidrx.Utils.Commons.Constants;

/**
 * Created by Philippe on 20/08/16.
 */
public class SimpleDroidRxApp extends Application {

    protected ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initDagger();
    }

    public ApplicationComponent getApplicationComponent(){
        return this.mApplicationComponent;
    }

    protected void initDagger(){
        //Init Dependency Injection
        mApplicationComponent = DaggerApplicationComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule(Constants.baseUrlApi))
                .streamsModule(new StreamsModule())
                .build();
    }
}
