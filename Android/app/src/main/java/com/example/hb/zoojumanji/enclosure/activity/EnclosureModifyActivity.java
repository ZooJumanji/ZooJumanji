package com.example.hb.zoojumanji.enclosure.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.hb.zoojumanji.R;
import com.example.hb.zoojumanji.enclosure.Enclosure;
import com.example.hb.zoojumanji.enclosure.EnclosureType;
import com.example.hb.zoojumanji.enclosure.adapter.EnclosureTypeSpinnerAdapter;
import com.example.hb.zoojumanji.enclosure.manager.EnclosureManager;

import java.util.Arrays;

public class EnclosureModifyActivity extends AppCompatActivity {

    protected EditText nameText;
    protected EditText maxText;
    protected Spinner typeSpinner;

    protected Enclosure enclosure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enclosure_modify);

        // Get Enclosure from id
        Intent intent = getIntent();
        enclosure = EnclosureManager.getEnclosure(intent.getIntExtra("id", 0));

        showEnclosureDetails();
        generateButtonsListener();
    }

    private void generateButtonsListener() {
        // Get clicked floatingButton
        FloatingActionButton saveButton = (FloatingActionButton) findViewById(R.id.save_fab);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Update enclosure
                EnclosureManager.modify(enclosure.getId(),
                        nameText.getText().toString(),
                        Integer.valueOf(maxText.getText().toString()),
                        (EnclosureType) typeSpinner.getSelectedItem());

                EnclosureModifyActivity.this.finish();

            }
        });
    }

    private void showEnclosureDetails() {
        // get TextView
        nameText = (EditText) findViewById(R.id.edit_enclosure_name);
        maxText = (EditText) findViewById(R.id.edit_enclosure_max);
        typeSpinner = (Spinner) findViewById(R.id.edit_enclosure_type);

        nameText.setText(enclosure.getName());
        maxText.setText(String.valueOf(enclosure.getMax()));

        ArrayAdapter<EnclosureType> adapter = new EnclosureTypeSpinnerAdapter(this,
                R.layout.spinner_enclosure_param_item,
                Arrays.asList(EnclosureType.values()));

        typeSpinner.setAdapter(adapter);
        typeSpinner.setSelection(adapter.getPosition(enclosure.getType()));
    }
}
