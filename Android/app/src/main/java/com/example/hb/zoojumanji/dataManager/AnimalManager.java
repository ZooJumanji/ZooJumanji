package com.example.hb.zoojumanji.dataManager;

import com.example.hb.zoojumanji.object.Animal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hb on 07/06/2016.
 */
public class AnimalManager {
    // Static animals list
    public static final Animal SIMBA = new Animal(0, "Simba", 8, Animal.SEX_MALE, Animal.SPECIES_LION, Animal.TYPE_CARNIVOROUS);
    public static final Animal TIMON = new Animal(1, "Timon", 12, Animal.SEX_MALE, Animal.SPECIES_SURICAT, Animal.TYPE_INSECTIVOROUS);
    public static final Animal PUMBA = new Animal(2, "Pumba", 15, Animal.SEX_MALE, Animal.SPECIES_WARTHOG, Animal.TYPE_OMNIVOROUS);
    public static final Animal NALA = new Animal(3, "Nala", 8, Animal.SEX_FEMALE, Animal.SPECIES_LION, Animal.TYPE_CARNIVOROUS);
    public static final Animal RAFIKKI = new Animal(4, "Rafikki", 82, Animal.SEX_MALE, Animal.SPECIES_MONKEY, Animal.TYPE_OMNIVOROUS);

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
}
