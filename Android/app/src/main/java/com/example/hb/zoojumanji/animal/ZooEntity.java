package com.example.hb.zoojumanji.animal;

import com.example.hb.zoojumanji.R;

/**
 * Created by isher on 08/06/2016.
 */
abstract class ZooEntity {


    /**
     * Return int from R.string.str
     *
     * @param str String
     * @return
     */
    public static int GetStringR(String str) {
        try {
            return R.string.class.getDeclaredField(str).getInt(null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return R.string.Error_Not_Found_Exception;
    }
}
