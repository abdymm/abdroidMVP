package com.abdymalikmulky.abdroidmvp.app.news;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abdymalikmulky.abdroidmvp.R;
import com.abdymalikmulky.abdroidmvp.app.data.news.pojo.Berita;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by abdymalikmulky on 1/26/17.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    List<Berita> mNews;


    public NewsAdapter(List<Berita> news) {
        mNews = news;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.news_title)
        TextView title;
        @BindView(R.id.news_summary)
        TextView sum;
        @BindView(R.id.news_tanggal)
        TextView date;

        public ViewHolder(View v){
            super(v);
            ButterKnife.bind(this,v);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_news, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Berita news = mNews.get(position);
        holder.title.setText(news.getJudul());
        holder.sum.setText(news.getIkhtisar());
        holder.date.setText(news.getTanggal());
    }

    @Override
    public int getItemCount() {
        return mNews.size();
    }
}
