package com.tvr.rxjavapractice.common;

public interface RequestCompleteListeners<T> {
    void RequestSuccess(T data);
    void RequestFailed(String errorMessage);
}
