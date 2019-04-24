package com.example.john.lastread;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public
class MainActivity extends AppCompatActivity {
    private List<Article> articleList=new ArrayList<>();
private String[] data={
        "a","b","c","a1","b2","c3","a4","b5","c6","a7","b8","c9"
};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initArticles();
        ArticleAdapter adapter=new ArticleAdapter(MainActivity.this,R.layout.article_item,articleList);
        ListView listView=findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view,int position,long id){
                Article article =articleList.get(position);
                Intent intent = new Intent(MainActivity.this,ArticleDetailActivity.class);
                startActivity(intent);
            }
        });
    }
    private void initArticles(){
        for(int i=0;i<2;i++){
            Article a=new Article("A",R.drawable.ic_test);
            articleList.add(a);
            Article b=new Article("B",R.drawable.ic_test);
            articleList.add(b);
            Article c=new Article("c",R.drawable.ic_test);
            articleList.add(c);
            Article d=new Article("D",R.drawable.ic_test);
            articleList.add(d);
            Article e=new Article("E",R.drawable.ic_test);
            articleList.add(e);
            Article f=new Article("F",R.drawable.ic_test);
            articleList.add(f);
        }
    }

}
