package com.example.john.qwe;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {
    private List<Article> mArticleList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView articleImage;
        TextView articleName;
        TextView getArticleDetail;
        public ViewHolder(View view){
            super(view);
            articleImage= view.findViewById(R.id.article_image);
            articleName= view.findViewById(R.id.article_name);
            getArticleDetail=view.findViewById(R.id.article_detail);
        }
    }
    public ArticleAdapter(List<Article> articlelist){
        mArticleList=articlelist;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.article_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position) {
        Article article=mArticleList.get(position);
        holder.articleImage.setImageResource(article.getImageId());
        holder.articleName.setText(article.getName());
        holder.getArticleDetail.setText(article.getDetail());
    }

    @Override
    public int getItemCount(){
        return mArticleList.size();
    }
}
