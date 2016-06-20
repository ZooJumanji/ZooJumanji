package com.example.hb.zoojumanji.stock.activity;

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
import com.example.hb.zoojumanji.stock.adapter.StockAdapter;
import com.example.hb.zoojumanji.stock.manager.StockManager;
import com.example.hb.zoojumanji.stock.Stock;

import java.util.List;

public class StockActivity extends AppCompatActivity {

    protected StockManager manager;
    protected boolean deletion = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);

        manager = new StockManager(this);
        generateList();

        // Get clicked floatingButton to add a new animal
        FloatingActionButton button = (FloatingActionButton) findViewById(R.id.add_fab);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Generate new Activity
                Intent intent = new Intent(StockActivity.this, StockCreationActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        generateList();
        if (StockManager.isInDeletion()) {
            generateUndeleteSnackBar();
        }
    }

    private void generateList() {
        // Get list of stock
        List<Stock> list = manager.getStocks();

        generateList(list);
    }

    private void generateList(List<Stock> initialList) {

        List<Stock> list = StockManager.cleanStockList(initialList);
        // Generate specific adaptper
        ArrayAdapter<Stock> adapter = new StockAdapter(this,
                R.layout.list_stock_item, list);

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

        // Add event listener on elements of list
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                // Send to detail page with id in argument
                Intent intent = new Intent(StockActivity.this, StockModifyActivity.class);
                TextView id_text = (TextView) view.findViewById(R.id.stock_id);
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
                Toast.makeText(StockActivity.this, R.string.message_undo_deletion, Toast.LENGTH_LONG)
                        .show();
                manager.restoreStock();
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
                    StockActivity.this.manager.cleanStock();
                }
            }
        });

        // Display snackbar
        snackbar.setAction(R.string.message_deletion_undo, undoListener)
                .setActionTextColor(0xFFFF0000)
                .show();
    }

    public void refreshList(List<Stock> updatedList) {
        Toast.makeText(this, "Updated list : "+String.valueOf(updatedList.size()), Toast.LENGTH_LONG)
                .show();
        generateList(updatedList);
    }
}
