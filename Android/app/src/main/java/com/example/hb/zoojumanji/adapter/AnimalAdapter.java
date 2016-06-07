package com.example.hb.zoojumanji.adapter;

import android.content.ClipData.Item;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.hb.zoojumanji.R;
import com.example.hb.zoojumanji.object.Animal;

import java.util.List;

/**
 * Created by hb on 06/06/2016.
 */
// Adapter for animals displaying list
public class AnimalAdapter extends ArrayAdapter<Animal> {

    // Default constructor
    public AnimalAdapter(Context context, int resource, int textViewResourceId, List<Animal> objects) {
        super(context, resource, textViewResourceId, objects);
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

        // Insert values
        id_text.setText(String.valueOf(animal.getId()));
        name_text.setText(animal.getName());

        return view;
    }
}
