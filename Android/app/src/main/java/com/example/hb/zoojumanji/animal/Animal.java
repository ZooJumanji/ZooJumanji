package com.example.hb.zoojumanji.animal;

import com.example.hb.zoojumanji.R;


/**
 * Created by hb on 06/06/2016.
 */
public class Animal extends ZooEntity {

    // Static animals type
//    public static final int TYPE_CARNIVOROUS = R.string.animal_type_carnivorous;
//    public static final int TYPE_HERBIVOROUS = R.string.animal_type_herbivorous;
//    public static final int TYPE_INSECTIVOROUS = R.string.animal_type_insectivorous;
//    public static final int TYPE_OMNIVOROUS = R.string.animal_type_omnivorous;
//    public static final int TYPE_FRUCTIVOROUS = R.string.animal_type_fructivorous;
//    public static final int TYPE_GRANIVOROUS = R.string.animal_type_granivorous;

    // Static animals species
//    public static final int SPECIES_LION = R.string.animal_species_lion;
//    public static final int SPECIES_SURICAT = R.string.animal_species_suricat;
//    public static final int SPECIES_MONKEY = R.string.animal_species_monkey;
//    public static final int SPECIES_WARTHOG = R.string.animal_species_warthog;
//    public static final int SPECIES_TIGER = R.string.animal_species_tiger;
//    public static final int SPECIES_BIRD = R.string.animal_species_bird;

    // Enum animal species
    public static enum typeAnimal {
        animal_type_carnivorous,
        animal_type_herbivorous,
        animal_type_insectivorous,
        animal_type_omnivorous,
        animal_type_fructivorous,
        animal_type_granivorous
    }

    // Enum animals sex
    public static enum sexAnimal {
        animal_sex_male,
        animal_sex_female,
        animal_sex_hermaphrodite,
        animal_sex_assexual
    }

    // Enum animal species
    public static enum speciesAnimal {
        animal_species_lion,
        animal_species_suricat,
        animal_species_monkey,
        animal_species_warthog,
        animal_species_tiger,
        animal_species_bird
    }

    protected int id;
    protected String name;
    protected int age;
    protected int sex;
    protected int type;
    protected int species;
    protected String food;

    public int getId() {
        return id;
    }

    public Animal setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Animal setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Animal setAge(int age) {
        this.age = age;
        return this;
    }

    public int getSex() {
        return sex;
    }

    public Animal setSex(int sex) {
        this.sex = sex;
        return this;
    }

    public int getType() {
        return type;
    }

    public Animal setType(int type) {
        this.type = type;
        return this;
    }

    public int getSpecies() {
        return species;
    }

    public Animal setSpecies(int species) {
        this.species = species;
        return this;
    }

    public String getFood() {
        return food;
    }

    public Animal setFood(String food) {
        this.food = food;
        return this;
    }

    public Animal(int id, String name, int age, int sex, int species, int type) {
        setId(id).setName(name)
                .setAge(age)
                .setSex(sex)
                .setSpecies(species)
                .setType(type);
    }

}