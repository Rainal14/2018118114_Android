package com.example.lab003;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class ImplicitIntent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("ImplicitIntent", "栈id为：" + getTaskId());
        setContentView(R.layout.activity_implicit_intent);
    }
}