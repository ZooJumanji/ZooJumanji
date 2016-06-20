package animal.Entity;

/**
 * Created by isher on 17/06/2016.
 */
public class Animal {

    protected int id;
    protected String name;
    protected int age;
    protected AnimalSex sex;
    protected AnimalType type;
    protected AnimalSpecies species;
    protected String food;

    private static int currentId = 0;

    private static int getCurrentId() {
        currentId++;
        return currentId;
    }

    public int getId() {
        return id;
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

    public String getFood() {
        return food;
    }

    public Animal setFood(String food) {
        this.food = food;
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
}
