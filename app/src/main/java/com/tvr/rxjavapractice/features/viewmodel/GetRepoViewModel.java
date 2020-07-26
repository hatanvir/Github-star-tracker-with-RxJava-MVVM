package com.tvr.rxjavapractice.features.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tvr.rxjavapractice.common.RequestCompleteListeners;
import com.tvr.rxjavapractice.features.model.response.GetRepoModel;
import com.tvr.rxjavapractice.features.model.response.GithubRepo;

import java.util.List;

public class GetRepoViewModel extends ViewModel {
    public  MutableLiveData<List<GithubRepo>> getReposuccessLiveData = new MutableLiveData();
    public  MutableLiveData<String> getRepoFailedLiveData = new MutableLiveData();
    public  MutableLiveData<Boolean> isEmptyListLiveData = new MutableLiveData();

    public void getRepo(String userName, GetRepoModel model){
        model.getRepo(userName, new RequestCompleteListeners<List<GithubRepo>>() {
            @Override
            public void RequestSuccess(List<GithubRepo> data) {
                if (data.isEmpty()){
                    isEmptyListLiveData.postValue(true);
                    data.clear();
                    getReposuccessLiveData.postValue(data);
                }else {
                    isEmptyListLiveData.postValue(false);
                    getReposuccessLiveData.postValue(data);
                }
            }

            @Override
            public void RequestFailed(String errorMessage) {
                getRepoFailedLiveData.postValue(errorMessage);
            }
        });
    }
}
