package com.cookminute.simpledroidrx.API;

import android.util.Log;

import com.cookminute.simpledroidrx.API.Model.GitHubMember;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * Created by Philippe on 21/07/16.
 */
public class GithubMemberObserverHelper {

    public static Func1<List<String>, Observable<String>> observeEachItem(){
        return new Func1<List<String>, Observable<String>>() {
            @Override
            public Observable<String> call(List<String> item) {
                return Observable.from(item);
            }
        };
    }

    public static Func1<String, Observable<GitHubMember>> getGithubMember(){
        return new Func1<String, Observable<GitHubMember>>() {
            @Override
            public Observable<GitHubMember> call(String memberName) {
                return ApiManager.getGitHubMember(memberName);
            }
        };
    }

    public static Action1<GitHubMember> logOnConsoleEachItem(){
        return new Action1<GitHubMember>() {
            @Override
            public void call(GitHubMember s) {
                Log.e("TAG", "Github member: "+s.toString());
            }
        };
    }

    public static Func1<GitHubMember, String> getNumberOfFollowers(){
        return new Func1<GitHubMember, String>() {
            @Override
            public String call(GitHubMember member) {
                return member.toString();
            }
        };
    }

    public static Func2<String, String, String> aggregateString(){
        return new Func2<String, String, String>() {
            @Override
            public String call(String s, String s2) {
                return s + s2;
            }
        };
    }
}
