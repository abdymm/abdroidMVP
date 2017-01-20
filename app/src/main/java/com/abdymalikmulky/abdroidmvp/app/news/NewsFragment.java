package com.abdymalikmulky.abdroidmvp.app.news;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.abdymalikmulky.abdroidmvp.R;
import com.abdymalikmulky.abdroidmvp.app.data.News;
import com.abdymalikmulky.abdroidmvp.util.LoadingUiUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsFragment extends Fragment implements NewsContract.View{
    private static final String TAG = NewsFragment.class.getSimpleName();

    //Fragment depend
    private OnFragmentInteractionListener mListener;


    private NewsContract.Presenter mNewsPresenter;


    //component
    @BindView(R.id.news_loading)
    ProgressBar loading;
    @BindView(R.id.news_data)
    TextView data;

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
            //arg
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        ButterKnife.bind(this, view);

        return view;

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
        LoadingUiUtils.hideShowLoading(loading,show);
    }

    @Override
    public void showNews(List<News> news) {
        Toast.makeText(getActivity().getApplicationContext(), news.toString(), Toast.LENGTH_SHORT).show();
        for (News newsData : news) {
            data.append(newsData.getTitle()+" "+newsData.getSummary()+" "+newsData.getDate()+"\n");
        }
    }

    @Override
    public void showNewsDetail(int newsId) {

    }

    @Override
    public void setPresenter(@NonNull NewsContract.Presenter presenter) {
        mNewsPresenter = presenter;
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
        mNewsPresenter.start();
    }
}
