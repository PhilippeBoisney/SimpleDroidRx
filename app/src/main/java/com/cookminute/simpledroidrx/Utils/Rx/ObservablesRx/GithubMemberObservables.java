package com.cookminute.simpledroidrx.Utils.Rx.ObservablesRx;

import com.cookminute.simpledroidrx.Utils.API.ApiManagerService;
import com.cookminute.simpledroidrx.Utils.API.Model.GitHubMember;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Philippe on 20/08/16.
 */
public class GithubMemberObservables {

    private ApiManagerService apiManager;

    public GithubMemberObservables(ApiManagerService apiManager){
        this.apiManager = apiManager;
    }

    /**
     * Observable on GetMember http request
     * @param username
     * @return
     */
    public Observable<GitHubMember> createObservableOnHttpRequestGetMember(final String username) {
        return Observable.create(
                new Observable.OnSubscribe<GitHubMember>() {
                    @Override
                    public void call(Subscriber<? super GitHubMember> observer) {
                        try {
                            GitHubMember member = apiManager.getMember(username).execute().body();
                            observer.onNext(member);
                            observer.onCompleted();
                        } catch (Exception e) {
                            observer.onError(e);
                        }
                    }
                });
    }
}
