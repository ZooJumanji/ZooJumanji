package com.example.hb.zoojumanji;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hb.zoojumanji.adapter.TicketTypeAdapter;
import com.example.hb.zoojumanji.adapter.ceil.TicketTypeCeil;
import com.example.hb.zoojumanji.dataManager.TicketManager;
import com.example.hb.zoojumanji.object.Ticket;

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

                /*
                // Send to detail page with id in argument
                Intent intent = new Intent(TicketActivity.this, EnclosureDetailActivity.class);
                TextView id_text = (TextView) view.findViewById(R.id.enclosure_id);
                intent.putExtra("id", Integer.valueOf(String.valueOf(id_text.getText())));

                startActivity(intent);
                //*/
                Toast.makeText(TicketActivity.this, "Click", Toast.LENGTH_LONG).show();
            }
        });
    }
}
