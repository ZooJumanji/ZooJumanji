package com.example.hb.zoojumanji.ticket.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.hb.zoojumanji.R;
import com.example.hb.zoojumanji.ticket.TicketType;
import com.example.hb.zoojumanji.ticket.adapter.TicketTypeSpinnerAdapter;
import com.example.hb.zoojumanji.ticket.manager.TicketManager;

import java.util.Arrays;

public class TicketCreationActivity extends AppCompatActivity {

    protected Spinner typeSpinner;
    protected EditText priceText;
    protected EditText quantityText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_creation);

        showTicketDetails();
        generateButtonListener();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        showTicketDetails();
    }

    private void generateButtonListener() {
        // Get clicked floatingButton
        FloatingActionButton button = (FloatingActionButton) findViewById(R.id.save_fab);
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    try {
                        TicketManager.createTicket((TicketType) typeSpinner.getSelectedItem(),
                                Double.valueOf(priceText.getText().toString()),
                                Integer.valueOf(quantityText.getText().toString()));

                        TicketCreationActivity.this.finish();
                    }
                    catch (RuntimeException e) {
                        Toast.makeText(TicketCreationActivity.this,
                                R.string.exception_formular_error,
                                Toast.LENGTH_LONG)
                                .show();
                    }
                }
            });
        }
    }

    private void showTicketDetails() {
        // get TextView
        priceText = (EditText) findViewById(R.id.edit_ticket_price);
        quantityText = (EditText) findViewById(R.id.edit_ticket_quantity);
        typeSpinner = (Spinner) findViewById(R.id.edit_ticket_type);

        ArrayAdapter<TicketType> adapter = new TicketTypeSpinnerAdapter(this,
                R.layout.spinner_ticket_param_item,
                Arrays.asList(TicketType.values()));

        typeSpinner.setAdapter(adapter);

        Intent intent = getIntent();

        if (intent != null && intent.getExtras() != null && intent.getExtras().containsKey
                ("type")) {
            TicketType type = (TicketType) intent.getExtras().get("type");
            typeSpinner.setSelection(adapter.getPosition(type));
        }
    }
}
