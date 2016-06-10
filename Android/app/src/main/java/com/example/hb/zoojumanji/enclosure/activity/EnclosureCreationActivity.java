package com.example.hb.zoojumanji.enclosure.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.hb.zoojumanji.R;
import com.example.hb.zoojumanji.enclosure.EnclosureType;
import com.example.hb.zoojumanji.enclosure.adapter.EnclosureTypeSpinnerAdapter;
import com.example.hb.zoojumanji.enclosure.manager.EnclosureManager;

import java.util.Arrays;

public class EnclosureCreationActivity extends AppCompatActivity {

    protected EnclosureManager manager;

    protected EditText nameText;
    protected EditText maxText;
    protected Spinner typeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enclosure_creation);

        manager = new EnclosureManager(getApplicationContext());

        // get TextView
        nameText = (EditText) findViewById(R.id.edit_enclosure_name);
        maxText = (EditText) findViewById(R.id.edit_enclosure_max);
        typeSpinner = (Spinner) findViewById(R.id.edit_enclosure_type);

        typeSpinner.setAdapter(new EnclosureTypeSpinnerAdapter(this,
                R.layout.spinner_enclosure_param_item,
                Arrays.asList(EnclosureType.values())));

        // Get clicked floatingButton
        FloatingActionButton button = (FloatingActionButton) findViewById(R.id.save_fab);
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    try {
                        manager.createEnclosure(nameText.getText().toString(),
                                Integer.valueOf(maxText.getText().toString()),
                                (EnclosureType) typeSpinner.getSelectedItem());

                        EnclosureCreationActivity.this.finish();
                    }
                    catch (RuntimeException e) {
                        Toast.makeText(EnclosureCreationActivity.this,
                                R.string.exception_formular_error,
                                Toast.LENGTH_LONG)
                                .show();
                    }
                }
            });
        }
    }
}
