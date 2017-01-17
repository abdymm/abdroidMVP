package com.abdymalikmulky.abdroidmvp.app.news;

import com.abdymalikmulky.abdroidmvp.app.BasePresenter;
import com.abdymalikmulky.abdroidmvp.app.BaseView;
import com.abdymalikmulky.abdroidmvp.app.news.data.News;

import java.util.List;

/**
 * Created by abdymalikmulky on 1/17/17.
 */

public class NewsContract {
    interface View extends BaseView<Presenter>{

        void showLoading(boolean show);

        void showNews(List<News> news);

        void showNewsDetail(int newsId);

    }

    interface Presenter extends BasePresenter{

        void loadNews();

        void openTaskDetail();

    }

    interface Listener {
        void onNewsClick(News news);
    }
}
