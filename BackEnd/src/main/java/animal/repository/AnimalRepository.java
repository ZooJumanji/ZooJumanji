package animal.repository;

import animal.Animal;
import animal.AnimalSex;
import animal.AnimalSpecies;
import animal.AnimalType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by isher on 17/06/2016.
 */
public class AnimalRepository {

    // Static animals list
    public static final Animal SIMBA = new Animal("Simba", 8, AnimalSex.MALE, AnimalSpecies.LION, AnimalType.CARNIVOROUS);
    public static final Animal TIMON = new Animal("Timon", 12, AnimalSex.MALE, AnimalSpecies.SURICATE, AnimalType.INSECTIVOROUS);
    public static final Animal PUMBA = new Animal("Pumba", 15, AnimalSex.MALE, AnimalSpecies.WARTHOG, AnimalType.OMNIVOROUS);
    public static final Animal NALA = new Animal("Nala", 8, AnimalSex.FEMALE, AnimalSpecies.LION, AnimalType.CARNIVOROUS);
    public static final Animal RAFIKKI = new Animal("Rafikki", 82, AnimalSex.MALE, AnimalSpecies.MONKEY, AnimalType.OMNIVOROUS);

    private static List<Animal> animalList;
    public static int counter = 0;


    private static AnimalRepository instance;
    public static AnimalRepository getInstance() {
        // singleton
        if (instance == null) {
            instance = new AnimalRepository();

            animalList = new ArrayList<>();
        }
        return instance;
    }

    /**
     * Retourne la liste des animaux
     *
     * @return List<Animal>
     */
    public List<Animal> GetList() {
        if (animalList.size() == 0) {
            animalList.add(SIMBA);
            animalList.add(TIMON);
            animalList.add(PUMBA);
            animalList.add(NALA);
            animalList.add(RAFIKKI);
            counter = 5;
        }

        return animalList;
    }

    public Animal GetListById(Integer id) {
        if (id < counter){
            return animalList.get(id);
        }
        return null;
    }


    public Animal CreateAndUpdate(Animal animal) {
        // create
        if (animal.getId() == 0) {
            animal.setId(++counter);
            animalList.add(animal);
        }
        else {
            Animal localAnimal = animalList.get(animal.getId());
            localAnimal.merge(animal);
        }

        return animal;
    }
}
