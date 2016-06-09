package com.example.hb.zoojumanji.ticket.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hb.zoojumanji.R;
import com.example.hb.zoojumanji.ticket.TicketType;
import com.example.hb.zoojumanji.ticket.adapter.TicketDateAdapter;
import com.example.hb.zoojumanji.ticket.manager.TicketManager;
import com.example.hb.zoojumanji.ticket.Ticket;

import java.util.List;

public class TicketTypeActivity extends AppCompatActivity {

    TicketType ticketType;
    TextView subTitle;

    ListView listView;

    protected boolean deletion = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_type);

        Intent intent = getIntent();
        ticketType = (TicketType) intent.getExtras().get("type");

        subTitle = (TextView) findViewById(R.id.sub_title_text);
        subTitle.setText(ticketType.getStringResource());

        generateList();
        generateButtonsListener();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        generateList();
        if (TicketManager.isInDeletion()) {
            generateUndeleteSnackBar();
        }
    }

    private void generateButtonsListener() {
        // Get clicked floatingButton
        FloatingActionButton button = (FloatingActionButton) findViewById(R.id.add_fab);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Send to detail page with id in argument
                Intent intent = new Intent(TicketTypeActivity.this, TicketCreationActivity
                        .class);
                intent.putExtra("type", ticketType);

                startActivity(intent);
            }
        });
    }

    private void generateList() {
        // Get list of tickets
        List<Ticket> list = TicketManager.getTickets(ticketType);

        // Generate specific adapter
        ArrayAdapter<Ticket> adapter = new TicketDateAdapter(this,
                R.layout.list_ticket_date_item, R.id.ticket_id, list);

        // Display list
        listView = (ListView) findViewById(R.id.tickets_list);
        listView.setAdapter(adapter);

        // Add event listener on elements of list
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Send to detail page with id in argument
                Intent intent = new Intent(TicketTypeActivity.this, TicketDetailActivity.class);
                TextView id_text = (TextView) view.findViewById(R.id.ticket_id);
                intent.putExtra("id", Integer.valueOf(String.valueOf(id_text.getText())));

                startActivity(intent);
            }
        });
    }
    private void generateUndeleteSnackBar() {

        // Activation deletion
        deletion = true;

        //The undo action that could be called by the following snackbar
        final View.OnClickListener undoListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abort deletion
                deletion = false;
                Toast.makeText(TicketTypeActivity.this, R.string.message_undo_deletion, Toast
                        .LENGTH_LONG)
                        .show();
                TicketManager.restoreTicket();
                generateList();
            }
        };

        // Generate snackbar
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                R.string.message_ticket_deletion,
                Snackbar.LENGTH_LONG);

        // Generate snackbar events
        snackbar.getView().addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {
                // Empty
            }

            @Override
            public void onViewDetachedFromWindow(View v) {
                if (deletion) {
                    TicketManager.cleanTicket();
                }
            }
        });

        // Display snackbar
        snackbar.setAction(R.string.message_deletion_undo, undoListener)
                .setActionTextColor(0xFFFF0000)
                .show();
    }
}
