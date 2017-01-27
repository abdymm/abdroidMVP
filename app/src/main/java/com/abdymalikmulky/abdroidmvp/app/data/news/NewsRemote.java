package com.abdymalikmulky.abdroidmvp.app.data.news;

import com.abdymalikmulky.abdroidmvp.app.data.news.pojo.Berita;
import com.abdymalikmulky.abdroidmvp.app.data.news.pojo.Beritas;
import com.abdymalikmulky.abdroidmvp.helper.ApiHelper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by abdymalikmulky on 1/20/17.
 */

public class NewsRemote implements NewsDataSource {

    private static NewsRemote INSTANCE;

    @Override
    public void load(final LoadNewsCallback callback) {
        NewsApi api = ApiHelper.client().create(NewsApi.class);
        Call<Beritas> call = api.getList();
        call.enqueue(new Callback<Beritas>() {
            @Override
            public void onResponse(Call<Beritas> call, Response<Beritas> response) {
                Timber.d(response.body().getBerita().toString());
                List<Berita> news = response.body().getBerita();
                callback.onNewsLoaded(news);
            }

            @Override
            public void onFailure(Call<Beritas> call, Throwable t) {
                Timber.e(t);
            }
        });
    }

    @Override
    public void get(Berita news, GetNewsCallback callback) {

    }

    @Override
    public void save(Berita news) {

    }

    @Override
    public void delete(Berita news) {

    }
}
