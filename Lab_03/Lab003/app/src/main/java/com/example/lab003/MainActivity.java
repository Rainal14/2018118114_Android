package com.example.lab003;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if(resultCode == RESULT_OK) {
                    String returnedData = data.getStringExtra("data_return");
                    Log.d("MainActivity", returnedData);
                }
                break;
            default:
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        Button button1 = findViewById(R.id.button_1);  //button for test
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast.makeText(MainActivity.this, "你点击了按钮1", Toast.LENGTH_SHORT).show();
            }
        });

        Button button2 = findViewById(R.id.button_exit);   //button for exit
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button button3 = findViewById(R.id.button_testExplicitIntent); //button for testing explicit intent
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TestIntentActivity.class);
                startActivity(intent);       //start a activity
            }
        });

        Button button4 = findViewById(R.id.button_testImplicitIntent); //button for testing implicit intent
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.example.activitytest.ACTION_START");
                intent.addCategory("com.example.activitytest.MY_CATEGORY");
                startActivity(intent);
            }
        });

        Button button5 = findViewById(R.id.button_sendData);
        button5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String data = "王子健最帅";
                Intent intent = new Intent(MainActivity.this, TestIntentActivity.class);
                intent.putExtra("extra_data", data);
                startActivity(intent);
            }
        });

        Button button6 = findViewById(R.id.button_getData);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TestIntentActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override //create menu and display it
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override //make events for the menu
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_item:
                Toast.makeText(this, "你点击了Add", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(this,"你点击了Remove", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }
}