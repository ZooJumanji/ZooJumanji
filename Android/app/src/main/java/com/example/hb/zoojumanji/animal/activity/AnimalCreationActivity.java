package com.example.hb.zoojumanji.animal.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hb.zoojumanji.R;
import com.example.hb.zoojumanji.animal.Animal;

public class AnimalCreationActivity extends AppCompatActivity {

    protected EditText nameText;
    protected EditText ageText;
    protected EditText sexText;
    protected EditText speciesText;
    protected EditText typeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_creation);

        // Get clicked floatingButton
        FloatingActionButton button = (FloatingActionButton) findViewById(R.id.save_fab);
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    // get TextView
                    nameText = (EditText) findViewById(R.id.edit_animal_name);
                    ageText = (EditText) findViewById(R.id.edit_animal_age);
                    sexText = (EditText) findViewById(R.id.edit_animal_sex);
                    speciesText = (EditText) findViewById(R.id.edit_animal_species);
                    typeText = (EditText) findViewById(R.id.edit_animal_type);

                    String strName = nameText.getText().toString();
                    int age = Integer.parseInt(ageText.getText().toString());
                    int sex = 1; //TODO JB : remplacer l'EditText par une enum

                    // create animal object
                    Animal animal = new Animal(5, strName, age,
                            Animal.GetStringR(Animal.sexAnimal.animal_sex_female.toString()),
                            Animal.GetStringR(Animal.speciesAnimal.animal_species_monkey.toString()),
                            Animal.GetStringR(Animal.typeAnimal.animal_type_fructivorous.toString())
                    );



                    // Toast save
                    Toast.makeText(AnimalCreationActivity.this, "Animal saved", Toast.LENGTH_LONG)
                            .show();
                }
            });
        }
    }
}
