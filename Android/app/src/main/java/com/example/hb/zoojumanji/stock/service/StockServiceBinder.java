package com.example.hb.zoojumanji.stock.service;

import android.os.Binder;

import com.example.hb.zoojumanji.stock.manager.StockManager;

/**
 * Created by hb on 10/06/2016.
 */
public class StockServiceBinder extends Binder {

    protected StockService service;

    public StockServiceBinder(StockService service) {
        this.service = service;
    }

    public void getStockList(StockManager manager) {
        service.getStockList(manager);
    }
}
