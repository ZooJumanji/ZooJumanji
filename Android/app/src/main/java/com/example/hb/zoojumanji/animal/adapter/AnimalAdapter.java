package com.example.hb.zoojumanji.animal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.hb.zoojumanji.R;
import com.example.hb.zoojumanji.animal.Animal;

import java.util.List;


// Adapter for animals displaying list
public class AnimalAdapter extends ArrayAdapter<Animal> {

    // Default constructor
    public AnimalAdapter(Context context, int resource, List<Animal> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Generate specific view
        View view = LayoutInflater.from(getContext()).inflate(R.layout.list_animal_item, null);

        // Get animal from position
        Animal animal = getItem(position);

        // Get multiple elements
        TextView id_text = (TextView) view.findViewById(R.id.animal_id);
        TextView name_text = (TextView) view.findViewById(R.id.animal_name);
        TextView species_text = (TextView) view.findViewById(R.id.animal_species);

        // Insert values
        id_text.setText(String.valueOf(animal.getId()));
        name_text.setText(animal.getName());
        species_text.setText(animal.getSpecies().getStringResource());

        return view;
    }
}
