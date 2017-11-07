package com.qxf.newsapp.news;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qxf.newsapp.R;
import com.qxf.newsapp.news.net.beans.NewsInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.NonNull;

/**
 * Created by quanxiaofeng on 2017/11/6.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private List<NewsInfo.ShowapiResBodyBean.NewslistBean> newsInfoList;
    private Context context;

    public NewsAdapter(Context context) {
        this.context = context;
    }

    public void setList(@NonNull List<NewsInfo.ShowapiResBodyBean.NewslistBean> newsInfoList) {
        this.newsInfoList = newsInfoList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_news, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NewsInfo.ShowapiResBodyBean.NewslistBean newslistBean = newsInfoList.get(position);
        holder.tvTitle.setText(newslistBean.getTitle());
        holder.tvNews.setText("体育新闻");
        holder.tvTime.setText(newslistBean.getCtime());
        Glide.with(context).load(newslistBean.getUrl()).into(holder.ivNews);
    }

    @Override
    public int getItemCount() {
        return newsInfoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_news)
        ImageView ivNews;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_news)
        TextView tvNews;
        @BindView(R.id.tv_time)
        TextView tvTime;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
