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
import com.qxf.newsapp.utils.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.NonNull;

/**
 * Created by quanxiaofeng on 2017/11/6.
 */

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener{

    private List<NewsInfo.ShowapiResBodyBean.NewslistBean> newsInfoList;
    private Context context;
    private final int TYPE_HEADER = 1;
    private final int TYPE_NORMAL = 2;
    private List<String> images;
    private OnItemClickListener onItemClickListener = null;

    public NewsAdapter(Context context) {
        this.context = context;
    }

    public void setList(@NonNull List<NewsInfo.ShowapiResBodyBean.NewslistBean> newsInfoList) {
        this.newsInfoList = newsInfoList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        RecyclerView.ViewHolder viewHolder;
        if (TYPE_HEADER == viewType) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_banner_news, parent, false);
            viewHolder = new HeaderViewHolder(v);
        } else {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_news, parent, false);
            v.setOnClickListener(this);
            viewHolder = new NormalViewHolder(v);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {
            initHeaderView(holder, position);
        } else {
            initNormalView(holder, position);
        }
    }

    private void initNormalView(RecyclerView.ViewHolder holder, int position) {
        NormalViewHolder normalViewHolder = (NormalViewHolder) holder;
        NewsInfo.ShowapiResBodyBean.NewslistBean newslistBean = newsInfoList.get(position - 1);
        normalViewHolder.tvTitle.setText(newslistBean.getTitle());
        normalViewHolder.tvNews.setText("体育新闻");
        normalViewHolder.tvTime.setText(newslistBean.getCtime());
        Glide.with(context)
                .load(newslistBean.getPicUrl())
                .thumbnail(0.1f)
                .placeholder(R.mipmap.ic_launcher)
                .into(normalViewHolder.ivNews);
        normalViewHolder.itemView.setTag(position);
    }

    private void initHeaderView(RecyclerView.ViewHolder holder, int position) {
        HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
        Banner banner = headerViewHolder.banner;
        images = new ArrayList<>();
        images.add("http://otmfrej7w.bkt.clouddn.com/1.png");
        images.add("http://otmfrej7w.bkt.clouddn.com/banner2.png");
        images.add("http://otmfrej7w.bkt.clouddn.com/3.png");
        banner.setImages(images);
        banner.setImageLoader(new GlideImageLoader());
        banner.setBannerAnimation(Transformer.Accordion);
        banner.isAutoPlay(true);
        banner.setDelayTime(3000);
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        banner.start();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        }
        return TYPE_NORMAL;
    }

    @Override
    public int getItemCount() {
        return newsInfoList.size();
    }

    @Override
    public void onClick(View view) {
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(view ,(int)view.getTag());
        }
    }

    class NormalViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_news)
        ImageView ivNews;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_news)
        TextView tvNews;
        @BindView(R.id.tv_time)
        TextView tvTime;

        public NormalViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.banner)
        Banner banner;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    interface OnItemClickListener {
        void onItemClick(View view , int position);
    }
}
