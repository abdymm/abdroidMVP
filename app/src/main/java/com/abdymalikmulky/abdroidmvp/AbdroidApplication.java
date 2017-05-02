package com.abdymalikmulky.abdroidmvp;

import android.app.Application;
import android.content.res.Configuration;

import com.abdymalikmulky.abdroidmvp.network.NetworkModule;

import io.realm.Realm;
import timber.log.Timber;

/**
 * Created by abdymalikmulky on 1/17/17.
 */

public class AbdroidApplication extends Application{

    AppComponent appComponent;

    private static
    AbdroidApplication instance;

    public static AbdroidApplication get() { return instance; }


    @Override
    public void onCreate() {
        super.onCreate();
        //instance
        instance = this;

        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .build();

        // Required initialization logic here!
        Realm.init(this);

        //Timber initialize tree
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    public AppComponent getAppComponent(){
        return appComponent;
    }


    // Called by the system when the device configuration changes while your component is running.
    // Overriding this method is totally optional!
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    // This is called when the overall system is running low on memory,
    // and would like actively running processes to tighten their belts.
    // Overriding this method is totally optional!
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
