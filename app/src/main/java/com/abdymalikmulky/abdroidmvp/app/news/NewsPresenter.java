package com.abdymalikmulky.abdroidmvp.app.news;

import android.os.Handler;

import com.abdymalikmulky.abdroidmvp.app.data.News;
import com.abdymalikmulky.abdroidmvp.app.data.NewsDataSource;
import com.abdymalikmulky.abdroidmvp.app.data.NewsLocal;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by abdymalikmulky on 1/17/17.
 */
public class NewsPresenter implements NewsContract.Presenter{
    private static final String TAG = NewsPresenter.class.getSimpleName();


    NewsContract.View mNewsView;
    NewsLocal newsLocal;


    public NewsPresenter(@NotNull NewsContract.View newsView) {
        newsLocal = new NewsLocal();
        mNewsView = checkNotNull(newsView);
        mNewsView.setPresenter(this);
    }

    @Override
    public void loadNews() {
        mNewsView.showLoading(true);

        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                newsLocal.load(new NewsDataSource.LoadNewsCallback() {
                    @Override
                    public void onNewsLoaded(List<News> news) {
                        mNewsView.showNews(news);
                        mNewsView.showLoading(false);
                    }

                    @Override
                    public void onDataNotAvailable() {

                    }
                });

            }
        }, 2000);
    }

    @Override
    public void openTaskDetail() {

    }

    @Override
    public void start() {
        loadNews();
    }
}
