package com.datayes.volleymodule;

import android.content.Context;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.datayes.network.RequestManager;
import com.datayes.network.ResponseListener;


/**
 * Created by quandong.li on 2015/7/29.
 */
public class LoginManager extends RequestManager{

    public LoginManager(Context context) {
        super(context);
    }

    public void login(String userName, String password,
                      Response.Listener<AuthResult> listener,
                      Response.ErrorListener errorListener) {
        LoginRequest request = new LoginRequest(userName,password,listener,errorListener);
        start(request);

    }

    public void login(String userName, String password,
                      final ResponseListener<AuthResult> responseListener) {
        Response.Listener<AuthResult> listener = new Response.Listener<AuthResult>() {
            @Override
            public void onResponse(AuthResult response) {
                responseListener.onSuccess(response);
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                responseListener.onError(error,-1);
            }
        };
        LoginRequest request = new LoginRequest(userName, password, listener, errorListener);
        start(request);

    }
}
