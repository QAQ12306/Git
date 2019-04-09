package com.example.john.test003;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class TextViewActivity extends AppCompatActivity {
    private TextView mtxt01;
    private TextView mtxt02;
    private TextView mtxt03;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view);
        mtxt01 =findViewById(R.id.txt01);
        mtxt01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TextViewActivity.this,"txt01被点击了",Toast.LENGTH_SHORT).show();
            }
        });
        mtxt02=findViewById(R.id.txt02);
        mtxt02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TextViewActivity.this,"txt02被点击了",Toast.LENGTH_SHORT).show();
            }
        });
        mtxt03=findViewById(R.id.txt03);
        mtxt03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TextViewActivity.this,"txt03被点击",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
