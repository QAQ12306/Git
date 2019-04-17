package com.example.john.qwe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
private List<Article> articleList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initArticles();//初始化文章数据
        RecyclerView recyclerView=(RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        ArticleAdapter adapter=new ArticleAdapter(articleList);
        recyclerView.setAdapter(adapter);
    }
    private void initArticles(){
        for (int i=0;i<2;i++){
            Article q=new Article("文章1",R.drawable.ic_test);
            articleList.add(q);
            Article w=new Article("文章2",R.drawable.ic_test);
            articleList.add(w);
            Article e=new Article("文章3",R.drawable.ic_test);
            articleList.add(e);
            Article r=new Article("文章4",R.drawable.ic_test);
            articleList.add(r);
            Article t=new Article("文章5",R.drawable.ic_test);
            articleList.add(t);
            Article y=new Article("文章6",R.drawable.ic_test);
            articleList.add(y);
            Article u=new Article("文章7",R.drawable.ic_test);
            articleList.add(u);
            Article o=new Article("文章8",R.drawable.ic_test);
            articleList.add(o);

        }
    }

}
