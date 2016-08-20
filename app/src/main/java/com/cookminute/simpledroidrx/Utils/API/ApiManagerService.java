package com.cookminute.simpledroidrx.Utils.API;

import com.cookminute.simpledroidrx.Utils.API.Model.GitHubMember;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Philippe on 20/08/16.
 */
public interface ApiManagerService {
    @GET("/users/{username}")
    Call<GitHubMember> getMember(@Path("username") String username);
}
