package com.example.hb.zoojumanji;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hb.zoojumanji.animal.activity.AnimalActivity;
import com.example.hb.zoojumanji.enclosure.activity.EnclosureActivity;
import com.example.hb.zoojumanji.stock.activity.StockActivity;
import com.example.hb.zoojumanji.ticket.activity.TicketActivity;

public class MainActivity extends AppCompatActivity {

    // IP local          "192.168.1.27"
    // IP virtual device "10.0.2.22"
    // IP Baya           "172.16.110.169"
    private static String webServiceIP = "192.168.1.37";
    protected TextView titleText;

    public static String getWebServiceIP() {
        return webServiceIP;
    }

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
        addMainButtonAction(R.id.stock_fab, StockActivity.class);
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
        if (id == R.id.action_webservice_ip) {
            displayIPConfigPrompt();
            return true;
        }

        // Return the parent method if clicked button is not catch
        return super.onOptionsItemSelected(item);
    }

    private void displayIPConfigPrompt() {

        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.main_prompt, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView
                .findViewById(R.id.prompt_result);
        userInput.setText(webServiceIP);

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton(getString(R.string.prompt_button_ok),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                webServiceIP = userInput.getText().toString();
                            }
                        })
                .setNegativeButton(getString(R.string.prompt_button_cancel),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }
}
