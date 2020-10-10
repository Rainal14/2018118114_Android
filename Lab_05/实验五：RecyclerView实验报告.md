# 实验五：Recycler View 实验报告

## 实验目的

## 实验过程

### 一、List View

#### 编辑布局文件，添加List View控件

```xml
<ListView
        android:id="@+id/list_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
```

#### 编辑主活动

```java
public class MainActivity extends AppCompatActivity {

    private String[] data = {"Dog", "Cat", "Ant", "Tiger", "Lion", "Pig", "Bird", "Horse", "Elephant", "Panda", "Duck", "Man", "Girl", "Ultraman"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, data);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
    }
}
```

#### 定义实体类

```java
package com.example.recyclerviewapplication;

public class Animal {
    
    private String name;
    private int imageID;
    
    public Animal(String name, int imageID){
        this.name = name;
        this.imageID = imageID;
    }
    
    public String getName(){
        return name;
    }
    
    public int getImageID(){
        return imageID;
    }
}
```

#### 新建自定义布局

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <ImageView
        android:id="@+id/animal_image"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"/>

    <TextView
        android:id="@+id/animal_name"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center_vertical"
        android:layout_marginLeft="10dp"/>

</LinearLayout>
```

#### 创建适配器

```java
public class AnimalAdapter extends ArrayAdapter<Animal> {

    private int resourceId;

    public AnimalAdapter(Context context, int textViewResourceId, List<Animal> objects){
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Animal animal = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        ImageView animalImage = (ImageView) view.findViewById(R.id.animal_image);
        TextView animalName = (TextView) view.findViewById(R.id.animal_name);
        animalImage.setImageResource(animal.getImageID());
        animalName.setText(animal.getName());
        return view;
    }
}
```

#### 修改主活动

```java
public class MainActivity extends AppCompatActivity {

    private List<Animal> animalList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initAnimals();
        AnimalAdapter adapter = new AnimalAdapter(MainActivity.this, R.layout.animal_item, animalList);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
    }

    private void initAnimals(){
        for (int i = 0; i < 2; i++){
            Animal dog = new Animal("Dog", R.drawable.dog);
            animalList.add(dog);
            Animal cat = new Animal("Cat", R.drawable.cat);
            animalList.add(cat);
            Animal ant = new Animal("Ant", R.drawable.ant);
            animalList.add(ant);
            Animal tiger = new Animal("Tiger", R.drawable.tiger);
            animalList.add(tiger);
            Animal lion = new Animal("Lion", R.drawable.lion);
            animalList.add(lion);
            Animal pig = new Animal("Pig", R.drawable.pig);
            animalList.add(pig);
            Animal bird = new Animal("Bird", R.drawable.bird);
            animalList.add(bird);
            Animal horse = new Animal("Horse", R.drawable.horse);
            animalList.add(horse);
            Animal elephant = new Animal("Elephant", R.drawable.elephant);
            animalList.add(elephant);
            Animal panda = new Animal("Panda", R.drawable.panda);
            animalList.add(panda);
            Animal duck = new Animal("Duck", R.drawable.duck);
            animalList.add(duck);
            Animal man = new Animal("Man", R.drawable.man);
            animalList.add(man);
            Animal girl = new Animal("Girl", R.drawable.girl);
            animalList.add(girl);
            Animal ultraman = new Animal("Ultraman", R.drawable.ultraman);
            animalList.add(ultraman);
        }
    }
}
```

运行截图如下

【run01】

图片分辨率过大遮挡文字，导致动物名字无法正常显示。

修改自定义布局文件里的图片显示控件，将宽度和高度固定。

```xml
<ImageView
        android:id="@+id/animal_image"
        android:layout_width="60dp"
        android:layout_height="60dp"/>
```

问题解决，图片和名字正常显示：

【run02】

### 二、Recycler View



## 总结



添加依赖：

```gradle
implementation 'androidx.recyclerview:recyclerview:1.0.0'
```



新建控件：

```xml
<androidx.recyclerview.widget.RecyclerView
        android:id="@+id/recycler_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        />
```



定义适配器

```java
public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.ViewHolder> {

    private List<Animal> mAnimalList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView animalImage;
        TextView animalName;

        public ViewHolder(View view){
            super(view);
            animalImage = (ImageView) view.findViewById(R.id.animal_image);
            animalName = (TextView) view.findViewById(R.id.animal_name);
        }
    }

    public  AnimalAdapter(List<Animal> animalList){
        mAnimalList = animalList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.animal_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Animal animal = mAnimalList.get(position);
        holder.animalImage.setImageResource(animal.getImageID());
        holder.animalName.setText(animal.getName());
    }

    @Override
    public int getItemCount() {
        return mAnimalList.size();
    }
}
```



修改主活动

```java
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initAnimals();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        AnimalAdapter adapter = new AnimalAdapter(animalList);
        recyclerView.setAdapter(adapter);
    }
```



运行效果如下

【recyrun01】

文字被遮挡

修改图片控件大小

```xml
<ImageView
        android:id="@+id/animal_image"
        android:layout_width="50dp"
        android:layout_height="50dp"/>
```

效果如下

【rerun02】

问题未解决

将主容器宽度和高度修改为包裹文本

```xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content">
</LinearLayout>
```

问题解决

【pic_recyclerview_run03.PNG】