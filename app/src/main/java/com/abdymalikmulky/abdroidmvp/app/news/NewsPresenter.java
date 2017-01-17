package com.abdymalikmulky.abdroidmvp.app.news;

import android.os.Handler;

import com.abdymalikmulky.abdroidmvp.app.news.data.News;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by abdymalikmulky on 1/17/17.
 */
public class NewsPresenter implements NewsContract.Presenter{
    private static final String TAG = NewsPresenter.class.getSimpleName();


    NewsContract.View mNewsView;

    //sementara
    private final static ArrayList<News> NEWS_DATA_MOCK;
    static {
        NEWS_DATA_MOCK = new ArrayList<>();
        NEWS_DATA_MOCK.add(new News(1,"Walikota Jateng","Ground looks good, work required no foundation work required ","12 Januari 2017"));
        NEWS_DATA_MOCK.add(new News(1,"Gubernur Kendari","Found awesome girders at half the cost! Found awesome ","20 Februari 2017"));
    }

    public NewsPresenter(@NotNull NewsContract.View newsView) {
        mNewsView = checkNotNull(newsView);
        mNewsView.setPresenter(this);
    }

    @Override
    public void loadNews() {
        mNewsView.showLoading(true);
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                mNewsView.showNews(NEWS_DATA_MOCK);
                mNewsView.showLoading(false);
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
