package com.abdymalikmulky.abdroidmvp.app.news;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.abdymalikmulky.abdroidmvp.AbdroidApplication;
import com.abdymalikmulky.abdroidmvp.R;
import com.abdymalikmulky.abdroidmvp.util.ActivityUtils;

import javax.inject.Inject;

import retrofit2.Retrofit;

public class NewsActivity extends AppCompatActivity implements NewsFragment.OnFragmentInteractionListener{


    @Inject
    Retrofit retrofit;

    private static final String TAG = NewsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Dagger Injection
        ((AbdroidApplication) getApplication()).getAppComponent().inject(this);

        setContentView(R.layout.activity_news);
        //create fragment
        NewsFragment newsFragment = (NewsFragment) getSupportFragmentManager().
                findFragmentById(R.id.newsContentFrame);

        if(newsFragment == null){
            newsFragment = NewsFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), newsFragment,R.id.newsContentFrame);
        }

        new NewsPresenter(newsFragment,retrofit);
    }

    @Override
    public void onFragmentInteraction(String data) {
        Log.d(TAG,data);
    }
}
