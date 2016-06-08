package com.example.hb.zoojumanji.enclosure.manager;

import com.example.hb.zoojumanji.animal.manager.AnimalManager;
import com.example.hb.zoojumanji.enclosure.Enclosure;
import com.example.hb.zoojumanji.enclosure.EnclosureType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hb on 07/06/2016.
 */
public class EnclosureManager {
    // Static Enclosure List
    public static final Enclosure LION_FOSS = new Enclosure("lion foss", 2, EnclosureType.PADDOCK);
    public static final Enclosure MONKEY_CAGE = new Enclosure("Rafikki cage", 12, EnclosureType.CAGE);
    public static final Enclosure TIMON_POOL = new Enclosure("Timon pool", 4, EnclosureType.POOL);

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

    public static void createEnclosure(String name, int capacity, EnclosureType type) {
        enclosuresList.add(new Enclosure(name, capacity, type));
    }

}
