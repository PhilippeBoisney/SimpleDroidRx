package com.cookminute.simpledroidrx.DependencyInjection.Modules;

import com.cookminute.simpledroidrx.Utils.API.ApiManagerService;
import com.cookminute.simpledroidrx.Utils.Rx.FunctionsRx.GithubMemberFunctions;
import com.cookminute.simpledroidrx.Utils.Rx.ObservablesRx.GithubMemberObservables;
import com.cookminute.simpledroidrx.Utils.Rx.Streams.Streams.StreamGithubMember;
import com.cookminute.simpledroidrx.Utils.Rx.Streams.StreamsManager;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Philippe on 20/08/16.
 */
@Module
public class StreamsModule {

    // ------  Schedulers RX ------
    @Provides
    @Named("SubscribeOn")
    public Scheduler provideSchedulerSubscribeOn() {
        return Schedulers.io();
    }

    @Provides
    @Named("ObserveOn")
    public Scheduler provideSchedulerObserveOn() {
        return AndroidSchedulers.mainThread();
    }

    // ------  Observables RX ------

    @Provides
    public GithubMemberObservables provideGithubMemberObservable(ApiManagerService apiManager) { return new GithubMemberObservables(apiManager); }


    // ------  Functions RX ------

    @Provides
    public GithubMemberFunctions provideGithubMemberFunctions(GithubMemberObservables observables) { return new GithubMemberFunctions(observables); }


    // ------  Streams ------

    @Provides
    StreamGithubMember provideGithubMemberStreams(GithubMemberObservables githubMemberObservables, @Named("SubscribeOn") Scheduler schedulerSubscribeOn, @Named("ObserveOn") Scheduler schedulerObserveOn, GithubMemberFunctions githubMemberFunctions) {
        return new StreamGithubMember(githubMemberObservables, schedulerSubscribeOn, schedulerObserveOn, githubMemberFunctions);
    }

    // ------  Manager ------

    @Provides //Method that expose available return types
    @Singleton
    StreamsManager provideStreamsManager(StreamGithubMember streamGithubMember) {
        return new StreamsManager(streamGithubMember);
    }
}
