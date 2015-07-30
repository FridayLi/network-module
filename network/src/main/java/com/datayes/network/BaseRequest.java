package com.datayes.network;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public abstract class BaseRequest<T> extends Request<T> {

    public static final int SECURE_NONE = 0;
    public static final int SECURE_USER = 1;

    private HashMap<String, String> mParams = new HashMap<String, String>();
    private HashMap<String, String> mHeaders = new HashMap<String, String>();
    private Listener<T> mListener;

    protected int mSecureType = SECURE_NONE;

    protected Priority mPriority = Priority.NORMAL;

    private String mContentType;

    public BaseRequest(int method, String url, Listener<T> listener, ErrorListener errorListener) {
        super(method, url, errorListener);
        mListener = listener;
        setRetryPolicy(new DefaultRetryPolicy(15*1000, 1, 0));
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return mParams;
    }

    @Override
    public Map<String, String> getHeaders()
            throws AuthFailureError {
        return mHeaders;
    }

    public void addParam(String key, String value) {
        mParams.put(key, value);
    }

    public void addParams(Map<String, String> params) {
        mParams.putAll(params);
    }

    public void addHeader(String title, String content) {
        mHeaders.put(title, content);
    }

    public void addHeader(Map<String, String> headers) {
        mHeaders.putAll(headers);
    }

    public int getSecureType() {
        return mSecureType;
    }

    public void setSecureType(int mSecureType) {
        this.mSecureType = mSecureType;
    }

    public void setContentType(String contentType) {
        mContentType = contentType;
    }

    @Override
    public Priority getPriority() {
        return mPriority;
    }

    public void setPriority(Priority priority) {
        mPriority = priority;
    }

    @Override
    public String getUrl() {
        if (getMethod() == Method.GET) {
            StringBuilder urlBuilder = new StringBuilder();
            urlBuilder.append(super.getUrl());
            if (!mParams.isEmpty()) {
                urlBuilder.append('?');
                Iterator<Map.Entry<String, String>> iterator = mParams.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, String> entry = iterator.next();
                    urlBuilder.append(entry.getKey());
                    urlBuilder.append('=');
                    try {
                        urlBuilder.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    if (iterator.hasNext()) {
                        urlBuilder.append('&');
                    }
                }
            }

            return urlBuilder.toString();
        } else {
            return super.getUrl();
        }

    }

    @Override
    public String getCacheKey() {
        StringBuilder cacheKeyBuilder = new StringBuilder();
        cacheKeyBuilder.append(getUrl());
        for (Map.Entry<String, String> entry : mParams.entrySet()) {
            cacheKeyBuilder.append(entry.getKey());
            cacheKeyBuilder.append('=');
            cacheKeyBuilder.append(entry.getValue());
            cacheKeyBuilder.append('&');
        }
        return cacheKeyBuilder.toString();
    }


    protected boolean needSetCookie() {
        return false;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            T result = getResponseParser().parse(response.data);
            if (needSetCookie()) {
                DYCookieManager.getInstance().setCookie(response.headers);
            }
            return Response.success(result, HttpHeaderParser.parseCacheHeaders(response));
        } catch (VolleyError e) {
            return Response.error(e);
        }
    }

    protected abstract ResponseParser<T, ?> getResponseParser();
}
