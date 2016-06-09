package com.example.hb.zoojumanji.enclosure.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hb.zoojumanji.R;
import com.example.hb.zoojumanji.enclosure.manager.EnclosureManager;
import com.example.hb.zoojumanji.enclosure.Enclosure;

public class EnclosureDetailActivity extends AppCompatActivity {

    protected TextView nameText;
    protected TextView countText;
    protected TextView typeText;

    protected Enclosure enclosure;
    protected Boolean deletion = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enclosure_detail);

        // Initialize TextView
        nameText = (TextView) findViewById(R.id.detail_enclosure_name);
        countText = (TextView) findViewById(R.id.detail_enclosure_count);
        typeText = (TextView) findViewById(R.id.detail_enclosure_type);

        // Get Animal from id
        Intent intent = getIntent();
        enclosure = EnclosureManager.getEnclosure(intent.getIntExtra("id", 0));
        showEnclosureDetails();

        // Get clicked floatingButton
        FloatingActionButton button = (FloatingActionButton) findViewById(R.id.delete_fab);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Displays a snackbar with a red UNDO action
                generateSnackBar();

            }
        });
    }

    private void showEnclosureDetails() {
        // Display parameters
        nameText.setText(enclosure.getName());
        countText.setText(String.valueOf(enclosure.getAnimalsCount())+"/"+String.valueOf(enclosure.getMax()));
        typeText.setText(enclosure.getType().getStringResource());

        // Show animal count in red if enclosure's full
        if (enclosure.getAnimalsCount() == enclosure.getMax()) {
            countText.setTextColor(Color.RED);
        }
    }

    private void generateSnackBar() {

        // Activation deletion
        deletion = true;

        //The undo action that could be called by the following snackbar
        final OnClickListener undoListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abort deletion
                deletion = false;
                Toast.makeText(EnclosureDetailActivity.this, R.string.message_undo_deletion, Toast.LENGTH_LONG)
                        .show();
            }
        };

        // Generate snackbar
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                R.string.message_stock_deletion,
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
                    deletionExecution();
                }
            }
        });

        // Display snackbar
        snackbar.setAction("Undo", undoListener)
                .setActionTextColor(0xFFFF0000)
                .show();
    }

    private void deletionExecution() {
        EnclosureManager.deleteEnclosure(enclosure);
        finish();
    }
}
