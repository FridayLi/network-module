package com.datayes.network;


public class RequestTask {
//	private static final String TAG = "RequestTask";

	private BaseRequest<?> mRequest;
	private RequestManager mManager;
	
	public RequestTask(BaseRequest<?> request, RequestManager manager) {
		mRequest = request;
		mManager = manager;
	}
	
	
	public void start() {
		if(mRequest.isCanceled()) {
			return;
		}
		if (prepare()) {
			mManager.addRequest(mRequest);
		}
	}

	private boolean prepare() {
		//TODO prepare before add to queue
		return true;
	}

}
