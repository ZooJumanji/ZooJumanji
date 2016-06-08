package com.example.hb.zoojumanji.enclosure.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.hb.zoojumanji.R;
import com.example.hb.zoojumanji.enclosure.EnclosureType;

import java.util.ArrayList;
import java.util.List;

public class EnclosureCreationActivity extends AppCompatActivity {

    protected EditText nameText;
    protected EditText maxText;
    protected Spinner typeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enclosure_creation);

        // get TextView
        nameText = (EditText) findViewById(R.id.edit_enclosure_name);
        maxText = (EditText) findViewById(R.id.edit_enclosure_max);
        typeSpinner = (Spinner) findViewById(R.id.edit_enclosure_type);

        EnclosureType[] listType = EnclosureType.values();
        List<String> stringList = new ArrayList<>();
        for (EnclosureType type : listType) {
            stringList.add(getString(type.getStringResource()));
        }

        typeSpinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout
                .simple_spinner_item, stringList));

        // Get clicked floatingButton
        FloatingActionButton button = (FloatingActionButton) findViewById(R.id.save_fab);
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    // Toast save
                    Toast.makeText(EnclosureCreationActivity.this, "Animal saved", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
