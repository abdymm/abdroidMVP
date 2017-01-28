package com.abdymalikmulky.abdroidmvp.helper;

import com.abdymalikmulky.abdroidmvp.util.CachingUtils;
import com.abdymalikmulky.abdroidmvp.util.EndpointUtils;
import com.abdymalikmulky.abdroidmvp.util.InterceptorUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.abdymalikmulky.abdroidmvp.util.InterceptorUtils.CACHE_INTERCEPTOR;
import static com.abdymalikmulky.abdroidmvp.util.InterceptorUtils.CACHE_OFFLINE_INTERCEPTOR;

/**
 * Created by abdymalikmulky on 1/27/17.
 */

public class ApiHelper {
    public static final String BASE_URL = EndpointUtils.BASE_URL;
    private static Retrofit retrofit = null;

    public static Retrofit client() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();


        //setup cache
        Cache cache = CachingUtils.getCacheOkHttp();


        //add cache to the client
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(InterceptorUtils.getLoggingInterceptor())
                .addInterceptor(CACHE_OFFLINE_INTERCEPTOR)
                .addNetworkInterceptor(CACHE_INTERCEPTOR)
                .cache(cache)
                .build();


        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

        return retrofit;
    }



}
