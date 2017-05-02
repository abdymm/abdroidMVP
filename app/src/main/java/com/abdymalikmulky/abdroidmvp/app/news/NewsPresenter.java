package com.abdymalikmulky.abdroidmvp.app.news;

import com.abdymalikmulky.abdroidmvp.app.data.news.NewsDataSource;
import com.abdymalikmulky.abdroidmvp.app.data.news.NewsLocal;
import com.abdymalikmulky.abdroidmvp.app.data.news.NewsRemote;
import com.abdymalikmulky.abdroidmvp.app.data.news.pojo.Berita;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Retrofit;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by abdymalikmulky on 1/17/17.
 */

public class NewsPresenter implements NewsContract.Presenter{
    private static final String TAG = NewsPresenter.class.getSimpleName();

    NewsContract.View mNewsView;
    NewsLocal newsLocal;
    NewsRemote newsRemote;



    public NewsPresenter(@NotNull NewsContract.View newsView, Retrofit retrofit) {
        newsRemote = new NewsRemote(retrofit);
        mNewsView = checkNotNull(newsView);
        mNewsView.setPresenter(this);
    }

    @Override
    public void loadNews(int page) {
        newsRemote.load(page,new NewsDataSource.LoadNewsCallback() {
            @Override
            public void onNewsLoaded(List<Berita> news) {
                mNewsView.showNews(news);
            }
            @Override
            public void onDataNotAvailable() {
                mNewsView.showNoNews();
            }
        });
    }

    @Override
    public void loadMore(int page) {
        newsRemote.load(page, new NewsDataSource.LoadNewsCallback() {
            @Override
            public void onNewsLoaded(List<Berita> news) {
                mNewsView.showLoadMoreNews(news);
            }

            @Override
            public void onDataNotAvailable() {
                mNewsView.showNoLoadMoreNews();
            }
        });
    }

    @Override
    public void openNewsDetail(String newsId) {
        mNewsView.showNewsDetail(newsId);
    }


    @Override
    public void start() {

    }
}
