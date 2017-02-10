package com.abdymalikmulky.abdroidmvp.app.data.news;

import com.abdymalikmulky.abdroidmvp.app.data.news.pojo.Berita;

import java.util.List;

/**
 * Created by abdymalikmulky on 1/20/17.
 */

public interface NewsDataSource {

    //OnAction Callback
    interface LoadNewsCallback {
        void onNewsLoaded(List<Berita> news);
        void onDataNotAvailable();
    }
    interface GetNewsCallback {
        void onNewsLoaded(Berita news);
        void onDataNotAvailable();
    }


    void load(LoadNewsCallback callback);

    void get(Berita news,GetNewsCallback callback);

    void save(Berita news);

    void delete(Berita news);
}
