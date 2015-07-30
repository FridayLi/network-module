package com.datayes.volleymodule;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.datayes.network.BaseRequest;
import com.datayes.network.JSONObjectResponseParser;
import com.datayes.network.ResponseParser;

import org.json.JSONObject;

/**
 * Created by quandong.li on 2015/7/29.
 */
public class LoginRequest extends BaseRequest<AuthResult> {

    public LoginRequest(String user, String password, Response.Listener<AuthResult> listener,
                        Response.ErrorListener errorListener) {
        super(Method.POST, "https://gw.wmcloud.com/usermaster/oauth2/token.json",
                listener, errorListener);
        int atIndex = user.indexOf("@");
        if(atIndex > 0) {
            addParam("username", user.substring(0, atIndex));
            addParam("tenant", user.substring(atIndex + 1));
        } else {
            addParam("username", user);
        }
        addParam("password", password);
        addParam("grant_type", "password");
    }

    @Override
    protected ResponseParser<AuthResult, ?> getResponseParser() {
        Log.d("test", "timeout = " + this.getTimeoutMs());

        ResponseParser<AuthResult, ?> parser = new JSONObjectResponseParser<AuthResult>() {
            @Override
            public AuthResult parseData(JSONObject data) throws VolleyError {
                AuthResult result = new AuthResult();
                result.parseJSONObject(data);
                return result;
            }
        };
        return parser;
    }
}
