package com.example.hb.zoojumanji.dataManager;

import com.example.hb.zoojumanji.object.Enclosure;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hb on 07/06/2016.
 */
public class EnclosureManager {
    // Static Enclosure List
    public static final Enclosure LION_FOSS = new Enclosure(0, "lion foss", 2, Enclosure.ENCLOSURE_TYPE_PADDOCK);
    public static final Enclosure MONKEY_CAGE = new Enclosure(1, "Rafikki cage", 12, Enclosure.ENCLOSURE_TYPE_CAGE);
    public static final Enclosure TIMON_POOL = new Enclosure(2, "Timon pool", 4, Enclosure.ENCLOSURE_TYPE_POOL);

    protected static List<Enclosure> enclosuresList = new ArrayList<>();

    public static List<Enclosure> getEnclosures() {

        // Initialize list if is empty
        if (enclosuresList.isEmpty()) {
            enclosuresList.add(LION_FOSS);
            enclosuresList.add(MONKEY_CAGE);
            enclosuresList.add(TIMON_POOL);

            LION_FOSS.addAnimal(AnimalManager.SIMBA);
            LION_FOSS.addAnimal(AnimalManager.NALA);
            MONKEY_CAGE.addAnimal(AnimalManager.RAFIKKI);
            TIMON_POOL.addAnimal(AnimalManager.TIMON);
            TIMON_POOL.addAnimal(AnimalManager.PUMBA);
        }

        return enclosuresList;
    }

    // Get Animal from id
    public static Enclosure getEnclosure(int id) {
        List<Enclosure> list = getEnclosures();
        for (Enclosure enclosure : list) {
            if (enclosure.getId() == id) {
                return enclosure;
            }
        }

        throw new IllegalArgumentException("Unknown enclosure");
    }
}
