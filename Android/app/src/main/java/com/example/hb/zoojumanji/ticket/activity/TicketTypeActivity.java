package com.example.hb.zoojumanji.ticket.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hb.zoojumanji.R;
import com.example.hb.zoojumanji.ticket.adapter.TicketDateAdapter;
import com.example.hb.zoojumanji.ticket.adapter.ceil.TicketDateCeil;
import com.example.hb.zoojumanji.ticket.manager.TicketManager;
import com.example.hb.zoojumanji.ticket.Ticket;

import java.util.List;

public class TicketTypeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_type);

        Intent intent = getIntent();
        int ticket_type_id = intent.getIntExtra("id", 0);

        // Get list of tickets
        List<Ticket> list = TicketManager.getTickets(ticket_type_id);

        // Generate specific adapter
        ArrayAdapter<TicketDateCeil> adapter = new TicketDateAdapter(this,
                R.layout.list_ticket_date_item, R.id.ticket_id, list);

        // Display list
        ListView listView = (ListView) findViewById(R.id.tickets_list);
        listView.setAdapter(adapter);

        // Add event listener on elements of list
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                /*
                // Send to detail page with id in argument
                Intent intent = new Intent(TicketTypeActivity.this, TicketTypeActivity.class);
                TextView id_text = (TextView) view.findViewById(R.id.ticket_id);
                intent.putExtra("id", Integer.valueOf(String.valueOf(id_text.getText())));

                startActivity(intent);
                //*/
                Toast.makeText(TicketTypeActivity.this, "Click", Toast.LENGTH_LONG).show();
            }
        });
    }
}
