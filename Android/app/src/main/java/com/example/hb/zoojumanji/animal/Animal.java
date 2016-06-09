package com.example.hb.zoojumanji.animal;

/**
 * Created by hb on 06/06/2016.
 */
public class Animal extends ZooEntity {

    protected int id;
    protected String name;
    protected int age;
    protected AnimalSexType sex;
    protected AnimalType type;
    protected AnimalSpeciesType species;
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

    public AnimalSexType getSex() {
        return sex;
    }

    public Animal setSex(AnimalSexType sex) {
        this.sex = sex;
        return this;
    }

    public AnimalType getType() {
        return type;
    }

    public Animal setType(AnimalType type) {
        this.type = type;
        return this;
    }

    public AnimalSpeciesType getSpecies() {
        return species;
    }

    public Animal setSpecies(AnimalSpeciesType species) {
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

    public Animal(int id, String name, int age, AnimalSexType sex, AnimalSpeciesType species, AnimalType type) {
        setId(id).setName(name)
                .setAge(age)
                .setSex(sex)
                .setSpecies(species)
                .setType(type);
    }

}
