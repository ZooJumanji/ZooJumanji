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
import com.example.hb.zoojumanji.object.Stock;

import java.util.List;

public class StockActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);

        // Get list of stock
        List<Stock> list = DataManager.getStocks();

        // Generate specific adaptper
        ArrayAdapter<Stock> adapter = new StockAdapter(this,
                R.layout.list_stock_item, R.id.stock_name, list);

        // Display list
        ListView listView = (ListView) findViewById(R.id.stocks_list);
        listView.setAdapter(adapter);


        // Add event listener on elements of list
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Send to detail page with id in argument
                Intent intent = new Intent(StockActivity.this, StockDetailActivity.class);
                TextView id_text = (TextView) view.findViewById(R.id.stock_id);
                intent.putExtra("id", Integer.valueOf(String.valueOf(id_text.getText())));

                startActivity(intent);
            }
        });
    }
}
