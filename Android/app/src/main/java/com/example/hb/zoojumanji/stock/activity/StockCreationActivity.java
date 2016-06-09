package com.example.hb.zoojumanji.stock.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.hb.zoojumanji.R;
import com.example.hb.zoojumanji.stock.StockType;
import com.example.hb.zoojumanji.stock.StockUnity;
import com.example.hb.zoojumanji.stock.adapter.StockTypeSpinnerAdapter;
import com.example.hb.zoojumanji.stock.adapter.StockUnitySpinnerAdapter;
import com.example.hb.zoojumanji.stock.manager.StockManager;

import java.util.Arrays;

public class StockCreationActivity extends AppCompatActivity {

    protected Spinner typeSpinner;
    protected EditText quantityText;
    protected EditText capacityText;
    protected Spinner unitySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_creation);

        // get TextView
        typeSpinner = (Spinner) findViewById(R.id.edit_stock_type);
        quantityText = (EditText) findViewById(R.id.edit_stock_quantity);
        capacityText = (EditText) findViewById(R.id.edit_stock_capacity);
        unitySpinner = (Spinner) findViewById(R.id.edit_stock_unity);

        typeSpinner.setAdapter(new StockTypeSpinnerAdapter(this,
                R.layout.spinner_stock_param_item,
                Arrays.asList(StockType.values())));

        unitySpinner.setAdapter(new StockUnitySpinnerAdapter(this,
                R.layout.spinner_stock_param_item,
                Arrays.asList(StockUnity.values())));

        // Get clicked floatingButton
        FloatingActionButton button = (FloatingActionButton) findViewById(R.id.save_fab);
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    StockManager.createStock((StockType) typeSpinner.getSelectedItem(),
                            Integer.valueOf(quantityText.getText().toString()),
                            Integer.valueOf(capacityText.getText().toString()),
                            (StockUnity) unitySpinner.getSelectedItem());

                    StockCreationActivity.this.finish();
                }
            });
        }
    }
}
