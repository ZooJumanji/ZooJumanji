package com.example.hb.zoojumanji.stock.manager;

import android.content.res.Resources;

import com.example.hb.zoojumanji.R;
import com.example.hb.zoojumanji.stock.Stock;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hb on 07/06/2016.
 */
public class StockManager {
    // static Stock list
    public static final Stock HAY = new Stock("Hay", Stock.TYPE_FOOD, 8, 10);
    public static final Stock PEANUT = new Stock("Peanut", Stock.TYPE_FOOD, 75, 100);

    protected static List<Stock> stockList = new ArrayList<>();

    public static List<Stock> getStocks() {
        // Initialize list if is empty
        if (stockList.isEmpty()) {
            stockList.add(HAY);
            stockList.add(PEANUT);
        }
        return stockList;
    }

    // Get Stock from id
    public static Stock getStock(int id) {
        List<Stock> list = getStocks();
        for (Stock stock : list) {
            if (stock.getId() == id) {
                return stock;
            }
        }

        throw new IllegalArgumentException(Resources.getSystem().getString(R.string.exception_unknown_stock));
    }
}
