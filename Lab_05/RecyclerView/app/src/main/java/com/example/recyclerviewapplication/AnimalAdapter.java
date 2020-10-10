package com.example.recyclerviewapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

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
