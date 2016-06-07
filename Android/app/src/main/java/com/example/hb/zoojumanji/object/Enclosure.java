package com.example.hb.zoojumanji.object;

import com.example.hb.zoojumanji.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hb on 07/06/2016.
 */
public class Enclosure {

    public static final int ENCLOSURE_TYPE_CAGE = R.string.enclosure_type_cage;
    public static final int ENCLOSURE_TYPE_PADDOCK = R.string.enclosure_type_paddock;
    public static final int ENCLOSURE_TYPE_POOL = R.string.enclosure_type_pool;
    public static final int ENCLOSURE_TYPE_AQUARIUM = R.string.enclosure_type_aquarium;
    public static final int ENCLOSURE_TYPE_VIVARIUM = R.string.enclosure_type_vivarium;

    protected int id;
    protected String name;
    protected int max;
    protected int type;
    protected List<Animal> animals = new ArrayList<>();

    public int getId() {
        return id;
    }

    public Enclosure setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Enclosure setName(String name) {
        this.name = name;
        return this;
    }

    public int getMax() {
        return max;
    }

    public Enclosure setMax(int max) {
        this.max = max;
        return this;
    }

    public int getType() {
        return type;
    }

    public Enclosure setType(int type) {
        this.type = type;
        return this;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public void deleteAnimal(Animal animal) {
        if (animals.contains(animal)) {
            animals.remove(animal);
        }
    }

    public int getAnimalsCount() {
        return animals.size();
    }

    public Enclosure(int id, String name, int max, int type) {
        setId(id)
            .setName(name)
            .setMax(max)
            .setType(type);
    }
}
