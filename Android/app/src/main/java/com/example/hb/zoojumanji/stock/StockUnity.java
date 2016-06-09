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
                resource = R.string.stock_unity_piece;
                break;
            case KILO :
                resource = R.string.stock_unity_kilo;
                break;
            case GRAMME :
                resource = R.string.stock_unity_gramme;
                break;
        }

        return resource;
    }
}
