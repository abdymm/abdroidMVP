package com.abdymalikmulky.abdroidmvp.app.data;

import android.util.Log;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by abdymalikmulky on 1/20/17.
 */

public class NewsLocal implements NewsDataSource{
    private static final String TAG = NewsLocal.class.getSimpleName();

    Realm realm;
    public NewsLocal() {
        realm = Realm.getDefaultInstance();
    }


    @Override
    public void load(LoadNewsCallback callback) {
        RealmResults<News> news = realm.where(News.class).findAll();
        if(!news.isEmpty()){
            callback.onNewsLoaded(news);
        }else {
            callback.onDataNotAvailable();
        }
    }

    @Override
    public void get(News newsParam, GetNewsCallback callback) {
        News news = realm.where(News.class).equalTo("id",newsParam.getId()).findFirst();
        if(news!=null){
            callback.onNewsLoaded(news);
        }else {
            callback.onDataNotAvailable();
        }
    }

    @Override
    public void save(final News news) {
        // Asynchronously update objects on a background thread
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                bgRealm.copyToRealmOrUpdate(news);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.d(TAG,"Success Added, "+news.toString());
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.e(TAG,"Error,  "+error.toString());
            }
        });

    }

    @Override
    public void delete(final News newsParam) {
        final News news = realm.where(News.class).equalTo("id",newsParam.getId()).findFirst();

        // Asynchronously update objects on a background thread
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                news.deleteFromRealm();
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.d(TAG,"Success Deleted, "+news.toString());
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.e(TAG,"Error,  "+error.toString());
            }
        });
    }
}
