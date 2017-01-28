package com.abdymalikmulky.abdroidmvp.util;

import com.abdymalikmulky.abdroidmvp.AbdroidApplication;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by abdymalikmulky on 1/28/17.
 */

public class InterceptorUtils {
    public static final String CACHE_CONTROL = "Cache-Control";

    public static final int MAX_STALE_OFFLINE = 3; //days //Timeout kadaluarsa cache
    public static final int MAX_AGE_OFFLINE = 60; //second //Timeout value in seconds to hit again.
    public static final int MAX_AGE_ONLINE = 2; //minutes //Timeout value in seconds to hit again.


    public static final Interceptor getLoggingInterceptor(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }

    public static final Interceptor CACHE_OFFLINE_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetworkUtils.isNetworkAvailable(AbdroidApplication.get())) {
                CacheControl cacheControl = new CacheControl.Builder()
                        .maxStale(MAX_STALE_OFFLINE, TimeUnit.DAYS)
                        .maxAge(MAX_AGE_OFFLINE, TimeUnit.SECONDS)
                        .build();

                request = request.newBuilder()
                        .cacheControl( cacheControl )
                        .build();
            }
            return chain.proceed(request);
        }
    };

    public static final Interceptor CACHE_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response response = chain.proceed(chain.request());

            CacheControl cacheControl = new CacheControl.Builder()
                    .maxAge(MAX_AGE_ONLINE, TimeUnit.MINUTES)
                    .build();

            return response.newBuilder()
                    .header(CACHE_CONTROL, cacheControl.toString() )
                    .build();
        }
    };


}
