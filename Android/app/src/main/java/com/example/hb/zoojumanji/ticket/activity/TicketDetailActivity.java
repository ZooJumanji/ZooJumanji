package com.example.hb.zoojumanji.ticket.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hb.zoojumanji.R;
import com.example.hb.zoojumanji.ticket.Ticket;
import com.example.hb.zoojumanji.ticket.manager.TicketManager;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class TicketDetailActivity extends AppCompatActivity {

    protected TextView typeText;
    protected TextView priceText;
    protected TextView quantityText;
    protected TextView dateText;

    protected Ticket ticket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_detail);

        // Initialize TextView
        typeText = (TextView) findViewById(R.id.detail_ticket_type);
        priceText = (TextView) findViewById(R.id.detail_ticket_price);
        quantityText = (TextView) findViewById(R.id.detail_ticket_quantity);
        dateText = (TextView) findViewById(R.id.detail_ticket_date);

        // Get Animal from id
        Intent intent = getIntent();
        ticket = TicketManager.getTicket(intent.getIntExtra("id", 0));

        showTicketDetail();
        generateButtonsListener();
    }

    private void generateButtonsListener() {
        // Get clicked floatingButton
        FloatingActionButton deletionButton = (FloatingActionButton) findViewById(R.id.delete_fab);

        deletionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Displays a snackbar with a red UNDO action
                deletionExecution();

            }
        });
    }

    private void showTicketDetail() {
        // Formatting price
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator(' ');
        symbols.setDecimalSeparator(',');

        DecimalFormat decimalFormat = new DecimalFormat("#,###.00 €", symbols);
        String formattedPrice = decimalFormat.format(ticket.getPrice());

        // Display parameters
        typeText.setText(ticket.getType().getStringResource());
        priceText.setText(formattedPrice);
        quantityText.setText(String.valueOf(ticket.getQuantity()));
        dateText.setText(ticket.getDateString());

        // Get clicked floatingButton
        FloatingActionButton button = (FloatingActionButton) findViewById(R.id.delete_fab);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                deletionExecution();
            }
        });
    }

    private void deletionExecution() {
        TicketManager.deleteTicket(ticket);
        finish();
    }
}
