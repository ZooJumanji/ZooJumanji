package animal.repository;

import animal.Entity.Animal;

import java.util.List;

/**
 * Created by isher on 20/06/2016.
 */
public interface IAnimalRepository {

    List<Animal> GetList();
    Animal GetListById(Integer id);
    Animal CreateAndUpdate(Animal animal);



}
