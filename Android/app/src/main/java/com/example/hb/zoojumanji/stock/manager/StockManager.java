package com.example.hb.zoojumanji.stock.manager;

import android.content.res.Resources;

import com.example.hb.zoojumanji.R;
import com.example.hb.zoojumanji.stock.Stock;
import com.example.hb.zoojumanji.stock.StockType;
import com.example.hb.zoojumanji.stock.StockUnity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hb on 07/06/2016.
 */
public class StockManager {
    // static Stock list
    public static final Stock MEAT = new Stock(StockType.MEAT, 82, 100, StockUnity.PIECE);
    public static final Stock APPLE = new Stock(StockType.APPLE, 3, 80, StockUnity.KILO);
    public static final Stock BANANA = new Stock(StockType.BANANA, 50, 80, StockUnity.PIECE);
    public static final Stock BUG = new Stock(StockType.BUG, 400, 400, StockUnity.GRAMME);
    public static final Stock POPCORN = new Stock(StockType.POPCORN, 1223, 1500, StockUnity.KILO);

    protected static Stock deletedStock;

    protected static List<Stock> stockList = new ArrayList<>();

    public static List<Stock> getStocks() {
        // Initialize list if is empty
        if (stockList.isEmpty()) {
            stockList.add(MEAT);
            stockList.add(APPLE);
            stockList.add(BANANA);
            stockList.add(BUG);
            stockList.add(POPCORN);
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

    public static void createStock(StockType type, int quantity, int capacity, StockUnity unity) {
        stockList.add(new Stock(type, quantity, capacity, unity));
    }

    public static void deleteStock(Stock stock) {
        if (stockList.contains(stock)) {
            stockList.remove(stock);
        }

        deletedStock = stock;
    }

    public static void restoreStock() {
        if (deletedStock != null && !stockList.contains(deletedStock)) {
            stockList.add(deletedStock);
        }

        cleanStock();
    }

    public static Boolean isInDeletion() {
        return deletedStock != null;
    }

    public static void cleanStock() {
        deletedStock = null;
    }

    public static void modify(int id, StockType type, int quantity, int capacity, StockUnity unity) {
        Stock stock = getStock(id);
        stock.setType(type)
                .setQuantity(quantity)
                .setCapacity(capacity)
                .setUnity(unity);
    }
}
