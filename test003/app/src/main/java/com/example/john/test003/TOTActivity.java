package com.example.john.test003;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TOTActivity extends AppCompatActivity {
    private Button mBtn_men1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tot);
        mBtn_men1 =(Button)findViewById(R.id.btn_men1);
        mBtn_men1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(TOTActivity.this,QWActivity.class);
                startActivity(intent);
            }
        });
    }
}
