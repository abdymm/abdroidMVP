package com.abdymalikmulky.abdroidmvp.util;

import com.abdymalikmulky.abdroidmvp.AbdroidApplication;

import java.io.File;

import okhttp3.Cache;

/**
 * Created by abdymalikmulky on 1/28/17.
 */

public class CachingUtils {

    public static Cache getCacheOkHttp(){
        File httpCacheDirectory = new File(AbdroidApplication.get().getCacheDir(), "responses");
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(httpCacheDirectory, cacheSize);
        return cache;
    }

}
