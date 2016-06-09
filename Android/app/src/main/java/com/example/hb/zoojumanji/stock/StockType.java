package com.example.hb.zoojumanji.stock;

import com.example.hb.zoojumanji.R;

/**
 * Created by hb on 08/06/2016.
 */
public enum StockType {
    MEAT,
    APPLE,
    BANANA,
    SEED,
    MOUSE,
    HAY,
    BUG,
    POPCORN;

    public int getStringResource() {

        int resource = 0;
        switch (this) {
            case MEAT :
                resource = R.string.stock_type_meat;
                break;
            case APPLE :
                resource = R.string.stock_type_apple;
                break;
            case BANANA :
                resource = R.string.stock_type_banana;
                break;
            case SEED :
                resource = R.string.stock_type_seed;
                break;
            case MOUSE :
                resource = R.string.stock_type_mouse;
                break;
            case HAY :
                resource = R.string.stock_type_hay;
                break;
            case BUG :
                resource = R.string.stock_type_bug;
                break;
            case POPCORN :
                resource = R.string.stock_type_popcorn;
                break;
        }

        return resource;
    }
}
