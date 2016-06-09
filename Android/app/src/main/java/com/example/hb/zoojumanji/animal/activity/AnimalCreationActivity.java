package com.example.hb.zoojumanji.animal.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.hb.zoojumanji.R;
import com.example.hb.zoojumanji.animal.AnimalSex;
import com.example.hb.zoojumanji.animal.AnimalSpecies;
import com.example.hb.zoojumanji.animal.AnimalType;
import com.example.hb.zoojumanji.animal.adapter.AnimalSexSpinnerAdapter;
import com.example.hb.zoojumanji.animal.adapter.AnimalSpeciesSpinnerAdapter;
import com.example.hb.zoojumanji.animal.adapter.AnimalTypeSpinnerAdapter;
import com.example.hb.zoojumanji.animal.manager.AnimalManager;

import java.util.Arrays;

public class AnimalCreationActivity extends AppCompatActivity {

    protected EditText nameText;
    protected EditText ageText;
    protected Spinner sexSpinner;
    protected Spinner speciesSpinner;
    protected Spinner typeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_creation);

        initializeViews();
        generateSpinners();

        // Get clicked floatingButton
        FloatingActionButton button = (FloatingActionButton) findViewById(R.id.save_fab);
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    try {
                        AnimalManager.createAnimal(nameText.getText().toString(),
                                Integer.valueOf(ageText.getText().toString()),
                                (AnimalSex) sexSpinner.getSelectedItem(),
                                (AnimalSpecies) speciesSpinner.getSelectedItem(),
                                (AnimalType) typeSpinner.getSelectedItem());

                        AnimalCreationActivity.this.finish();
                    }
                    catch (RuntimeException e) {
                        Toast.makeText(AnimalCreationActivity.this,
                                R.string.exception_formular_error,
                                Toast.LENGTH_LONG)
                            .show();
                    }
                }
            });
        }
    }

    private void initializeViews() {
        // get TextView
        nameText = (EditText) findViewById(R.id.edit_animal_name);
        ageText = (EditText) findViewById(R.id.edit_animal_age);
        sexSpinner = (Spinner) findViewById(R.id.edit_animal_sex);
        speciesSpinner = (Spinner) findViewById(R.id.edit_animal_species);
        typeSpinner = (Spinner) findViewById(R.id.edit_animal_type);
    }

    private void generateSpinners() {
        Spinner sexSpinner = (Spinner) findViewById(R.id.edit_animal_sex);
        sexSpinner.setAdapter(new AnimalSexSpinnerAdapter(this,
                R.layout.spinner_animal_param_item,
                Arrays.asList(AnimalSex.values())));

        Spinner speciesSpinner = (Spinner) findViewById(R.id.edit_animal_species);
        speciesSpinner.setAdapter(new AnimalSpeciesSpinnerAdapter(this,
                R.layout.spinner_animal_param_item,
                Arrays.asList(AnimalSpecies.values())));

        Spinner typeSpinner = (Spinner) findViewById(R.id.edit_animal_type);
        typeSpinner.setAdapter(new AnimalTypeSpinnerAdapter(this,
                R.layout.spinner_animal_param_item,
                Arrays.asList(AnimalType.values())));
    }
}
