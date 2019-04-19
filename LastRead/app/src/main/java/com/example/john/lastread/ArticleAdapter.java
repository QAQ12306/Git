package com.example.john.lastread;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ArticleAdapter extends ArrayAdapter<Article> {
    private int resourceId;

    public ArticleAdapter(Context context, int textViewRescourceId, List<Article> objects) {
        super(context, textViewRescourceId, objects);
        resourceId = textViewRescourceId;
    }

    public
    View getView(int position, View convertView, ViewGroup parent) {
        Article article = getItem(position);
        View view;
        /*ViewHolder =new viewHolder;*/
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
 /*           viewHolder=new ViewHolder();
            viewHolder.articleImage= view.findViewById(R.id.article_image);
            viewHolder.articleName= view.findViewById(R.id.article_name);*/
           /* view.setTag(viewHolder);*/
        } else {
            view = convertView;
          /*  viewHolder=(ViewHolder) view.getTag();*/
        }
        ImageView articleImage=view.findViewById(R.id.article_image);
        TextView articlename= view.findViewById(R.id.article_name);
        articleImage.setImageResource(article.getImageId());
        articlename.setText(article.getName());
  /*      viewHolder.articleImage.setImageResource(article.getImageId());
        viewHolder.articleName.setText(article.getName());*/
        return view;
    }
/*class ViewHolder{
        ImageView articleImage;
        TextView articleName;
}*/
}
