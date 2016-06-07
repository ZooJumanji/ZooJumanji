package com.example.hb.zoojumanji;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hb.zoojumanji.dataManager.DataManager;
import com.example.hb.zoojumanji.object.Stock;

public class StockDetailActivity extends AppCompatActivity {

    protected TextView nameText;
    protected TextView currentCountText;
    protected TextView maxCountText;
    protected TextView typeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_detail);

        // Initialize TextView
        nameText = (TextView) findViewById(R.id.detail_stock_name);
        typeText = (TextView) findViewById(R.id.detail_stock_type);
        currentCountText = (TextView) findViewById(R.id.detail_stock_actual_count);
        maxCountText = (TextView) findViewById(R.id.detail_stock_max_count);

        // Get stock from id
        Intent intent = getIntent();
        Stock stock = DataManager.getStock(intent.getIntExtra("id", 0));

        // Display parameters
        nameText.setText(stock.getName());
        currentCountText.setText(String.valueOf(stock.getCurrentCount()));
        maxCountText.setText(String.valueOf(stock.getMaxCount()));
        typeText.setText(getString(stock.getType()));


    }
}
