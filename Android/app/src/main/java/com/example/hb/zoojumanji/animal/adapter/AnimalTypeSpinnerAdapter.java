package com.example.hb.zoojumanji.animal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.hb.zoojumanji.R;
import com.example.hb.zoojumanji.animal.AnimalSpeciesType;
import com.example.hb.zoojumanji.animal.AnimalType;

import java.util.List;

/**
 * Created by jonas on 08/06/2016.
 */
public class AnimalTypeSpinnerAdapter extends ArrayAdapter<AnimalType>
{

    public AnimalTypeSpinnerAdapter(Context context, int resource, List<AnimalType> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Generate specific view
        return inflateStringResourceView(position);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return inflateStringResourceView(position);
    }

    private View inflateStringResourceView(int position) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.list_animal_sex_item, null);

        // Get animal from position
        AnimalType type = getItem(position);

        // Get multiple elements
        TextView name_text = (TextView) view.findViewById(R.id.animal_sex_name);

        // Insert values
        name_text.setText(type.getStringResource());

        return view;
    }
}
