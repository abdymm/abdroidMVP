package com.abdymalikmulky.abdroidmvp;

import android.app.Application;
import android.content.res.Resources;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by abdymalikmulky on 2/15/17.
 */
@Module
public class AppModule {

    Application application;

    AppModule(Application application)
    {
        this.application = application;
    }

    @Provides
    @Singleton
    public Application provideApplication()
    {
        return application;
    }

    @Provides
    @Singleton
    public Resources provideResources(Application context)
    {
        return context.getResources();
    }

}
