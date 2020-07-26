package com.tvr.rxjavapractice.features.model.response;

import com.tvr.rxjavapractice.common.RequestCompleteListeners;

import java.util.List;

public interface GetRepoModel {
    void getRepo(String userName, RequestCompleteListeners<List<GithubRepo>> repoRequestCompleteListeners);
}
