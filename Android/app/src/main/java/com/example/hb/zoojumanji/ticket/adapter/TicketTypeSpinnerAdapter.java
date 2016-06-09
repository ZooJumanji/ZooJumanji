package com.example.hb.zoojumanji.ticket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.hb.zoojumanji.R;
import com.example.hb.zoojumanji.ticket.TicketType;

import java.util.List;

/**
 * Created by hb on 08/06/2016.
 */
public class TicketTypeSpinnerAdapter extends ArrayAdapter<TicketType> {

    public TicketTypeSpinnerAdapter(Context context, int resource, List<TicketType> objects) {
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
        View view = LayoutInflater.from(getContext()).inflate(R.layout.spinner_ticket_type_item,
                null);

        // Get animal from position
        TicketType type = getItem(position);

        // Get multiple elements
        TextView name_text = (TextView) view.findViewById(R.id.ticket_type_name);

        // Insert values
        name_text.setText(type.getStringResource());

        return view;
    }
}
