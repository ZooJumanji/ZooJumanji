package com.example.hb.zoojumanji;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    protected TextView titleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Initialize activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Generate toobar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initialize Title
        titleText = (TextView) findViewById(R.id.title_text);

        // Add action on floatingButtons
        addMainButtonAction(R.id.animal_fab, AnimalActivity.class);
        addMainButtonAction(R.id.enclosure_fab, EnclosureActivity.class);
        addMainButtonAction(R.id.food_fab, StockActivity.class);
        addMainButtonAction(R.id.ticket_fab, TicketActivity.class);
    }

    // Floating button send to an other activity
    protected void addMainButtonAction(int button_id, final Class<? extends Activity> activityClass) {

        // Get clicked floatingButton
        FloatingActionButton button = (FloatingActionButton) findViewById(button_id);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Generate new Activity
                Intent intent = new Intent(MainActivity.this, activityClass);
                startActivity(intent);
            }
        });
    }

    // Menu creation (not use for now)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // Item menu click event listener
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Get clicked item id
        int id = item.getItemId();

        // Action if clicked button is "settings"
        if (id == R.id.action_settings) {
            return true;
        }

        // Return the parent method if clicked button is not catch
        return super.onOptionsItemSelected(item);
    }
}
