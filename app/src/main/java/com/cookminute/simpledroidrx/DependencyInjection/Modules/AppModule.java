package com.cookminute.simpledroidrx.DependencyInjection.Modules;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Philippe on 20/08/16.
 */
@Module
public class AppModule {
    Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides //Method that expose available return types
    @Singleton
    Application providesApplication() {
        return mApplication;
    }
}
