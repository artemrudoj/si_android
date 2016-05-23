package com.wintersportcoaches.common.rest.service;

import android.support.annotation.NonNull;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by artem on 23.05.16.
 */
public class NetworkServiceFactory {

    public final static String WSC_SERVER_URL = "http://ec2-54-93-219-101.eu-central-1.compute.amazonaws.com:8000/";
    private static final OkHttpClient CLIENT = new OkHttpClient();

    public static NetworkService getNetworkService() {
        return buildRetrofit().create(NetworkService.class);
    }

    @NonNull
    public static Retrofit buildRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(WSC_SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(CLIENT)
                .build();
    }

}
