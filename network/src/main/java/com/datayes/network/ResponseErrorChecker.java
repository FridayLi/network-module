package com.datayes.network;

import com.android.volley.VolleyError;

public interface ResponseErrorChecker<T> {
	void checkError(T response) throws VolleyError;
}
