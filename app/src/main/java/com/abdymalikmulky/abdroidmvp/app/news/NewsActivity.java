package com.abdymalikmulky.abdroidmvp.app.news;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.abdymalikmulky.abdroidmvp.R;
import com.abdymalikmulky.abdroidmvp.util.ActivityUtils;

public class NewsActivity extends AppCompatActivity implements NewsFragment.OnFragmentInteractionListener{


    private static final String TAG = NewsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        //create fragment
        NewsFragment newsFragment = (NewsFragment) getSupportFragmentManager().
                findFragmentById(R.id.newsContentFrame);
        if(newsFragment == null){
            newsFragment = NewsFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), newsFragment,R.id.newsContentFrame);
        }

        new NewsPresenter(newsFragment);
    }

    @Override
    public void onFragmentInteraction(String data) {
        Log.d(TAG,data);
    }
}
