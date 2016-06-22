package animal.entity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by isher on 17/06/2016.
 */
@XmlRootElement
public class Animal {

    protected int id;
    protected String name;
    protected int age;
    protected AnimalSex sex;
    protected AnimalType type;
    protected AnimalSpecies species;

    private static int currentId = 0;

    private static int getCurrentId() {
        currentId++;
        return currentId;
    }

    public int getId() {
        return id;
    }
    
    public Animal setId(int id) {
    	if (id > currentId) {
    		currentId = id + 1;
    	}
    	
    	if (id == 0) {
    		this.id = getCurrentId();
    	}
    	else {
    		this.id = id;
    	}
    	
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

    public AnimalSex getSex() {
        return sex;
    }

    public Animal setSex(AnimalSex sex) {
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

    public AnimalSpecies getSpecies() {
        return species;
    }

    public Animal setSpecies(AnimalSpecies species) {
        this.species = species;
        return this;
    }

    public Animal() {
    }

    public Animal(String name, int age, AnimalSex sex, AnimalSpecies species, AnimalType type) {
        this.id = getCurrentId();
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.species = species;
        this.type = type;
    }

    public void merge(Animal animal) {
        this.setName(animal.name);
        this.setAge(animal.age);
        this.setSex(animal.sex);
        this.setSpecies(animal.species);
        this.setType(animal.type);
    }
}
