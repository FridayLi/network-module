package com.datayes.network;

import com.android.volley.VolleyError;

/**
 * Created by quandong.li on 2015/7/22.
 */
public abstract class ResponseParser<Bean,Type> {

    protected ResponseErrorChecker<Type> mResponseErrorChecker;

    public void setResponseErrorChecker(ResponseErrorChecker<Type> errorChecker) {
        mResponseErrorChecker = errorChecker;
    }

    public final Bean parse(byte[] bytes) throws VolleyError {
        Type data = convertBytes(bytes);
        if (mResponseErrorChecker != null) {
            mResponseErrorChecker.checkError(data);
        }
        return parseData(data);
    }

    public abstract Type convertBytes(byte[] bytes) throws VolleyError;

    public abstract Bean parseData(Type data) throws VolleyError;

}
