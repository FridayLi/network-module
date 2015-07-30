package com.datayes.network;

/**
 * Created by quandong.li on 2015/7/30.
 */
public interface ResponseListener<T> {

    void onSuccess(T data);

    void onError(Exception exception, int errorCode);
}
