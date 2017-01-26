package com.abdymalikmulky.abdroidmvp.app.news;

import android.os.Handler;

import com.abdymalikmulky.abdroidmvp.app.data.News;
import com.abdymalikmulky.abdroidmvp.app.data.NewsDataSource;
import com.abdymalikmulky.abdroidmvp.app.data.NewsLocal;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by abdymalikmulky on 1/17/17.
 */
public class NewsPresenter implements NewsContract.Presenter{
    private static final String TAG = NewsPresenter.class.getSimpleName();


    NewsContract.View mNewsView;
    NewsLocal newsLocal;

    //sementara
    private final static ArrayList<News> NEWS_DATA_MOCK;
    static {
        NEWS_DATA_MOCK = new ArrayList<>();
        NEWS_DATA_MOCK.add(new News(1,"Walikota Jateng","Ground looks good, work required no foundation work required ","12 Januari 2017"));
        NEWS_DATA_MOCK.add(new News(2,"Gubernur Kendari","Found awesome girders at half the cost! Found awesome ","20 Februari 2017"));
        NEWS_DATA_MOCK.add(new News(3,"Berita Bandung","Found awesome girders at work required no foundation work required ","23 Februari 2017"));
    }

    public NewsPresenter(@NotNull NewsContract.View newsView) {
        newsLocal = new NewsLocal();
        mNewsView = checkNotNull(newsView);
        mNewsView.setPresenter(this);
    }

    @Override
    public void loadNews() {
        for (News newsData:NEWS_DATA_MOCK) {
            newsLocal.save(newsData);
        }

        new Handler().postDelayed(new Runnable() {
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
    }

    @Override
    public void openTaskDetail() {

    }

    @Override
    public void start() {
        loadNews();
    }
}
