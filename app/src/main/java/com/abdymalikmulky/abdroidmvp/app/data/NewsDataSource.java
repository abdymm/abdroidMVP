package com.abdymalikmulky.abdroidmvp.app.data;

import java.util.List;

/**
 * Created by abdymalikmulky on 1/20/17.
 */

public interface NewsDataSource {

    //OnAction Callback
    interface LoadNewsCallback {
        void onNewsLoaded(List<News> news);
        void onDataNotAvailable();
    }
    interface GetNewsCallback {
        void onNewsLoaded(News news);
        void onDataNotAvailable();
    }


    void load(LoadNewsCallback callback);

    void get(News news,GetNewsCallback callback);

    void save(News news);

    void delete(News news);
}
