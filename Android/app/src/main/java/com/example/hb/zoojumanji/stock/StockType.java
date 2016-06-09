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
                resource = R.string.enclosure_type_cage;
                break;
            case APPLE :
                resource = R.string.enclosure_type_paddock;
                break;
            case BANANA :
                resource = R.string.enclosure_type_pool;
                break;
            case SEED :
                resource = R.string.enclosure_type_aquarium;
                break;
            case MOUSE :
                resource = R.string.enclosure_type_vivarium;
                break;
            case HAY :
                resource = R.string.enclosure_type_vivarium;
                break;
            case BUG :
                resource = R.string.enclosure_type_vivarium;
                break;
            case POPCORN :
                resource = R.string.enclosure_type_vivarium;
                break;
        }

        return resource;
    }
}
