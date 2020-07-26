package com.tvr.rxjavapractice.features.model.response;

import android.content.Context;
import android.util.Log;

import com.tvr.rxjavapractice.common.RequestCompleteListeners;
import com.tvr.rxjavapractice.network.GitHubApi;
import com.tvr.rxjavapractice.network.RetrofitClient;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class GetRepoModelImplementation implements GetRepoModel {
    //Subscription subscription;
    GitHubApi gitHubApi;
    Context context;

    public GetRepoModelImplementation(Context context) {
        this.context = context;
    }
    Disposable d;

    @Override
    public void getRepo(String userName, final RequestCompleteListeners<List<GithubRepo>> repoRequestCompleteListeners) {
        gitHubApi = RetrofitClient.getRetrofitClient().create(GitHubApi.class);
                gitHubApi
               .getStarredRepo(userName)
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new Observer<List<GithubRepo>>() {
                   @Override
                   public void onSubscribe(Disposable d) {

                   }

                   @Override
                   public void onNext(List<GithubRepo> githubRepos) {
                       repoRequestCompleteListeners.RequestSuccess(githubRepos);
                   }

                   @Override
                   public void onError(Throwable e) {
                       repoRequestCompleteListeners.RequestFailed(e.getMessage());
                   }

                   @Override
                   public void onComplete() {
                       repoRequestCompleteListeners.RequestFailed("complete");
                      // repoRequestCompleteListeners.RequestFailed("complete");
                   }
               });
    }
}
