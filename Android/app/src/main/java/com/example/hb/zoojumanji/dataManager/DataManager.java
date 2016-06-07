package com.example.hb.zoojumanji.dataManager;

import com.example.hb.zoojumanji.object.Animal;
import com.example.hb.zoojumanji.object.Enclosure;
import com.example.hb.zoojumanji.object.Stock;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hb on 06/06/2016.
 */
public class DataManager {

    /******************* ANIMAL *********************************************/
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

    /********************** STOCK *******************************************/
    // static Stock list
    public static final Stock HAY = new Stock(0, "Hay", Stock.TYPE_FOOD, 8, 10);
    public static final Stock PEANUT = new Stock(0, "Peanut", Stock.TYPE_FOOD, 75, 100);

    protected static List<Stock> stockList = new ArrayList<>();

    public static List<Stock> getStocks() {
        // Initialize list if is empty
        if (stockList.isEmpty()) {
            stockList.add(HAY);
            stockList.add(PEANUT);
        }
        return stockList;
    }

    // Get Stock from id
    public static Stock getStock(int id) {
        List<Stock> list = getStocks();
        for (Stock stock : list) {
            if (stock.getId() == id) {
                return stock;
            }
        }

        throw new IllegalArgumentException("Unknown animal");
    }


    /******************** Enclosure **************************************/
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

            LION_FOSS.addAnimal(SIMBA);
            LION_FOSS.addAnimal(NALA);
            MONKEY_CAGE.addAnimal(RAFIKKI);
            TIMON_POOL.addAnimal(TIMON);
            TIMON_POOL.addAnimal(PUMBA);
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