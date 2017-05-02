package com.abdymalikmulky.abdroidmvp;

import com.abdymalikmulky.abdroidmvp.app.news.NewsActivity;
import com.abdymalikmulky.abdroidmvp.network.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by abdymalikmulky on 2/15/17.
 */

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent  {
    void inject(NewsActivity newsActivity);
}
