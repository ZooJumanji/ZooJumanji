package com.example.hb.zoojumanji.animal.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.hb.zoojumanji.R;
import com.example.hb.zoojumanji.animal.Animal;
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
    protected Spinner sexText;
    protected Spinner speciesText;
    protected Spinner typeText;

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

                    AnimalManager.createAnimal(nameText.getText().toString(),
                            Integer.valueOf(ageText.getText().toString()),
                            (AnimalSex) sexText.getSelectedItem(),
                            (AnimalSpecies) speciesText.getSelectedItem(),
                            (AnimalType) typeText.getSelectedItem());

                    AnimalCreationActivity.this.finish();

                }
            });
        }
    }

    private void initializeViews() {
        // get TextView
        nameText = (EditText) findViewById(R.id.edit_animal_name);
        ageText = (EditText) findViewById(R.id.edit_animal_age);
        sexText = (Spinner) findViewById(R.id.edit_animal_sex);
        speciesText = (Spinner) findViewById(R.id.edit_animal_species);
        typeText = (Spinner) findViewById(R.id.edit_animal_type);
    }

    private void generateSpinners() {
        Spinner sexSpinner = (Spinner) findViewById(R.id.edit_animal_sex);
        sexSpinner.setAdapter(new AnimalSexSpinnerAdapter(this,
                R.layout.list_animal_sex_item,
                Arrays.asList(AnimalSex.values())));

        Spinner speciesSpinner = (Spinner) findViewById(R.id.edit_animal_species);
        speciesSpinner.setAdapter(new AnimalSpeciesSpinnerAdapter(this,
                R.layout.list_animal_sex_item,
                Arrays.asList(AnimalSpecies.values())));

        Spinner typeSpinner = (Spinner) findViewById(R.id.edit_animal_type);
        typeSpinner.setAdapter(new AnimalTypeSpinnerAdapter(this,
                R.layout.list_animal_sex_item,
                Arrays.asList(AnimalType.values())));
    }
}
