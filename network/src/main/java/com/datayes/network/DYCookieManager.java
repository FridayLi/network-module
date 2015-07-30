package com.datayes.network;

import android.text.TextUtils;
import java.util.Map;

public class DYCookieManager {
	private static final String TAG = "DYCookieManager";
	private static DYCookieManager sInstance = new DYCookieManager();
	
	private String cookie;
	
	private DYCookieManager() {
	}
	
	public static DYCookieManager getInstance() {
		return sInstance;
	}
	
	public void setCookie(Map<String,String> headers) {
         String cookieFromResponse = headers.get("Set-Cookie");
         if(cookieFromResponse != null) {
             cookie = cookieFromResponse;
         }
	}
	
	public String getCookie() {
		return cookie;
	}
	
	public boolean hasCookie() {
		return !TextUtils.isEmpty(cookie);
	}
	
	public void clear() {
		cookie = null;
	}
	
}
