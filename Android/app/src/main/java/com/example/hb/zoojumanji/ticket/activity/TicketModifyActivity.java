package com.example.hb.zoojumanji.ticket.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.hb.zoojumanji.R;
import com.example.hb.zoojumanji.ticket.Ticket;
import com.example.hb.zoojumanji.ticket.TicketType;
import com.example.hb.zoojumanji.ticket.adapter.TicketTypeSpinnerAdapter;
import com.example.hb.zoojumanji.ticket.manager.TicketManager;

import java.util.Arrays;

public class TicketModifyActivity extends AppCompatActivity {

    protected Spinner typeSpinner;
    protected EditText priceText;
    protected EditText quantityText;

    protected Ticket ticket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_modify);

        // Get Enclosure from id
        Intent intent = getIntent();
        ticket = TicketManager.getTicket(intent.getIntExtra("id", 0));

        showTicketDetails();
        generateButtonsListener();
    }

    private void generateButtonsListener() {
        // Get clicked floatingButton
        FloatingActionButton saveButton = (FloatingActionButton) findViewById(R.id.save_fab);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Update enclosure
                TicketManager.modifyTicket(ticket.getId(),
                        (TicketType) typeSpinner.getSelectedItem(),
                        Double.valueOf(priceText.getText().toString()),
                        Integer.valueOf(quantityText.getText().toString()));

                TicketModifyActivity.this.finish();

            }
        });
    }

    private void showTicketDetails() {
        // get TextView
        priceText = (EditText) findViewById(R.id.edit_ticket_price);
        quantityText = (EditText) findViewById(R.id.edit_ticket_quantity);
        typeSpinner = (Spinner) findViewById(R.id.edit_ticket_type);

        priceText.setText(String.valueOf(ticket.getPrice()));
        quantityText.setText(String.valueOf(ticket.getQuantity()));

        ArrayAdapter<TicketType> adapter = new TicketTypeSpinnerAdapter(this,
                R.layout.spinner_ticket_param_item,
                Arrays.asList(TicketType.values()));

        typeSpinner.setAdapter(adapter);
        typeSpinner.setSelection(adapter.getPosition(ticket.getType()));
    }
}
