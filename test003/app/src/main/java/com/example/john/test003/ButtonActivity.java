package com.example.john.test003;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ButtonActivity extends AppCompatActivity {
    private Button mBtn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);
        mBtn3 = (Button) findViewById(R.id.btn_3);]
        mBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(ButtonActivity.this,"Btn3被点击了",Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(ButtonActivity.this,TextViewActivity.class);
                startActivity(intent);
            }
        });
    }
    public void showToast(View view){
        Toast.makeText(this,"我被点击了",Toast.LENGTH_SHORT).show();
    }
}
