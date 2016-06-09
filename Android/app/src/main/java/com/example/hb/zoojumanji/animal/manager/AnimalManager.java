package com.example.hb.zoojumanji.animal.manager;

import com.example.hb.zoojumanji.animal.Animal;
import com.example.hb.zoojumanji.animal.AnimalSexType;
import com.example.hb.zoojumanji.animal.AnimalSpeciesType;
import com.example.hb.zoojumanji.animal.AnimalType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hb on 07/06/2016.
 */
public class AnimalManager {

    // Static animals list
    public static final Animal SIMBA = new Animal(0, "Simba", 8, AnimalSexType.MALE, AnimalSpeciesType.LION, AnimalType.CARNIVOROUS);

    public static final Animal TIMON = new Animal(1, "Timon", 12, AnimalSexType.MALE, AnimalSpeciesType.SURICATE, AnimalType.INSECTIVOROUS);

    public static final Animal PUMBA = new Animal(2, "Pumba", 15, AnimalSexType.MALE, AnimalSpeciesType.WARTHOG, AnimalType.OMNIVOROUS);

    public static final Animal NALA = new Animal(3, "Nala", 8, AnimalSexType.FEMALE, AnimalSpeciesType.LION, AnimalType.CARNIVOROUS);

    public static final Animal RAFIKKI = new Animal(4, "Rafikki", 82, AnimalSexType.MALE, AnimalSpeciesType.MONKEY, AnimalType.OMNIVOROUS);

    protected static List<Animal> animalsList = new ArrayList<>();

    public static List<Animal> getAnimals() {

        // Initialize list if is empty
        if (animalsList.isEmpty()) {
            animalsList.add(SIMBA);
            animalsList.add(TIMON);
            animalsList.add(PUMBA);
            animalsList.add(NALA);
            animalsList.add(RAFIKKI);
        }

        return animalsList;
    }

    // Get Animal from id
    public static Animal getAnimal(int id) {
        List<Animal> list = getAnimals();
        for (Animal animal : list) {
            if (animal.getId() == id) {
                return animal;
            }
        }

        throw new IllegalArgumentException("Unknown animal");
    }

    // Add animal to list
    public static void addAnimal(Animal animal) {

        getAnimals().add(animal);
    }
}
