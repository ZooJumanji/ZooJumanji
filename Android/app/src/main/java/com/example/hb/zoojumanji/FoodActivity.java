package com.example.hb.zoojumanji;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hb.zoojumanji.adapter.StockAdapter;
import com.example.hb.zoojumanji.dataManager.DataManager;
import com.example.hb.zoojumanji.dataManager.StockManager;
import com.example.hb.zoojumanji.object.Stock;

import java.util.List;

public class FoodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        // Get list of stock
        List<Stock> list = StockManager.getStocks();

        // Generate specific adaptper
        ArrayAdapter<Stock> adapter = new StockAdapter(this,
                R.layout.list_stock_item, R.id.stock_name, list);

        // Display list
        ListView listView = (ListView) findViewById(R.id.stocks_list);
        listView.setAdapter(adapter);

    }
}
