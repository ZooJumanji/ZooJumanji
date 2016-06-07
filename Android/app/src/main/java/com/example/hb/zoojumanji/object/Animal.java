package com.example.hb.zoojumanji.object;

import android.content.res.Resources;

import com.example.hb.zoojumanji.R;


/**
 * Created by hb on 06/06/2016.
 */
public class Animal {

    // Static animals type
    public static final int TYPE_CARNIVOROUS = R.string.animal_type_carnivorous;
    public static final int TYPE_HERBIVOROUS = R.string.animal_type_herbivorous;
    public static final int TYPE_INSECTIVOROUS = R.string.animal_type_insectivorous;
    public static final int TYPE_OMNIVOROUS = R.string.animal_type_omnivorous;
    public static final int TYPE_FRUCTIVOROUS = R.string.animal_type_fructivorous;
    public static final int TYPE_GRANIVOROUS = R.string.animal_type_granivorous;

    // Static animals species
    public static final int SPECIES_LION = R.string.animal_species_lion;
    public static final int SPECIES_SURICAT = R.string.animal_species_suricat;
    public static final int SPECIES_MONKEY = R.string.animal_species_monkey;
    public static final int SPECIES_WARTHOG = R.string.animal_species_warthog;
    public static final int SPECIES_TIGER = R.string.animal_species_tiger;
    public static final int SPECIES_BIRD = R.string.animal_species_bird;

    // Static animals sex
    public static final int SEX_MALE = R.string.animal_sex_male;
    public static final int SEX_FEMALE = R.string.animal_sex_female;
    public static final int SEX_HERMAPHRODITE = R.string.animal_sex_hermaphrodite;
    public static final int SEX_ASSEXUAL = R.string.animal_sex_assexual;

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
