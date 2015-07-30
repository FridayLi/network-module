package com.datayes.network;

import com.android.volley.ParseError;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * Created by quandong.li on 2015/7/22.
 */
public abstract class JSONObjectResponseParser<Bean> extends ResponseParser<Bean, JSONObject>{

    @Override
    public JSONObject convertBytes(byte[] bytes) throws VolleyError {
        try {
            String str = new String(bytes, "utf-8");
            return new JSONObject(str);
        } catch (UnsupportedEncodingException e) {
            throw new ParseError(e);
        } catch (JSONException e) {
            throw new ParseError(e);
        }
    }

}
