package com.example.hb.zoojumanji.enclosure;

import com.example.hb.zoojumanji.R;

/**
 * Created by hb on 08/06/2016.
 */
public enum EnclosureType {
    CAGE,
    PADDOCK,
    POOL,
    AQUARIUM,
    VIVARIUM;

    public int getStringResources() {

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
