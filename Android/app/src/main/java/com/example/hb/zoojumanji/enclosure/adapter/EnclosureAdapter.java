package com.example.hb.zoojumanji.enclosure.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.hb.zoojumanji.R;
import com.example.hb.zoojumanji.enclosure.Enclosure;

import java.util.List;

/**
 * Created by hb on 06/06/2016.
 */
// Adapter for animals displaying list
public class EnclosureAdapter extends ArrayAdapter<Enclosure> {

    // Default constructor
    public EnclosureAdapter(Context context, int resource, List<Enclosure> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Generate specific view
        View view = LayoutInflater.from(getContext()).inflate(R.layout.list_enclosure_item, null);

        // Get animal from position
        Enclosure enclosure = getItem(position);

        // Get multiple elements
        TextView id_text = (TextView) view.findViewById(R.id.enclosure_id);
        TextView name_text = (TextView) view.findViewById(R.id.enclosure_name);
        TextView type_text = (TextView) view.findViewById(R.id.enclosure_type);

        // Insert values
        id_text.setText(String.valueOf(enclosure.getId()));
        name_text.setText(enclosure.getName());
        type_text.setText(enclosure.getType().getStringResource());

        return view;
    }
}
