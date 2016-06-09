package com.example.hb.zoojumanji.animal.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hb.zoojumanji.R;
import com.example.hb.zoojumanji.animal.manager.AnimalManager;
import com.example.hb.zoojumanji.animal.Animal;

public class AnimalDetailActivity extends AppCompatActivity {

    protected TextView nameText;
    protected TextView ageText;
    protected TextView sexText;
    protected TextView speciesText;
    protected TextView typeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_detail);

        // Initialize TextView
        nameText = (TextView) findViewById(R.id.detail_animal_name);
        ageText = (TextView) findViewById(R.id.detail_animal_age);
        sexText = (TextView) findViewById(R.id.detail_animal_sex);
        speciesText = (TextView) findViewById(R.id.detail_animal_species);
        typeText = (TextView) findViewById(R.id.detail_animal_type);

        // Get Animal from id
        Intent intent = getIntent();
        Animal animal = AnimalManager.getAnimal(intent.getIntExtra("id", 0));

        // Display parameters
        nameText.setText(animal.getName());
        ageText.setText(String.valueOf(animal.getAge()));
        sexText.setText(animal.getSex().getStringResource());
        speciesText.setText(animal.getSpecies().getStringResource());
        typeText.setText(animal.getType().getStringResource());

        // Get clicked floatingButton
        FloatingActionButton button = (FloatingActionButton) findViewById(R.id.delete_fab);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Toast deletion
                Toast.makeText(AnimalDetailActivity.this, "Animal deleted", Toast.LENGTH_LONG)
                    .show();
            }
        });
    }
}
