package com.example.hb.zoojumanji.animal.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hb.zoojumanji.R;
import com.example.hb.zoojumanji.animal.adapter.AnimalAdapter;
import com.example.hb.zoojumanji.animal.manager.AnimalManager;
import com.example.hb.zoojumanji.animal.Animal;

import java.util.List;

public class AnimalActivity extends AppCompatActivity {

    protected AnimalManager manager;
    protected boolean deletion = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal);

        manager = new AnimalManager(this);

        generateList();
        generateButtonListener();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        generateList();
        if (AnimalManager.isInDeletion()) {
            generateUndeleteSnackBar();
        }
    }

    private void generateButtonListener() {
        // Get clicked floatingButton to add a new animal
        FloatingActionButton button = (FloatingActionButton) findViewById(R.id.add_fab);
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    // Generate new Activity
                    Intent intent = new Intent(AnimalActivity.this, AnimalCreationActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

    private void generateList() {
        // Get list of enclosures
        List<Animal> list = manager.getAnimals();

        generateList(list);
    }
    private void generateList(List<Animal> list ) {
        // Generate specific adapter
        ArrayAdapter<Animal> adapter = new AnimalAdapter(this,
                R.layout.list_animal_item, list);

        // Display list
        ListView listView = (ListView) findViewById(R.id.animals_list);
        listView.setAdapter(adapter);

        // Add event listener on elements of list
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Send to detail page with id in argument
                Intent intent = new Intent(AnimalActivity.this, AnimalDetailActivity.class);
                TextView id_text = (TextView) view.findViewById(R.id.animal_id);
                intent.putExtra("id", Integer.valueOf(String.valueOf(id_text.getText())));

                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                // Send to detail page with id in argument
                Intent intent = new Intent(AnimalActivity.this, AnimalModifyActivity.class);
                TextView id_text = (TextView) view.findViewById(R.id.animal_id);
                intent.putExtra("id", Integer.valueOf(String.valueOf(id_text.getText())));

                startActivity(intent);
                return false;
            }
        });
    }

    private void generateUndeleteSnackBar() {

        // Activation deletion
        deletion = true;

        //The undo action that could be called by the following snackbar
        final View.OnClickListener undoListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abort deletion
                deletion = false;
                Toast.makeText(AnimalActivity.this, R.string.message_undo_deletion, Toast
                        .LENGTH_LONG)
                        .show();
                manager.restoreAnimal();
                generateList();
            }
        };

        // Generate snackbar
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                R.string.message_stock_deletion,
                Snackbar.LENGTH_LONG);

        // Generate snackbar events
        snackbar.getView().addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {
                // Empty
            }

            @Override
            public void onViewDetachedFromWindow(View v) {
                if (deletion) {
                    manager.cleanAnimal();
                }
            }
        });

        // Display snackbar
        snackbar.setAction(R.string.message_deletion_undo, undoListener)
                .setActionTextColor(0xFFFF0000)
                .show();
    }

    public void refreshList(List<Animal> updatedList) {
        Toast.makeText(this, "Updated list : "+String.valueOf(updatedList.size()), Toast.LENGTH_LONG)
                .show();
        generateList(updatedList);
    }
}
