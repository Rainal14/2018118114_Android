package com.example.recyclerviewapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Animal> animalList = new ArrayList<>();

    @Override
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