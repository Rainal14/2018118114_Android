package com.example.recyclerviewapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private List<Animal> animalList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initAnimals();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        AnimalAdapter adapter = new AnimalAdapter(animalList);
        recyclerView.setAdapter(adapter);
    }

    private void initAnimals(){
        for (int i = 0; i < 2; i++){
            Animal dog = new Animal(getRandomLengthName("Dog"), R.drawable.dog);
            animalList.add(dog);
            Animal cat = new Animal(getRandomLengthName("Cat"), R.drawable.cat);
            animalList.add(cat);
            Animal ant = new Animal(getRandomLengthName("Ant"), R.drawable.ant);
            animalList.add(ant);
            Animal tiger = new Animal(getRandomLengthName("Tiger"), R.drawable.tiger);
            animalList.add(tiger);
            Animal lion = new Animal(getRandomLengthName("Lion"), R.drawable.lion);
            animalList.add(lion);
            Animal pig = new Animal(getRandomLengthName("Pig"), R.drawable.pig);
            animalList.add(pig);
            Animal bird = new Animal(getRandomLengthName("Bird"), R.drawable.bird);
            animalList.add(bird);
            Animal horse = new Animal(getRandomLengthName("Horse"), R.drawable.horse);
            animalList.add(horse);
            Animal elephant = new Animal(getRandomLengthName("Elephant"), R.drawable.elephant);
            animalList.add(elephant);
            Animal panda = new Animal(getRandomLengthName("Panda"), R.drawable.panda);
            animalList.add(panda);
            Animal duck = new Animal(getRandomLengthName("Duck"), R.drawable.duck);
            animalList.add(duck);
            Animal man = new Animal(getRandomLengthName("Man"), R.drawable.man);
            animalList.add(man);
            Animal girl = new Animal(getRandomLengthName("Girl"), R.drawable.girl);
            animalList.add(girl);
            Animal ultraman = new Animal(getRandomLengthName("Ultraman"), R.drawable.ultraman);
            animalList.add(ultraman);
        }
    }

    private String getRandomLengthName(String name){
        Random random = new Random();
        int length = random.nextInt(20) + 1;
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < length; i++){
            builder.append(name);
        }
        return builder.toString();
    }
}