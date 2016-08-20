package com.cookminute.simpledroidrx.Utils.Rx.Streams.Streams;

import com.cookminute.simpledroidrx.Utils.API.Model.GitHubMember;
import com.cookminute.simpledroidrx.Utils.Rx.FunctionsRx.GithubMemberFunctions;
import com.cookminute.simpledroidrx.Utils.Rx.ObservablesRx.GithubMemberObservables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Philippe on 20/08/16.
 */
public class StreamGithubMember {

    private GithubMemberObservables githubMemberObservables;
    private GithubMemberFunctions githubMemberFunctions;
    private Scheduler schedulerSubscribeOn; //Schedulers.io();
    private Scheduler schedulerObserveOn; //AndroidSchedulers.mainThread()

    public StreamGithubMember(GithubMemberObservables githubMemberObservables, Scheduler schedulerSubscribeOn, Scheduler schedulerObserveOn, GithubMemberFunctions githubMemberFunctions){
        this.githubMemberObservables = githubMemberObservables;
        this.schedulerSubscribeOn = schedulerSubscribeOn;
        this.schedulerObserveOn = schedulerObserveOn;
        this.githubMemberFunctions = githubMemberFunctions;
    }


    /**
     * Stream that get Github info for each member of list of user
     * @param listOfUserName
     * @return
     */
    public Observable<String> streamGetGithubInfoForEachMember(List<String> listOfUserName) {
        return Observable.just(listOfUserName) //1 - Create an observable for the list of String
                .flatMap(githubMemberFunctions.observeEachItem()) // 2 - Create an observable for each item of list
                .flatMap(githubMemberFunctions.getGithubMember()) // 3 - For each item, execute an http request that will get Github infos
                .map(githubMemberFunctions.getNumberOfFollowers()) // 4 - Get number of follower of GithubMember
                .reduce(githubMemberFunctions.aggregateString()) // 5 - Aggregate result
                .subscribeOn(schedulerSubscribeOn)
                .observeOn(schedulerObserveOn);
    }
}
