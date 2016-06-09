package com.example.hb.zoojumanji.animal.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.hb.zoojumanji.R;
import com.example.hb.zoojumanji.animal.Animal;
import com.example.hb.zoojumanji.animal.AnimalSex;
import com.example.hb.zoojumanji.animal.AnimalSpecies;
import com.example.hb.zoojumanji.animal.AnimalType;
import com.example.hb.zoojumanji.animal.adapter.AnimalSexSpinnerAdapter;
import com.example.hb.zoojumanji.animal.adapter.AnimalSpeciesSpinnerAdapter;
import com.example.hb.zoojumanji.animal.adapter.AnimalTypeSpinnerAdapter;
import com.example.hb.zoojumanji.animal.manager.AnimalManager;
import com.example.hb.zoojumanji.enclosure.EnclosureType;
import com.example.hb.zoojumanji.enclosure.adapter.EnclosureTypeSpinnerAdapter;

import java.util.Arrays;

public class AnimalModifyActivity extends AppCompatActivity {

    protected EditText nameText;
    protected EditText ageText;
    protected Spinner sexSpinner;
    protected Spinner speciesSpinner;
    protected Spinner typeSpinner;

    protected Animal animal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_modify);

        // Get Enclosure from id
        Intent intent = getIntent();
        animal = AnimalManager.getAnimal(intent.getIntExtra("id", 0));

        showAnimalDetail();
        generateButtonsListener();
    }

    private void generateButtonsListener() {
        // Get clicked floatingButton
        FloatingActionButton saveButton = (FloatingActionButton) findViewById(R.id.save_fab);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Update animal
                AnimalManager.modify(animal.getId(),
                        nameText.getText().toString(),
                        Integer.valueOf(ageText.getText().toString()),
                        (AnimalSex) sexSpinner.getSelectedItem(),
                        (AnimalSpecies) speciesSpinner.getSelectedItem(),
                        (AnimalType) typeSpinner.getSelectedItem());

                AnimalModifyActivity.this.finish();

            }
        });
    }

    private void showAnimalDetail() {
        // get TextView
        nameText = (EditText) findViewById(R.id.edit_animal_name);
        ageText = (EditText) findViewById(R.id.edit_animal_age);
        sexSpinner = (Spinner) findViewById(R.id.edit_animal_sex);
        speciesSpinner = (Spinner) findViewById(R.id.edit_animal_species);
        typeSpinner = (Spinner) findViewById(R.id.edit_animal_type);

        nameText.setText(
                animal.getName());
        ageText.setText(String.valueOf(animal.getAge()));

        ArrayAdapter<AnimalSex> sexAdapter = new AnimalSexSpinnerAdapter(this,
                R.layout.spinner_animal_param_item,
                Arrays.asList(AnimalSex.values()));

        sexSpinner.setAdapter(sexAdapter);
        sexSpinner.setSelection(sexAdapter.getPosition(animal.getSex()));

        ArrayAdapter<AnimalSpecies> speciesAdapter = new AnimalSpeciesSpinnerAdapter(this,
                R.layout.spinner_animal_param_item,
                Arrays.asList(AnimalSpecies.values()));

        speciesSpinner.setAdapter(speciesAdapter);
        speciesSpinner.setSelection(speciesAdapter.getPosition(animal.getSpecies()));

        ArrayAdapter<AnimalType> typeAdapter = new AnimalTypeSpinnerAdapter(this,
                R.layout.spinner_animal_param_item,
                Arrays.asList(AnimalType.values()));

        typeSpinner.setAdapter(typeAdapter);
        typeSpinner.setSelection(typeAdapter.getPosition(animal.getType()));
    }
}
