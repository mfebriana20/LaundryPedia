package com.laundrypedia.laundrypedia.service.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by yzzzd on 4/10/16.
 */
public class ApiClient {
    public static ApiInterface getClient() {
        Retrofit retrofit = provideRetrofit(provideGson(), provideOkhttpClient());
        return provideApiInterface(retrofit);
    }

    public static Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeNulls();
        return gsonBuilder.create();
    }


    private static OkHttpClient provideOkhttpClient() {

        HttpLoggingInterceptor interceptors = new HttpLoggingInterceptor();
        interceptors.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS);
            client.addInterceptor(interceptors);

        return client.build();
    }

    private static Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("http://laundrypedia.store/")
                .client(okHttpClient)
                .build();
    }


    private static ApiInterface provideApiInterface(Retrofit retrofit) {
        return retrofit.create(ApiInterface.class);
    }
}