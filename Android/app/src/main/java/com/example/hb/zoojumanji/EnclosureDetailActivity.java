package com.example.hb.zoojumanji;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hb.zoojumanji.dataManager.DataManager;
import com.example.hb.zoojumanji.object.Animal;
import com.example.hb.zoojumanji.object.Enclosure;

public class EnclosureDetailActivity extends AppCompatActivity {

    protected TextView nameText;
    protected TextView maxText;
    protected TextView typeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enclosure_detail);

        // Initialize TextView
        nameText = (TextView) findViewById(R.id.detail_enclosure_name);
        maxText = (TextView) findViewById(R.id.detail_enclosure_max);
        typeText = (TextView) findViewById(R.id.detail_enclosure_type);

        // Get Animal from id
        Intent intent = getIntent();
        Enclosure enclosure = DataManager.getEnclosure(intent.getIntExtra("id", 0));

        // Display parameters
        nameText.setText(enclosure.getName());
        maxText.setText(String.valueOf(enclosure.getMax()));
        typeText.setText(getString(enclosure.getType()));

        // Get clicked floatingButton
        FloatingActionButton button = (FloatingActionButton) findViewById(R.id.delete_fab);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Toast deletion
                Toast.makeText(EnclosureDetailActivity.this, "Enclosure deleted", Toast.LENGTH_LONG)
                        .show();
            }
        });
    }
}
