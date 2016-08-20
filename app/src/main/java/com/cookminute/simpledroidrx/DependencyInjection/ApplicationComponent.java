package com.cookminute.simpledroidrx.DependencyInjection;

import com.cookminute.simpledroidrx.DependencyInjection.Modules.AppModule;
import com.cookminute.simpledroidrx.DependencyInjection.Modules.NetworkModule;
import com.cookminute.simpledroidrx.DependencyInjection.Modules.StreamsModule;
import com.cookminute.simpledroidrx.UI.Fragments.BaseFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Philippe on 20/08/16.
 */
@Singleton
@Component(modules={NetworkModule.class, AppModule.class, StreamsModule.class})
public interface ApplicationComponent {
    void inject(BaseFragment baseFragment);
}
