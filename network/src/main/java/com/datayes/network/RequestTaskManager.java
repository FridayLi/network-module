package com.datayes.network;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class RequestTaskManager {
    private static RequestTaskManager instance = new RequestTaskManager();
    private final AtomicBoolean mIsTokenRefreshing = new AtomicBoolean();

    private ArrayList<BaseRequest<?>> mPendingRequestList = new ArrayList<BaseRequest<?>>();

    public static RequestTaskManager getInstance() {
        return instance;
    }

    public boolean isTokenRefreshing() {
        return mIsTokenRefreshing.get();
    }

    public void setTokenRefreshing(boolean isTokenRefreshing) {
        mIsTokenRefreshing.set(isTokenRefreshing);
    }

    public synchronized void addPendingRequest(BaseRequest<?> request) {
        mPendingRequestList.add(request);
    }

    public synchronized ArrayList<BaseRequest<?>> getPendingRequestList() {
        return mPendingRequestList;
    }

    public synchronized void clearPendingRequestList() {
        mPendingRequestList.clear();
    }
}
