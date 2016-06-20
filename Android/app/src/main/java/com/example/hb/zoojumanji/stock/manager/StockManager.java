package com.example.hb.zoojumanji.stock.manager;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.example.hb.zoojumanji.R;
import com.example.hb.zoojumanji.stock.Stock;
import com.example.hb.zoojumanji.stock.StockType;
import com.example.hb.zoojumanji.stock.StockUnity;
import com.example.hb.zoojumanji.stock.activity.StockActivity;
import com.example.hb.zoojumanji.stock.service.StockService;
import com.example.hb.zoojumanji.stock.service.StockServiceBinder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hb on 07/06/2016.
 */
public class StockManager {

    protected Context context;
    private ServiceConnection connection;


    protected static Stock deletedStock;

    protected static List<Stock> stocksList = new ArrayList<>();

    public StockManager(Context context) {
        this.context = context;
    }

    public List<Stock> getStocks() {
        startBindingService();

        return cleanStockList(stocksList);
    }

    public static List<Stock> cleanStockList(List<Stock> stocks) {
        List<Stock> list = new ArrayList<>();

        for (Stock stock : stocks) {
            if ( deletedStock == null ||
                    (!stock.equals(deletedStock) && stock.getId() != deletedStock.getId())) {
                list.add(stock);
            }
        }

        return list;
    }

    protected void startBindingService() {

        Intent intent = new Intent(StockManager.this.context, StockService
                .class);
        intent.setAction("list");

        connection = new ServiceConnection() {

            StockServiceBinder serviceBinder;

            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                serviceBinder = (StockServiceBinder) service;
                serviceBinder.getStockList(StockManager.this);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
            }
        };

        context.bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    protected void startService(final String action, final int id) {

        Thread thread = new Thread() {
            @Override
            public void run() {
                Intent intent = new Intent(StockManager.this.context, StockService.class);
                intent.setAction(action);
                intent.putExtra("id", id);
                context.startService(intent);
            }
        };

        thread.start();
    }

    // Get Stock from id
    public Stock getStock(int id) {
        List<Stock> list = getStocks();
        for (Stock stock : list) {
            if (stock.getId() == id) {
                return stock;
            }
        }

        throw new IllegalArgumentException(context.getString(R.string.exception_unknown_stock));
    }

    public void createStock(StockType type, int quantity, int capacity, StockUnity unity) {

        Stock stock = new Stock(type, quantity, capacity, unity);

        /* CANCEL ANDROID ID AUTO-INCREMENT */
        stock.setId(0);

        startService("create", stock.getId());
        stocksList.add(stock);
    }

    public void deleteStock(Stock stock) {
        if (stocksList.contains(stock)) {
            stocksList.remove(stock);
        }

        deletedStock = stock;
    }

    public void restoreStock() {
        if (deletedStock != null && !stocksList.contains(deletedStock)) {
            stocksList.add(deletedStock);
        }

        deletedStock = null;
        updateList(stocksList);
    }

    public static Boolean isInDeletion() {
        return deletedStock != null;
    }

    public void cleanStock() {
        startService("delete", deletedStock.getId());
        deletedStock = null;
    }

    public void modifyStock(int id, StockType type, int quantity, int capacity, StockUnity unity) {
        startService("modify", id);

        Stock stock = getStock(id);
        stock.setType(type)
                .setQuantity(quantity)
                .setCapacity(capacity)
                .setUnity(unity);
    }

    public void updateList(List<Stock> stocks) {
        stocksList = stocks;

        if (context != null && context.getClass() == StockActivity.class) {
            StockActivity activity = (StockActivity) context;
            activity.refreshList(stocksList);
        }
    }
}
