package com.datayes.network;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.Volley;

/**
 *
 * Created by quandong.li on 2015/7/28.
 */
public class VolleySingleton {

    private static volatile RequestQueue sRequestQueue;

    public static RequestQueue getRequestQueue(Context context) {
        if (sRequestQueue == null) {
            synchronized (VolleySingleton.class) {
                if (sRequestQueue == null) {
                    HttpsStack stack = new HttpsStack();
                    sRequestQueue = Volley.newRequestQueue(context, stack);
                    VolleyLog.DEBUG = true;
                }
            }
        }
        return sRequestQueue;
    }
}
