package com.example.hb.zoojumanji.animal.Repository;

import com.example.hb.zoojumanji.animal.Animal;
import com.example.hb.zoojumanji.animal.AnimalSex;
import com.example.hb.zoojumanji.animal.AnimalSpecies;
import com.example.hb.zoojumanji.animal.AnimalType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by isher on 10/06/2016.
 */
public class AnimalRepository {

    // Static animals list
    public static final Animal SIMBA = new Animal("Simba", 8, AnimalSex.MALE, AnimalSpecies.LION, AnimalType.CARNIVOROUS);
    public static final Animal TIMON = new Animal("Timon", 12, AnimalSex.MALE, AnimalSpecies.SURICATE, AnimalType.INSECTIVOROUS);
    public static final Animal PUMBA = new Animal("Pumba", 15, AnimalSex.MALE, AnimalSpecies.WARTHOG, AnimalType.OMNIVOROUS);
    public static final Animal NALA = new Animal("Nala", 8, AnimalSex.FEMALE, AnimalSpecies.LION, AnimalType.CARNIVOROUS);
    public static final Animal RAFIKKI = new Animal("Rafikki", 82, AnimalSex.MALE, AnimalSpecies.MONKEY, AnimalType.OMNIVOROUS);


    public List<Animal> GetList() {

        List<Animal> animalsList = new ArrayList<>();
        if (animalsList.isEmpty()) {
            animalsList.add(SIMBA);
            animalsList.add(TIMON);
            animalsList.add(PUMBA);
            animalsList.add(NALA);
            animalsList.add(RAFIKKI);
        }

        return animalsList;
    }
}
