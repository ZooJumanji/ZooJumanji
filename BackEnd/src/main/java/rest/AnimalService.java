package rest;

import animal.Entity.Animal;
import animal.repository.IAnimalRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

@Path("/animaux")
public class AnimalService {

    @Inject
    IAnimalRepository repo;

    //AnimalRepository repo = AnimalRepository.getInstance();

    @GET
    @Produces({"application/json"})
    public List<Animal> getList() {
        List<Animal> maList = repo.GetList();
        return maList;
    }

    @GET
    @Path("/{id:[0-9]+}")
    @Produces({"application/json"})
    public Animal getById(@PathParam("id") Integer id) {
        return repo.GetListById(id);
    }

    @POST
    @Produces({"application/json"})
    public Animal Create(Animal animal) {
        return repo.CreateAndUpdate(animal);
    }

    @PUT
    @Produces({"application/json"})
    public Animal Update(Animal animal) {
        return repo.CreateAndUpdate(animal);
    }



}
