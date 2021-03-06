package com.example.hb.zoojumanji.ticket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.hb.zoojumanji.R;
import com.example.hb.zoojumanji.ticket.TicketType;
import com.example.hb.zoojumanji.ticket.adapter.ceil.TicketTypeCeil;
import com.example.hb.zoojumanji.ticket.Ticket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


// Adapter for tickets displaying list classed by type
public class TicketTypeAdapter extends ArrayAdapter<TicketTypeCeil> {

    // Default constructor
    public TicketTypeAdapter(Context context, int resource, List<Ticket> tickets) {

        super(context, resource);

        Map<TicketType, TicketTypeCeil> map = new HashMap<>();
        for (Ticket ticket : tickets) {
            if (!map.containsKey(ticket.getType())) {
                map.put(ticket.getType(), new TicketTypeCeil(ticket.getType()));
            }
            TicketTypeCeil ceil = map.get(ticket.getType());
            ceil.addTicket(ticket);
        }

        List<TicketTypeCeil> list = new ArrayList<>();
        for (TicketTypeCeil ticketTypeCeil : map.values()) {
            list.add(ticketTypeCeil);
        }

        this.addAll(list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Generate specific view
        View view = LayoutInflater.from(getContext()).inflate(R.layout.list_ticket_type_item, null);

        // Get animal from position
        TicketTypeCeil ticketTypeCeil = getItem(position);

        // Get multiple elements
        TextView id_text = (TextView) view.findViewById(R.id.ticket_type_id);
        TextView type_text = (TextView) view.findViewById(R.id.ticket_ceil_type);
        TextView count_text = (TextView) view.findViewById(R.id.ticket_ceil_count);

        // Insert values
        id_text.setText(String.valueOf(ticketTypeCeil.getType()));
        type_text.setText(ticketTypeCeil.getType().getStringResource());
        count_text.setText(String.valueOf(ticketTypeCeil.getTicketsCount()));

        return view;
    }
}
