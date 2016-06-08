package com.example.hb.zoojumanji.ticket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.hb.zoojumanji.R;
import com.example.hb.zoojumanji.ticket.adapter.ceil.TicketDateCeil;
import com.example.hb.zoojumanji.ticket.Ticket;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


// Adapter for tickets displaying list classed by type
public class TicketDateAdapter extends ArrayAdapter<Ticket> {

    // Default constructor
    public TicketDateAdapter(Context context, int resource, int textViewResourceId, List<Ticket> tickets) {

        super(context, resource, textViewResourceId);

        // Sort listing by date
        Collections.sort(tickets, new Comparator<Ticket>() {
            @Override
            public int compare(Ticket ceil_1, Ticket ceil_2) {
                return ceil_1.getDate().compareTo(ceil_2.getDate());
            }
        });

        this.addAll(tickets);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Generate specific view
        View view = LayoutInflater.from(getContext()).inflate(R.layout.list_ticket_date_item, null);

        // Get animal from position
        Ticket ticket = getItem(position);

        // Get multiple elements
        TextView id_text = (TextView) view.findViewById(R.id.ticket_id);
        TextView date_text = (TextView) view.findViewById(R.id.ticket_ceil_date);
        TextView quantity_text = (TextView) view.findViewById(R.id.ticket_ceil_quantity);

        // Insert values
        id_text.setText(String.valueOf(ticket.getId()));
        date_text.setText(ticket.getDateString());
        quantity_text.setText(String.valueOf(ticket.getQuantity()));

        return view;
    }
}
