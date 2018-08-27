package com.example.karthikrammoorthy.currencyapplication;

import android.content.Context;

import com.android.volley.*;
import com.android.volley.toolbox.Volley;

/**
 * Created by karthikrammoorthy on 08/03/18.
 */

public class RequestQueueSingleton {


    private  static RequestQueueSingleton mInstance;

    private RequestQueue mRequestQueue;
    private static Context mCtx;

    private RequestQueueSingleton(Context context) {
        mCtx = context.getApplicationContext();
        mRequestQueue = getRequestQueue();
    }

    public static synchronized RequestQueueSingleton getInstance(Context context) {

        if(mInstance == null) {
            mInstance = new RequestQueueSingleton(context.getApplicationContext());
        }

        return  mInstance;
    }

   public RequestQueue getRequestQueue() {

        if(mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());

        }

        return  mRequestQueue;
   }

   public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
   }
}
