package com.example.hb.zoojumanji;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hb.zoojumanji.dataManager.StockManager;
import com.example.hb.zoojumanji.object.Stock;

public class StockDetailActivity extends AppCompatActivity {

    protected TextView nameText;
    protected TextView currentCountText;
    protected TextView maxCountText;
    protected TextView typeText;

    View.OnClickListener mOnClickListener;

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
        Stock stock = StockManager.getStock(intent.getIntExtra("id", 0));

        // Display parameters
        nameText.setText(stock.getName());
        currentCountText.setText(String.valueOf(stock.getCurrentCount()));
        maxCountText.setText(String.valueOf(stock.getMaxCount()));
        typeText.setText(getString(stock.getType()));

        // Get clicked delete floatingButton
        FloatingActionButton button = (FloatingActionButton) findViewById(R.id.delete_fab);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //The undo action that could be called by the following snackbar
                mOnClickListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(StockDetailActivity.this, "Stock UNdeleted", Toast.LENGTH_LONG)
                                .show();
                    }
                };
                //Displays a snackbar with a red UNDO action
                Snackbar.make(findViewById(android.R.id.content), "Stock deleted", Snackbar.LENGTH_LONG)
                        .setAction("Undo", mOnClickListener)
                        .setActionTextColor(0xFFFF0000)
                        .show();

            }
        });
    }
}
