package com.example.hb.zoojumanji.enclosure.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.hb.zoojumanji.R;
import com.example.hb.zoojumanji.enclosure.EnclosureType;

import java.util.List;

/**
 * Created by hb on 08/06/2016.
 */
public class EnclosureTypeSpinnerAdapter extends ArrayAdapter<EnclosureType> {

    public EnclosureTypeSpinnerAdapter(Context context, int resource, List<EnclosureType> objects) {
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
        View view = LayoutInflater.from(getContext()).inflate(R.layout.list_enclosure_type_item, null);

        // Get animal from position
        EnclosureType type = getItem(position);

        // Get multiple elements
        TextView name_text = (TextView) view.findViewById(R.id.enclosure_type_name);

        // Insert values
        name_text.setText(type.getStringResource());

        return view;
    }
}
