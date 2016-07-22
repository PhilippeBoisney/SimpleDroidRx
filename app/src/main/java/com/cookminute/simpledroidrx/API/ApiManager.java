package com.cookminute.simpledroidrx.API;

import com.cookminute.simpledroidrx.API.Model.GitHubMember;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Philippe on 21/07/16.
 */
public class ApiManager {

    private interface ApiManagerService {
        @GET("/users/{username}")
        Call<GitHubMember> getMember(@Path("username") String username);
    }

    private static final Retrofit restAdapter = new Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static final ApiManagerService apiManager = restAdapter.create(ApiManagerService.class);

    public static Observable<GitHubMember> getGitHubMember(final String username) {
        return getObservable(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    //-------------------

    private static Observable<GitHubMember> getObservable(final String username) {
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
