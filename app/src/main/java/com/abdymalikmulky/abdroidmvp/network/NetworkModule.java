package com.abdymalikmulky.abdroidmvp.network;

import android.app.Application;

import com.abdymalikmulky.abdroidmvp.util.EndpointUtils;
import com.abdymalikmulky.abdroidmvp.util.InterceptorUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.abdymalikmulky.abdroidmvp.util.InterceptorUtils.CACHE_INTERCEPTOR;
import static com.abdymalikmulky.abdroidmvp.util.InterceptorUtils.CACHE_OFFLINE_INTERCEPTOR;

/**
 * Created by abdymalikmulky on 2/19/17.
 */
@Module
public class NetworkModule {
    public NetworkModule(){
    }

    @Provides
    @Singleton
    Cache provideHttpCache(Application application) {
        File httpCacheDirectory = new File(application.getCacheDir(), "responses");
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(httpCacheDirectory, cacheSize);
        return cache;
    }


    @Provides
    @Singleton
    Gson provideGson(){
        return new GsonBuilder()
                .setLenient()
                .create();
    }


    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache){
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(InterceptorUtils.getLoggingInterceptor())
                .addInterceptor(CACHE_OFFLINE_INTERCEPTOR)
                .addNetworkInterceptor(CACHE_INTERCEPTOR)
                .cache(cache)
                .build();
        return client;
    }


    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient client,Gson gson){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(EndpointUtils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

        return retrofit;
    }
}
