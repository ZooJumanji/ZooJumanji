package com.example.hb.zoojumanji.object;

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
    protected int currentCount;
    protected int maxCount;


    public int getCurrentCount() {
        return currentCount;
    }

    public Stock setCurrentCount(int currentCount) {
        this.currentCount = currentCount;
        return this;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public Stock setMaxCount(int maxCount) {
        this.maxCount = maxCount;
        return this;
    }
    public int getId() {
        return id;
    }

    public Stock setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Stock setName(String name) {
        this.name = name;
        return this;
    }

    public int getType() {
        return type;
    }

    public Stock setType(int type) {
        this.type = type;
        return this;
    }


    public Stock(int id, String name, int type, int count, int maxCount) {
        setId(id).setName(name)
                .setType(type)
                .setCurrentCount(count)
                .setMaxCount(maxCount);
    }

}
