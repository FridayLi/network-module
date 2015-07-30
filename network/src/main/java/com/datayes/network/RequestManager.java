package com.datayes.network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;

public abstract class RequestManager {
	
	private RequestQueue mRequestQueue;

	public RequestManager(Context context) {
		mRequestQueue = VolleySingleton.getRequestQueue(context);
	}
	
	public RequestQueue getRequestQueue() {
		return mRequestQueue;
	}
	
	public <T> void addRequest(Request<T> request) {
		if (request.getTag() == null) {
			request.setTag(this);
		}
		mRequestQueue.add(request);
	}

	public <T> void cancelRequest(Request<T> request) {
		request.cancel();
	}
	
	public void cancelRequestsByTag(Object tag) {
		mRequestQueue.cancelAll(tag);
	}

	public void cancelAllRequests() {
		cancelRequestsByTag(this);
	}

	public <T> void start(BaseRequest<T> request) {
		RequestTask task = new RequestTask(request, this);
		task.start();
	}
	

}
