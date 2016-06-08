package com.example.hb.zoojumanji.enclosure;

import com.example.hb.zoojumanji.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hb on 08/06/2016.
 */
public enum EnclosureType {
    CAGE,
    PADDOCK,
    POOL,
    AQUARIUM,
    VIVARIUM;

    public static List<EnclosureType> getAll() {
        List<EnclosureType> list = new ArrayList<>();

        list.add(CAGE);
        list.add(PADDOCK);
        list.add(POOL);
        list.add(AQUARIUM);
        list.add(VIVARIUM);

        return list;
    }

    public int getStringResource() {

        int resource = 0;
        switch (this) {
            case CAGE :
                resource = R.string.enclosure_type_cage;
                break;
            case PADDOCK :
                resource = R.string.enclosure_type_paddock;
                break;
            case POOL :
                resource = R.string.enclosure_type_pool;
                break;
            case AQUARIUM :
                resource = R.string.enclosure_type_aquarium;
                break;
            case VIVARIUM :
                resource = R.string.enclosure_type_vivarium;
                break;
        }

        return resource;
    }
}
