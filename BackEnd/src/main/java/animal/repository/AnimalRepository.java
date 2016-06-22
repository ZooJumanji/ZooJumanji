package animal.repository;

import javax.ejb.Stateless;

import animal.entity.Animal;
import animal.entity.AnimalSex;
import animal.entity.AnimalSpecies;
import animal.entity.AnimalType;
import rest.WebServiceResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by isher on 17/06/2016.
 */
@Stateless
public class AnimalRepository implements IAnimalRepository {

    // Static animals list
    public static final Animal SIMBA = new Animal("Simba", 8, AnimalSex.MALE, AnimalSpecies.LION, AnimalType.CARNIVOROUS);
    public static final Animal TIMON = new Animal("Timon", 12, AnimalSex.MALE, AnimalSpecies.SURICATE, AnimalType.INSECTIVOROUS);
    public static final Animal PUMBA = new Animal("Pumba", 15, AnimalSex.MALE, AnimalSpecies.WARTHOG, AnimalType.OMNIVOROUS);
    public static final Animal NALA = new Animal("Nala", 8, AnimalSex.FEMALE, AnimalSpecies.LION, AnimalType.CARNIVOROUS);
    public static final Animal RAFIKKI = new Animal("Rafikki", 82, AnimalSex.MALE, AnimalSpecies.MONKEY, AnimalType.OMNIVOROUS);

    private static List<Animal> animalsList  = new ArrayList<>();
	private static boolean initialize = false;

    /**
     * Retourne la liste des animaux
     *
     * @return List<Animal>
     */
    public List<Animal> getList() {
        if (!initialize) {
            animalsList.add(SIMBA);
            animalsList.add(TIMON);
            animalsList.add(PUMBA);
            animalsList.add(NALA);
            animalsList.add(RAFIKKI);
            
            initialize = true;
        }

        return animalsList;
    }

    public Animal get(Integer id) {
		for (Animal animal : animalsList) {
			if (animal.getId() == id) {
				return animal;
			}
		}
        return null;
    }

	@Override
	public List<Animal> getByName(String subname) {
		List<Animal> animals = new ArrayList<>();
		
		for (Animal animal : animalsList) {
			if (animal.getName().contains(subname)) {
				animals.add(animal);
			}
		}
		
		return animals;
	}

	@Override
	public Animal create(Animal animal) {
		
		animalsList.add(animal);	
		return animal;
	}

	@Override
	public Animal update(Animal animal) {
		
        Animal localAnimal = get(animal.getId());
		
        localAnimal.merge(animal);
		
		return animal;
	}

	@Override
	public WebServiceResponse delete(int id) {
		Animal animal = get(id);
		
		if (animal == null) {
			return WebServiceResponse.REQUESTED_RANGE_NOT_SATISFIABLE;
		}
		
		animalsList.remove(animal);
		return WebServiceResponse.ACCEPTED;
	}
}
