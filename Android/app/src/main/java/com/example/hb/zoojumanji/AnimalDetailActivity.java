package com.example.hb.zoojumanji;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hb.zoojumanji.dataManager.DataManager;
import com.example.hb.zoojumanji.object.Animal;

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
        Animal animal = DataManager.getAnimal(intent.getIntExtra("id", 0));

        // Display parameters
        nameText.setText(animal.getName());
        ageText.setText(String.valueOf(animal.getAge()));
        sexText.setText(getString(animal.getSex()));
        speciesText.setText(getString(animal.getSpecies()));
        typeText.setText(getString(animal.getType()));

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
