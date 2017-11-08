package com.aadamsdev.seshmobile.utils;

import android.content.Context;
import android.nfc.Tag;
import android.util.Log;

import com.aadamsdev.seshmobile.Product;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by andrewadams on 2017-11-08.
 */

public class HttpUtils {
    private final static String TAG = HttpUtils.class.getSimpleName();
    private final static String baseUrl = "http://10.0.2.2:8000"; // TODO: LOCALHOST

    public static void getAllProducts(Context context, final HttpCallback httpCallback) {
        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest request = new StringRequest(Request.Method.GET, baseUrl + "/product/all", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "onResponse");

                Gson gson = new Gson();

                Product[] products = gson.fromJson(response, Product[].class);
                List<Product> toReturn = Arrays.asList(products);

                httpCallback.onProductsReceived(toReturn);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        queue.add(request);
    }

    public interface HttpCallback {
        void onProductsReceived(List<Product> products);
    }
}
