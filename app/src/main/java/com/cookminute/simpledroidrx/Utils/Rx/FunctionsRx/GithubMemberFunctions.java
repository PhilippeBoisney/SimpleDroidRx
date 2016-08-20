package com.cookminute.simpledroidrx.Utils.Rx.FunctionsRx;

import android.util.Log;

import com.cookminute.simpledroidrx.Utils.API.ApiManagerService;
import com.cookminute.simpledroidrx.Utils.API.Model.GitHubMember;
import com.cookminute.simpledroidrx.Utils.Rx.ObservablesRx.GithubMemberObservables;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * Created by Philippe on 20/08/16.
 */
public class GithubMemberFunctions {

    private GithubMemberObservables githubMemberObservables;

    public GithubMemberFunctions(GithubMemberObservables githubMemberObservables){
        this.githubMemberObservables = githubMemberObservables;
    }

    /**
     * Create and return an observable for each item on a list of string
     * @return
     */
    public Func1<List<String>, Observable<String>> observeEachItem(){
        return new Func1<List<String>, Observable<String>>() {
            @Override
            public Observable<String> call(List<String> item) {
                return Observable.from(item);
            }
        };
    }

    /**
     * Call and execute an observable on result of http request that get Github member infos
     * @return
     */
    public Func1<String, Observable<GitHubMember>> getGithubMember(){
        return new Func1<String, Observable<GitHubMember>>() {
            @Override
            public Observable<GitHubMember> call(String memberName) {
                return githubMemberObservables.createObservableOnHttpRequestGetMember(memberName);
            }
        };
    }

    /**
     * Function that logs each item on a console
     * @return
     */
    public Action1<GitHubMember> logOnConsoleEachItem(){
        return new Action1<GitHubMember>() {
            @Override
            public void call(GitHubMember s) {
                Log.e("TAG", "Github member: "+s.toString());
            }
        };
    }

    /**
     * Function that get number of followers from a GitHubMember
     * @return
     */
    public Func1<GitHubMember, String> getNumberOfFollowers(){
        return new Func1<GitHubMember, String>() {
            @Override
            public String call(GitHubMember member) {
                return member.toString();
            }
        };
    }

    /**
     * Function that aggregate multiple string together
     * @return
     */
    public Func2<String, String, String> aggregateString(){
        return new Func2<String, String, String>() {
            @Override
            public String call(String s, String s2) {
                return s + s2;
            }
        };
    }

}
