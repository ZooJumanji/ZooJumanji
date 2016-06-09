package com.example.hb.zoojumanji.animal.activity;

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
import com.example.hb.zoojumanji.animal.Animal;
import com.example.hb.zoojumanji.animal.AnimalSexType;
import com.example.hb.zoojumanji.animal.adapter.AnimalSexSpinnerAdapter;
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

        Spinner sexSpinner = (Spinner) findViewById(R.id.edit_animal_sex);
        sexSpinner.setAdapter(new AnimalSexSpinnerAdapter(this, R.layout.list_animal_sex_item,Arrays.asList(AnimalSexType.values())));

        Spinner speciesSpinner = (Spinner) findViewById(R.id.edit_animal_species);
        speciesSpinner.setAdapter(new ArrayAdapter<Animal.speciesAnimal>(this, android.R.layout.simple_list_item_1, Animal.speciesAnimal.values()));

        Spinner typeSpinner = (Spinner) findViewById(R.id.edit_animal_type);
        typeSpinner.setAdapter(new ArrayAdapter<Animal.typeAnimal>(this, android.R.layout.simple_list_item_1, Animal.typeAnimal.values()));

        // Get clicked floatingButton
        FloatingActionButton button = (FloatingActionButton) findViewById(R.id.save_fab);
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    // get TextView
                    nameText = (EditText) findViewById(R.id.edit_animal_name);
                    ageText = (EditText) findViewById(R.id.edit_animal_age);
                    sexText = (Spinner) findViewById(R.id.edit_animal_sex);
                    speciesText = (Spinner) findViewById(R.id.edit_animal_species);
                    typeText = (Spinner) findViewById(R.id.edit_animal_type);

                    String strName = nameText.getText().toString();
                    int age = Integer.parseInt(ageText.getText().toString());
                    int sex = Integer.parseInt(sexText.getSelectedItem().toString());
                    int species = Integer.parseInt(speciesText.getSelectedItem().toString());
                    int type = Integer.parseInt(typeText.getSelectedItem().toString());

                    // create animal object
                    Animal animal = new Animal(5, strName, age,sex,species, type   );

                    AnimalManager.addAnimal(animal);

                    // Toast save
                    Toast.makeText(AnimalCreationActivity.this, "Animal saved", Toast.LENGTH_LONG)
                            .show();

                    Intent intent = new Intent(AnimalCreationActivity.this, AnimalActivity.class);
                    startActivity(intent);

                }
            });
        }
    }
}
