package com.example.john.lastread;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.internal.NavigationMenuItemView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public
class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private List<Article> articleList=new ArrayList<>();
    private String[] data={
        "a","b","c","a1","b2","c3","a4","b5","c6","a7","b8","c9"
        };
    private DrawerLayout mDrawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);  //设置Toolbar
        mDrawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar !=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.menu);
        }
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
        NavigationView navigationView=findViewById(R.id.leftmenu);
        navigationView.setNavigationItemSelectedListener(MainActivity.this);
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem Item) {
        //设置Item点击事件

        return false;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        //主页面菜单点击侧滑
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;

            default:
        }
        return true;
    }


}
