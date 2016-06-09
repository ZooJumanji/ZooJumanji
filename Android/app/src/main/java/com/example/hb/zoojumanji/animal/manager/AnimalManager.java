package com.example.hb.zoojumanji.animal.manager;

import com.example.hb.zoojumanji.animal.Animal;
import com.example.hb.zoojumanji.animal.AnimalSex;
import com.example.hb.zoojumanji.animal.AnimalSpecies;
import com.example.hb.zoojumanji.animal.AnimalType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hb on 07/06/2016.
 */
public class AnimalManager {

    // Static animals list
    public static final Animal SIMBA = new Animal("Simba", 8, AnimalSex.MALE, AnimalSpecies.LION, AnimalType.CARNIVOROUS);
    public static final Animal TIMON = new Animal("Timon", 12, AnimalSex.MALE, AnimalSpecies.SURICATE, AnimalType.INSECTIVOROUS);
    public static final Animal PUMBA = new Animal("Pumba", 15, AnimalSex.MALE, AnimalSpecies.WARTHOG, AnimalType.OMNIVOROUS);
    public static final Animal NALA = new Animal("Nala", 8, AnimalSex.FEMALE, AnimalSpecies.LION, AnimalType.CARNIVOROUS);
    public static final Animal RAFIKKI = new Animal("Rafikki", 82, AnimalSex.MALE, AnimalSpecies.MONKEY, AnimalType.OMNIVOROUS);

    protected static Animal deletedAnimal;

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

    public static void createAnimal(String name, int age, AnimalSex sex, AnimalSpecies species, AnimalType type) {
        animalsList.add(new Animal(name, age, sex, species, type));
    }

    public static void deleteAnimal(Animal animal) {
        if (animalsList.contains(animal)) {
            animalsList.remove(animal);
        }

        deletedAnimal = animal;
    }

    public static void restoreAnimal() {
        if (deletedAnimal != null && !animalsList.contains(deletedAnimal)) {
            animalsList.add(deletedAnimal);
        }

        cleanEnclosure();
    }

    public static Boolean isInDeletion() {
        return deletedAnimal != null;
    }

    public static void cleanEnclosure() {
        deletedAnimal = null;
    }

    public static void modify(int id, String name, int age, AnimalSex sex, AnimalSpecies species, AnimalType type) {
        Animal animal = getAnimal(id);
        animal.setName(name)
                .setAge(age)
                .setSex(sex)
                .setSpecies(species)
                .setType(type);
    }
}
