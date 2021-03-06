package com.example.hb.zoojumanji.enclosure.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.example.hb.zoojumanji.R;
import com.example.hb.zoojumanji.enclosure.Enclosure;
import com.example.hb.zoojumanji.enclosure.manager.EnclosureManager;

public class EnclosureDetailActivity extends AppCompatActivity {

    protected EnclosureManager manager;

    protected TextView nameText;
    protected TextView countText;
    protected TextView typeText;

    protected Enclosure enclosure;
    protected int defaultTextColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enclosure_detail);

        manager = new EnclosureManager(getApplicationContext());

        // Initialize TextView
        nameText = (TextView) findViewById(R.id.detail_enclosure_name);
        countText = (TextView) findViewById(R.id.detail_enclosure_count);
        typeText = (TextView) findViewById(R.id.detail_enclosure_type);

        defaultTextColor = countText.getCurrentTextColor();

        // Get Enclosure from id
        Intent intent = getIntent();
        enclosure = manager.getEnclosure(intent.getIntExtra("id", 0));

        showEnclosureDetails();
        generateButtonsListener();
    }

    private void generateButtonsListener() {
        // Get clicked floatingButton
        FloatingActionButton deletionButton = (FloatingActionButton) findViewById(R.id.delete_fab);
        FloatingActionButton modifyButton = (FloatingActionButton) findViewById(R.id.modify_fab);

        deletionButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                deletionExecution();

            }
        });

        modifyButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                // Send to detail page with id in argument
                Intent intent = new Intent(EnclosureDetailActivity.this, EnclosureModifyActivity.class);
                intent.putExtra("id", enclosure.getId());

                startActivity(intent);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        showEnclosureDetails();
    }

    private void showEnclosureDetails() {
        // Display parameters
        nameText.setText(enclosure.getName());
        countText.setText(String.valueOf(enclosure.getAnimalsCount())+"/"+String.valueOf
                (enclosure.getMax()));
        typeText.setText(enclosure.getType().getStringResource());

        // Show animal count in red if enclosure's full
        if (enclosure.getAnimalsCount() >= enclosure.getMax()) {
            countText.setTextColor(Color.RED);
        }
        else {
            countText.setTextColor(defaultTextColor);
        }
    }

    private void deletionExecution() {
        manager.deleteEnclosure(enclosure);
        finish();
    }
}
