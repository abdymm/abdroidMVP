package com.abdymalikmulky.abdroidmvp.app.data.news;

import android.util.Log;

import com.abdymalikmulky.abdroidmvp.app.data.news.pojo.Berita;

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
        RealmResults<Berita> news = realm.where(Berita.class).findAll();
        if(!news.isEmpty()){
            callback.onNewsLoaded(news);
        }else {
            callback.onDataNotAvailable();
        }
    }

    @Override
    public void get(Berita newsParam, GetNewsCallback callback) {
        Berita news = realm.where(Berita.class).equalTo("detailUrl",newsParam.getDetailUrl()).findFirst();
        if(news!=null){
            callback.onNewsLoaded(news);
        }else {
            callback.onDataNotAvailable();
        }
    }

    @Override
    public void save(final Berita news) {
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
    public void delete(final Berita newsParam) {
        final Berita news = realm.where(Berita.class).equalTo("detailUrl",newsParam.getDetailUrl()).findFirst();

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
