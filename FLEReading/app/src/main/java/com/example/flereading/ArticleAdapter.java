package com.example.flereading;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ArticleAdapter extends BaseAdapter {
    private Context acontext;
    private LayoutInflater layoutInflater=null;
    public ArticleAdapter(Context context){
        this.acontext=context;
        this.layoutInflater=LayoutInflater.from(context);
    }
    static class ViewHolder{
        public ImageView articleImage;
        public TextView articleName;
    }
    @Override
    public
    int getCount() {
        return 12;
    }

    @Override
    public
    Object getItem(int position) {
        return null;
    }

    @Override
    public
    long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.article_item,null);
            viewHolder=new ViewHolder();
            viewHolder.articleImage=convertView.findViewById(R.id.article_image);
            viewHolder.articleName=convertView.findViewById(R.id.article_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        if(viewHolder !=null) {
            switch (position) {
                case 0:
                    viewHolder.articleImage.setImageResource(R.drawable.ic_a);
                    viewHolder.articleName.setText("联合国：需加大力度重视温室气体");
                    break;
                case 1:
                    viewHolder.articleImage.setImageResource(R.drawable.ic_b);
                    viewHolder.articleName.setText("菲律宾三名禁毒警察被裁定法外谋杀罪");
                    break;
                case 2:
                    viewHolder.articleImage.setImageResource(R.drawable.ic_c);
                    viewHolder.articleName.setText("帮助破获尼泊尔黑猩猩走私案");
                    break;
                case 3:
                    viewHolder.articleImage.setImageResource(R.drawable.ic_d);
                    viewHolder.articleName.setText("英格兰银行称无协议脱欧或重创英国经济");
                    break;
                case 4:
                    viewHolder.articleImage.setImageResource(R.drawable.ic_e);
                    viewHolder.articleName.setText("G20峰会在布宜诺斯艾利斯举行");
                    break;
                case 5:
                    viewHolder.articleImage.setImageResource(R.drawable.ic_f);
                    viewHolder.articleName.setText("法国总统谴责巴黎反政府游行暴力事件");
                    break;
                case 6:
                    viewHolder.articleImage.setImageResource(R.drawable.ic_g);
                    viewHolder.articleName.setText("阿富汗多支女子运动队遭性侵");
                    break;
                case 7:
                    viewHolder.articleImage.setImageResource(R.drawable.ic_h);
                    viewHolder.articleName.setText("英国首相开启为期5天“脱欧”辩论");
                    break;
                case 8:
                    viewHolder.articleImage.setImageResource(R.drawable.ic_i);
                    viewHolder.articleName.setText("法国政府放弃上涨燃油税计划");
                    break;
                case 9:
                    viewHolder.articleImage.setImageResource(R.drawable.ic_j);
                    viewHolder.articleName.setText("今年二氧化碳排放量达到历史新高");
                    break;
                case 10:
                    viewHolder.articleImage.setImageResource(R.drawable.ic_k);
                    viewHolder.articleName.setText("意大利夜店发生踩踏事件 至少6人死亡");
                    break;
                case 11:
                    viewHolder.articleImage.setImageResource(R.drawable.ic_m);
                    viewHolder.articleName.setText("日本检察官正式对日产汽车前会长提起公诉");
                    break;
            }
        }
        return convertView;
    }
}

