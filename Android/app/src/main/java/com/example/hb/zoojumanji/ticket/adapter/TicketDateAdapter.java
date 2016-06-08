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
public class TicketDateAdapter extends ArrayAdapter<TicketDateCeil> {

    // Default constructor
    public TicketDateAdapter(Context context, int resource, int textViewResourceId, List<Ticket> tickets) {

        super(context, resource, textViewResourceId);

        Map<Date, TicketDateCeil> map = new HashMap<>();
        for (Ticket ticket : tickets) {
            if (!map.containsKey(ticket.getDate())) {
                map.put(ticket.getDate(), new TicketDateCeil(ticket.getType(), ticket.getDate()));
            }
            TicketDateCeil ceil = map.get(ticket.getDate());
            ceil.addTicket(ticket);
        }

        List<TicketDateCeil> list = new ArrayList<>();
        for (TicketDateCeil ticketTypeCeil : map.values()) {
            list.add(ticketTypeCeil);
        }

        // Sort listing by date
        Collections.sort(list, new Comparator<TicketDateCeil>() {
            @Override
            public int compare(TicketDateCeil ceil_1, TicketDateCeil ceil_2) {
                return ceil_1.getDate().compareTo(ceil_2.getDate());
            }
        });

        this.addAll(list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Generate specific view
        View view = LayoutInflater.from(getContext()).inflate(R.layout.list_ticket_date_item, null);

        // Get animal from position
        TicketDateCeil ticketTypeCeil = getItem(position);

        // Get multiple elements
        TextView date_text = (TextView) view.findViewById(R.id.ticket_ceil_date);
        TextView type_text = (TextView) view.findViewById(R.id.ticket_ceil_type);
        TextView count_text = (TextView) view.findViewById(R.id.ticket_ceil_count);

        // Insert values
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        date_text.setText(df.format(ticketTypeCeil.getDate()));
        type_text.setText(ticketTypeCeil.getType());
        count_text.setText(String.valueOf(ticketTypeCeil.getTicketsCount()));

        return view;
    }
}
