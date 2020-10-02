# Android开发第三次实验报告

## 实验目的

掌握四种活动启动模式的使用和原理。

## 实验过程

### 1、standard启动模式

1）在MainActivity（活动一）的布局文件中添加测试按钮。

```main_layout.xml
<Button
        android:id="@+id/button_standard"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="启动模式：standard" />
```

在MainActivity（活动一）的onCreate()中添加按钮引用。

```MainActivity
Button button7 = findViewById(R.id.button_standard);
```



2）设置监听器，创建Intent，在MainActivity（活动一）的基础上启动MainActivity（活动一）。

```MainActivity
button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }
});
```



3）启动安卓虚拟设备，开始测试：点击两次“启动模式：standard”按钮。

![standard_device.PNG](https://github.com/Rainal14/2018118114_Android/blob/homework/Lab_03/Lab03_pic/standard_device.PNG?raw=true)



![standard.PNG](https://github.com/Rainal14/2018118114_Android/blob/homework/Lab_03/Lab03_pic/standard.PNG?raw=true)



从测试信息看出，默认的启动模式standard每一次都会创建一个新的活动实例。



### 2、singleTop启动模式

1）在AndroidManifest.xml中修改MainActivity（活动一）的启动模式为singleTop

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



2）启动虚拟机，多次点击按钮，logcat不会出现新的打印信息。

![singleTop.PNG](https://github.com/Rainal14/2018118114_Android/blob/homework/Lab_03/Lab03_pic/singleTop.PNG?raw=true)



3）在TestIntentActivity（活动二）中添加通过显式intend跳转到MainActivity（活动一）的按钮。

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



4）在MainActivity（活动一）通过显式intent跳转到TestIntentActivity（活动二），然后通过显式intent返回MainActivity（活动一），此时由于之前的MainActivity（活动一）不在栈顶，所以新的MainActivity（活动一）实例会被创建。

![singleTop2.PNG](https://github.com/Rainal14/2018118114_Android/blob/homework/Lab_03/Lab03_pic/singleTop2.PNG?raw=true)



### 3、singleTask启动模式

1）在AndroidManifest.xml中修改MainActivity（活动一）的启动模式为singleTask。

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



2）在MainActivity（活动一）中添加onRestart()方法。

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



3）在TestIntentActivity（活动二）中添加onDestroy()方法。

```TestIntentActivity
@Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("TestIntentActivity", "onDestroy");
    }
```

并在onCreate()中添加Log调试信息。

```TestIntentActivity
Log.d("TestIntentActivity", this.toString());
```



4）在TestIntentActivity（活动二）跳转到MainActivity（活动一）时，由于此时在栈顶的活动是TestIntentActivity（活动二），所以TestIntentActivity（活动二）的onDestroy()函数会被调用，MainActivity（活动一）被重启，此时MainActivity（活动一）回到栈顶，避免了同时创建多个相同活动的情况发生。

![singleTask.PNG](https://github.com/Rainal14/2018118114_Android/blob/homework/Lab_03/Lab03_pic/singleTask.PNG?raw=true)



### 4、singleInstance启动方式

1）在AndroidManifest.xml中修改TestIntentActivity（活动二）的启动模式为singleInstance。

```AndroidManifest.xml
<activity
            android:name=".TestIntentActivity"
            android:launchMode="singleInstance"
            android:label="显式与隐式Intent测试">
            <intent-filter>
                <action android:name="com.example.activitytest.ACTION_START" />

                <category android:name="android.intent.category.DEFAULT" />
                <category android:name="com.example.activitytest.MY_CATEGORY" />
            </intent-filter>
        </activity>
```



2）修改MainActivity（活动一）和TestIntentActivity（活动二）的onCreate()方法中的Log信息。

```MainActivity
Log.d("MainActivity", "栈id为：" + getTaskId());  //测试启动模式
```

```TestIntentActivity
Log.d("TestIntentActivity", "栈id为：" + getTaskId()); //测试启动模式
```



3）TestIntentActivity（活动二）的布局文件中添加按钮，并在TestIntentActivity（活动二）中创建按钮引用和监听器，实现点击时通过显式intent跳转到ImplicitIntent（活动三）的功能。

```activity_test_intent.xml
<Button
        android:id="@+id/button_toImplicitIntent"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="显式intent跳转到ImplicitIntent活动" />
```

```TestIntentActivity
Button button6 = findViewById(R.id.button_toImplicitIntent);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TestIntentActivity.this, ImplicitIntent.class);
                startActivity(intent);
            }
        });
```



4）修改ImplicitActivity（活动三）的onCreate()方法代码，添加Log信息。

```ImplicitIntent
public class ImplicitIntent extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("ImplicitIntent", "栈id为：" + getTaskId());
        setContentView(R.layout.activity_implicit_intent);
    }
}
```



5）打开应用（创建MainActivity即活动一）时输出的栈id为72，此时通过显式intent跳转到TestIntentActivity（活动二），输出的栈id为73，在TestIntentActivity（活动二）里跳转到ImplicitActivity（活动三）时输出的栈id为72。由此可见TestIntentActivity（活动二）和MainActivity（活动一）、ImplicitActivity（活动三）位于不同的栈里。TestIntentActivity（活动二）创建了新的返回栈以实现这个活动的实例可以和其他应用程序共享。

![singleInstance.PNG](https://github.com/Rainal14/2018118114_Android/blob/homework/Lab_03/Lab03_pic/singleInstance.PNG?raw=true)



## 总结

默认的启动模式standard每一次启动活动都会创建新的活动实例。

singleTop启动模式通过判断启动的活动是否在栈顶来决定是否创建新的活动实例，若在栈顶则跳转到栈顶已经创建的活动实例，若不在栈顶则创建新的活动实例。

singleTask启动模式通过调用onDestroy()和onRestart()方法销毁栈顶的其他活动实例，并且重启已经存在的实例。

singleInstance启动模式通过创建新的返回栈并存放新启动的活动实例，以实现与程序返回栈的分离，和其他程序实现该活动实例的共享。