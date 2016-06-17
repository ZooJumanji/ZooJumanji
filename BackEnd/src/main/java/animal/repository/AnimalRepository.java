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

    /**
     * Retourne la liste des animaux
     * @return List<Animal>
     */
    public List<Animal> GetList() {
        // Static animals list
        Animal SIMBA = new Animal("Simba", 8, AnimalSex.MALE, AnimalSpecies.LION, AnimalType.CARNIVOROUS);
        Animal TIMON = new Animal("Timon", 12, AnimalSex.MALE, AnimalSpecies.SURICATE, AnimalType.INSECTIVOROUS);
        Animal PUMBA = new Animal("Pumba", 15, AnimalSex.MALE, AnimalSpecies.WARTHOG, AnimalType.OMNIVOROUS);
        Animal NALA = new Animal("Nala", 8, AnimalSex.FEMALE, AnimalSpecies.LION, AnimalType.CARNIVOROUS);
        Animal RAFIKKI = new Animal("Rafikki", 82, AnimalSex.MALE, AnimalSpecies.MONKEY, AnimalType.OMNIVOROUS);

        List<Animal> animalsList = new ArrayList<>();
        if (animalsList.isEmpty()) {
            animalsList.add(SIMBA);
            animalsList.add(TIMON);
            animalsList.add(PUMBA);
            animalsList.add(NALA);
            animalsList.add(RAFIKKI);
        }

        return animalsList;
    }
}
