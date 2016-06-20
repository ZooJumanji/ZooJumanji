package com.example.hb.zoojumanji.stock.activity;

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
import com.example.hb.zoojumanji.stock.Stock;
import com.example.hb.zoojumanji.stock.StockType;
import com.example.hb.zoojumanji.stock.StockUnity;
import com.example.hb.zoojumanji.stock.adapter.StockTypeSpinnerAdapter;
import com.example.hb.zoojumanji.stock.adapter.StockUnitySpinnerAdapter;
import com.example.hb.zoojumanji.stock.manager.StockManager;

import java.util.Arrays;

public class StockModifyActivity extends AppCompatActivity {

    protected StockManager manager;

    protected Spinner typeSpinner;
    protected EditText quantityText;
    protected EditText capacityText;
    protected Spinner unitySpinner;

    protected Stock stock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_modify);

        manager = new StockManager(getApplicationContext());

        // Get Stock from id
        Intent intent = getIntent();
        stock = manager.getStock(intent.getIntExtra("id", 0));

        showStockDetails();
        generateButtonsListener();
    }

    private void generateButtonsListener() {
        // Get clicked floatingButton
        FloatingActionButton saveButton = (FloatingActionButton) findViewById(R.id.save_fab);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Update stock
                try {
                    manager.modifyStock(stock.getId(),
                        (StockType) typeSpinner.getSelectedItem(),
                        Integer.valueOf(quantityText.getText().toString()),
                        Integer.valueOf(capacityText.getText().toString()),
                        (StockUnity) unitySpinner.getSelectedItem());

                    StockModifyActivity.this.finish();
                }
                catch (RuntimeException e) {
                    Toast.makeText(StockModifyActivity.this,
                            R.string.exception_formular_error,
                            Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
    }

    private void showStockDetails() {
        // get TextView
        typeSpinner = (Spinner) findViewById(R.id.edit_stock_type);
        quantityText = (EditText) findViewById(R.id.edit_stock_quantity);
        capacityText = (EditText) findViewById(R.id.edit_stock_capacity);
        unitySpinner = (Spinner) findViewById(R.id.edit_stock_unity);

        quantityText.setText(String.valueOf(stock.getQuantity()));
        capacityText.setText(String.valueOf(stock.getCapacity()));

        ArrayAdapter<StockType> typeAdapter = new StockTypeSpinnerAdapter(this,
                R.layout.spinner_stock_param_item,
                Arrays.asList(StockType.values()));

        typeSpinner.setAdapter(typeAdapter);
        typeSpinner.setSelection(typeAdapter.getPosition(stock.getType()));

        ArrayAdapter<StockUnity> unityAdapter = new StockUnitySpinnerAdapter(this,
                R.layout.spinner_stock_param_item,
                Arrays.asList(StockUnity.values()));

        unitySpinner.setAdapter(unityAdapter);
        unitySpinner.setSelection(unityAdapter.getPosition(stock.getUnity()));
    }
}
