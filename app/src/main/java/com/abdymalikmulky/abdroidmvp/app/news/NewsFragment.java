package com.abdymalikmulky.abdroidmvp.app.news;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.abdymalikmulky.abdroidmvp.R;
import com.abdymalikmulky.abdroidmvp.app.data.news.pojo.Berita;
import com.abdymalikmulky.abdroidmvp.listener.OnVerticalScrollListener;
import com.abdymalikmulky.abdroidmvp.util.LoadingUiUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsFragment extends Fragment implements NewsContract.View,SwipeRefreshLayout.OnRefreshListener,NewsContract.Listener{
    private static final String TAG = NewsFragment.class.getSimpleName();


    //Fragment depend
    private OnFragmentInteractionListener mListener;

    //news
    private NewsContract.Presenter mNewsPresenter;

    //component

    @BindView(R.id.news_refresh)
    SwipeRefreshLayout refresher;
    @BindView(R.id.news_loading)
    ProgressBar loading;
    @BindView(R.id.news_loadmore)
    ProgressBar loadingMore;
    @BindView(R.id.news_loading_text)
    TextView loadingText;

    //List
    @BindView(R.id.news_list)
    RecyclerView newsList;

    private NewsAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;


    private List<Berita> news;
    int currentPage = 1;
    boolean loadMoreState = true;


    public NewsFragment() {
    }

    public static NewsFragment newInstance() {
        NewsFragment fragment = new NewsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        ButterKnife.bind(this, view);


        setUpRV(newsList);
        setUpSwipeRefresh(refresher);


        news = new ArrayList<>();
        mAdapter = new NewsAdapter(news,this);
        newsList.setAdapter(mAdapter);


        return view;
    }

    private void setUpRV(RecyclerView rv){
        rv.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(mLayoutManager);
        rv.setOnScrollListener(new OnVerticalScrollListener(currentPage) {
            @Override
            public void onLoadMore(int current_page) {
                if(loadMoreState){
                    loadMoreState = false;
                    showLoadingMore(true);
                    mNewsPresenter.loadMore(currentPage);
                }
            }
        });

    }
    private void setUpSwipeRefresh(SwipeRefreshLayout sr){
        sr.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        sr.setOnRefreshListener(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
            onActionActivity();
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }


    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void showLoading(boolean show) {
        if(!show){
            refresher.setRefreshing(false);
        }
        LoadingUiUtils.hideShowLoading(loading,loadingText,show);
    }

    public void showLoadingMore(boolean show){
        if(!show){
            loadingMore.setVisibility(View.GONE);
        }else {
            loadingMore.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showNoNews() {
        showLoading(false);
        Toast.makeText(getActivity(), "No Berita", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNews(List<Berita> news) {
        currentPage++;
        showLoading(false);
        mAdapter.replace(news);
    }

    @Override
    public void showLoadMoreNews(List<Berita> news) {
        currentPage++;
        loadMoreState = true;
        showLoadingMore(false);
        mAdapter.add(news);
    }

    @Override
    public void showNoLoadMoreNews() {
        Toast.makeText(getActivity(), "NO LOAD MORE NEWS", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void showNewsDetail(String newsId) {
        //show detail
    }

    @Override
    public void setPresenter(@NonNull NewsContract.Presenter presenter) {
        mNewsPresenter = presenter;
    }

    @Override
    public void onNewsClick(Berita news) {
        mNewsPresenter.openNewsDetail(news.getId());
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(String param);
    }



    public void onActionActivity() {
        if (mListener != null) {
            mListener.onFragmentInteraction("");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mNewsPresenter.loadNews(currentPage);
    }

    @Override
    public void onRefresh() {
        currentPage=1;
        mNewsPresenter.loadNews(currentPage);
    }


}
