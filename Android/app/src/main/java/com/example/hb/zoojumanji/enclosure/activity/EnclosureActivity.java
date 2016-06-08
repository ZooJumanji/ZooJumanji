package com.example.hb.zoojumanji.enclosure.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hb.zoojumanji.R;
import com.example.hb.zoojumanji.enclosure.adapter.EnclosureAdapter;
import com.example.hb.zoojumanji.enclosure.manager.EnclosureManager;
import com.example.hb.zoojumanji.enclosure.Enclosure;

import java.util.List;

public class EnclosureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enclosure);

        // Get list of animals
        List<Enclosure> list = EnclosureManager.getEnclosures();

        // Generate specific adapter
        ArrayAdapter<Enclosure> adapter = new EnclosureAdapter(this,
                R.layout.list_enclosure_item, R.id.enclosure_name, list);

        // Display list
        ListView listView = (ListView) findViewById(R.id.enclosures_list);
        listView.setAdapter(adapter);

        // Add event listener on elements of list
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Send to detail page with id in argument
                Intent intent = new Intent(EnclosureActivity.this, EnclosureDetailActivity.class);
                TextView id_text = (TextView) view.findViewById(R.id.enclosure_id);
                intent.putExtra("id", Integer.valueOf(String.valueOf(id_text.getText())));

                startActivity(intent);
            }
        });

        // Get clicked floatingButton to add a new animal
        FloatingActionButton button = (FloatingActionButton) findViewById(R.id.add_fab);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Generate new Activity
                Intent intent = new Intent(EnclosureActivity.this, EnclosureCreationActivity.class);
                startActivity(intent);
            }
        });
    }
}
