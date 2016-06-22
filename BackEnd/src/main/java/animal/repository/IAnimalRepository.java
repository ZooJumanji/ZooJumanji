package animal.repository;

import java.util.List;

import animal.entity.Animal;
import enclosure.Enclosure;
import rest.WebServiceResponse;

/**
 * Created by isher on 20/06/2016.
 */
public interface IAnimalRepository {

    List<Animal> getList();
    
	List<Animal> getByName(String subname);
	
    Animal get(Integer id);
    
    Animal create(Animal animal);
    
    Animal update(Animal animal);
    
    WebServiceResponse delete(int id);
}
