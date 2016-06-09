package com.example.hb.zoojumanji.stock.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hb.zoojumanji.R;
import com.example.hb.zoojumanji.stock.manager.StockManager;
import com.example.hb.zoojumanji.stock.Stock;

public class StockDetailActivity extends AppCompatActivity {

    protected TextView typeText;
    protected TextView quantityText;
    protected TextView capacityText;
    protected TextView unityText;

    protected Stock stock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_detail);

        // Initialize TextView
        typeText = (TextView) findViewById(R.id.detail_stock_type);
        quantityText = (TextView) findViewById(R.id.detail_stock_quantity);
        capacityText = (TextView) findViewById(R.id.detail_stock_capacity);
        unityText = (TextView) findViewById(R.id.detail_stock_unity);

        // Get stock from id
        Intent intent = getIntent();
        stock = StockManager.getStock(intent.getIntExtra("id", 0));

        showStockDetails();
        generateButtonsListener();
    }

    private void generateButtonsListener() {
        // Get clicked delete floatingButton
        FloatingActionButton button = (FloatingActionButton) findViewById(R.id.delete_fab);
        FloatingActionButton modifyButton = (FloatingActionButton) findViewById(R.id.modify_fab);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletionExecution();
            }
        });

        modifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Send to detail page with id in argument
                Intent intent = new Intent(StockDetailActivity.this, StockModifyActivity.class);
                intent.putExtra("id", stock.getId());

                startActivity(intent);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        showStockDetails();
    }

    private void showStockDetails() {
        // Display parameters
        typeText.setText(stock.getType().getStringResource());
        quantityText.setText(String.valueOf(stock.getQuantity()));
        capacityText.setText(String.valueOf(stock.getCapacity()));
        unityText.setText(getString(stock.getUnity().getStringResource()));
    }

    private void deletionExecution() {
        StockManager.deleteStock(stock);
        finish();
    }
}
