package com.example.hb.zoojumanji.stock;

import com.example.hb.zoojumanji.R;

/**
 * Created by hb on 08/06/2016.
 */
public enum StockUnity {
    PIECE,
    KILO,
    GRAMME,
    ;

    public int getStringResource() {

        int resource = 0;
        switch (this) {
            case PIECE :
                resource = R.string.enclosure_type_cage;
                break;
            case KILO :
                resource = R.string.enclosure_type_paddock;
                break;
            case GRAMME :
                resource = R.string.enclosure_type_pool;
                break;
        }

        return resource;
    }
}
