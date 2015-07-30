package com.datayes.volleymodule;

import com.android.volley.VolleyError;

import org.json.JSONObject;

/**
 * Created by quandong.li on 2015/7/29.
 */
public class AuthResult {

    private String scope;
    private String tokenType;
    private String accessToken;
    private String refreshToken;
    private long expireTime;

    public void parseJSONObject(JSONObject object) throws VolleyError {
        scope = object.optString("scope");
        tokenType = object.optString("token_type");
        accessToken = object.optString("access_token");
        refreshToken = object.optString("refresh_token");
        int expiresIn = object.optInt("expires_in");
        expireTime = System.currentTimeMillis() + expiresIn*1000 - 1000;
    }

    public String getScope() {
        return scope;
    }

    public String getTokenType() {
        return tokenType;
    }


    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public long getExpireTime() {
        return expireTime;
    }

}
