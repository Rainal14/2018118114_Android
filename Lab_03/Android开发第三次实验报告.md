# Android开发第三次实验报告

## 实验目的

掌握四种活动启动模式的使用和原理

## 实验过程

### 1、standard启动模式

1）在布局文件中中添加测试按钮。

```main_layout.xml
<Button
        android:id="@+id/button_standard"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="启动模式：standard" />
```

在主活动的onCreate()中添加按钮引用。

```MainActivity
Button button7 = findViewById(R.id.button_standard);
```

2）设置监听器，创建Intent，在主活动（MainActivity）的基础上启动MainActivity。

```MainActivity
button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }
});
```

3）启动安卓虚拟设备，开始测试：点击两次“启动模式：standard”按钮

![standard_device.PNG](https://github.com/Rainal14/2018118114_Android/blob/homework/Lab_03/Lab03_pic/standard_device.PNG?raw=true)



![standard.PNG](https://github.com/Rainal14/2018118114_Android/blob/homework/Lab_03/Lab03_pic/standard.PNG?raw=true)

### 2、singleTop启动模式

1）在AndroidManifest.xml中修改启动模式为singleTop

```AndroidManifest.xml
<activity
            android:name=".MainActivity"
            android:launchMode="singleTop"
            android:label="WZJ_APP">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
```



2）启动虚拟机，多次点击按钮，logcat不会出现新的打印信息

![singleTop.PNG](https://github.com/Rainal14/2018118114_Android/blob/homework/Lab_03/Lab03_pic/singleTop.PNG?raw=true)



3）在TestIntentActivity中添加通过显式intend跳转到MainActivity的按钮

```TestIntentActivity
Button button5 = findViewById(R.id.button_toMain);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TestIntentActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
```



4）在MainActivity通过显式intent跳转到TestIntentActivity，然后通过显式intent返回MainActivity，此时由于之前的MainActivity不在栈顶，所以新的activity会被创建

![singleTop2.PNG](https://github.com/Rainal14/2018118114_Android/blob/homework/Lab_03/Lab03_pic/singleTop2.PNG?raw=true)



### 3、singleTask启动模式

1) 在AndroidManifest.xml中修改启动模式为singleTask

```AndroidManifest.xml
<activity
            android:name=".MainActivity"
            android:launchMode="singleTask"
            android:label="WZJ_APP">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
```

2)在MainActivity中添加onRestart()方法

```MainActivity
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
```

3)在TestIntentActivity中添加onDestroy()方法

```TestIntentActivity
@Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("TestIntentActivity", "onDestroy");
    }
```

并在onCreate()中添加Log调试信息

```TestIntentActivity
Log.d("TestIntentActivity", this.toString());
```

4)

在TestIntentActivity跳转到MainActivity时，由于此时在栈顶的活动是TestIntentActivity，所以TestIntentActivity的onDestroy()函数会被调用，MainActivity被重启，此时MainActivity回到栈顶，避免了创建多个活动的情况发生。

![singleTask.PNG](https://github.com/Rainal14/2018118114_Android/blob/homework/Lab_03/Lab03_pic/singleTask.PNG?raw=true)