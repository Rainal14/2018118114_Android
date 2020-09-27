package com.example.lab003;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class TestIntentActivity extends AppCompatActivity {

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("TestIntentActivity", "onDestroy");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_intent);
        Log.d("TestIntentActivity", "栈id为：" + getTaskId()); //测试启动模式
        Button button1 = findViewById(R.id.button_baidu);  //turn to http://www.baidu.com
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent);
            }
        });

        Button button2 = findViewById(R.id.button_tel);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:10086"));
                startActivity(intent);
            }
        });

        Button button3 = findViewById(R.id.button_getData);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                String data = intent.getStringExtra("extra_data");
                Log.d("TestIntentActivity", data);
            }
        });

        Button button4 = findViewById(R.id.button_sendDataToLastActivity);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("data_return", "王子健超级无敌帅");
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        Button button5 = findViewById(R.id.button_toMain);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TestIntentActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button button6 = findViewById(R.id.button_toImplicitIntent);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TestIntentActivity.this, ImplicitIntent.class);
                startActivity(intent);
            }
        });
    }
}