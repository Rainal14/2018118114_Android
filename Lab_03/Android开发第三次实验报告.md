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

```xml
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