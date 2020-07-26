package com.tvr.rxjavapractice.network;

import com.tvr.rxjavapractice.features.model.response.GithubRepo;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubApi {
    @GET("users/{userName}/starred")
    Observable<List<GithubRepo>> getStarredRepo(@Path("userName") String userName);
}
