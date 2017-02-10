package com.abdymalikmulky.abdroidmvp.app.news;

import com.abdymalikmulky.abdroidmvp.app.data.news.NewsDataSource;
import com.abdymalikmulky.abdroidmvp.app.data.news.NewsLocal;
import com.abdymalikmulky.abdroidmvp.app.data.news.NewsRemote;
import com.abdymalikmulky.abdroidmvp.app.data.news.pojo.Berita;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import timber.log.Timber;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by abdymalikmulky on 1/17/17.
 */
public class NewsPresenter implements NewsContract.Presenter{
    private static final String TAG = NewsPresenter.class.getSimpleName();


    NewsContract.View mNewsView;
    NewsLocal newsLocal;
    NewsRemote newsRemote;


    public NewsPresenter(@NotNull NewsContract.View newsView) {
        newsRemote = new NewsRemote();
        mNewsView = checkNotNull(newsView);
        mNewsView.setPresenter(this);
    }

    @Override
    public void loadNews() {
        /*new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                newsLocal.load(new NewsDataSource.LoadNewsCallback() {
                    @Override
                    public void onNewsLoaded(List<News> news) {
                        mNewsView.showNews(news);
                    }
                    @Override
                    public void onDataNotAvailable() {
                        mNewsView.showNoNews();
                    }
                });

            }
        }, 1000);
        */
        newsRemote.load(new NewsDataSource.LoadNewsCallback() {
            @Override
            public void onNewsLoaded(List<Berita> news) {
                mNewsView.showNews(news);
            }
            @Override
            public void onDataNotAvailable() {
                mNewsView.showNoNews();
                Timber.e("No Data");
            }
        });
    }

    @Override
    public void openTaskDetail() {

    }

    @Override
    public void start() {
        loadNews();
    }
}
