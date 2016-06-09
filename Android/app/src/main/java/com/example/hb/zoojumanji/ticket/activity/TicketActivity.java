package com.example.hb.zoojumanji.ticket.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hb.zoojumanji.R;
import com.example.hb.zoojumanji.ticket.TicketType;
import com.example.hb.zoojumanji.ticket.adapter.TicketTypeAdapter;
import com.example.hb.zoojumanji.ticket.adapter.ceil.TicketTypeCeil;
import com.example.hb.zoojumanji.ticket.manager.TicketManager;
import com.example.hb.zoojumanji.ticket.Ticket;

import java.util.List;

public class TicketActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);

        // Get list of tickets
        List<Ticket> list = TicketManager.getTickets();

        // Generate specific adapter
        ArrayAdapter<TicketTypeCeil> adapter = new TicketTypeAdapter(this,
                R.layout.list_ticket_type_item, R.id.ticket_id, list);

        // Display list
        ListView listView = (ListView) findViewById(R.id.tickets_list);
        listView.setAdapter(adapter);

        // Add event listener on elements of list
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Send to detail page with id in argument
                Intent intent = new Intent(TicketActivity.this, TicketTypeActivity.class);
                TextView id_text = (TextView) view.findViewById(R.id.ticket_type_id);
                intent.putExtra("type", Enum.valueOf(TicketType.class, String.valueOf(id_text
                        .getText())));

                startActivity(intent);
            }
        });
    }
}
