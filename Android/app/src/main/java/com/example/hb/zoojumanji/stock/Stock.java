package com.example.hb.zoojumanji.stock;

import com.example.hb.zoojumanji.R;


/**
 * Created by jonas on 07//06/2016.
 */
public class Stock {

    // Static stock type
    public static final int TYPE_FOOD = R.string.stock_type_food;

    protected int id;
    protected String name;
    protected int type;
    protected int quantity;
    protected int capacity;

    private static int currentId = 0;

    private static int getCurrentId() {
        currentId++;
        return currentId;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    public Stock(String name, int type, int quantity, int capacity) {
        this.id = getCurrentId();
        this.name = name;
        this.type = type;
        this.quantity = quantity;
        this.capacity = capacity;
    }

}
