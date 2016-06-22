package rest;

import animal.entity.Animal;
import animal.repository.IAnimalRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import java.util.List;

@Path("/animals")
public class AnimalService {

    @Inject
    IAnimalRepository repo;

    @GET
    @Produces({"application/json"})
    public List<Animal> getList() {
        return repo.getList();
    }

    @GET
    @Path("/{id:[0-9]+}")
    @Produces({"application/json"})
    public Animal getById(@PathParam("id") Integer id) {
        return repo.get(id);
    }

    @POST
    @Produces({"application/json"})
    public Animal create(Animal animal) {
        return repo.create(animal);
    }

    @PUT
    @Produces({"application/json"})
    public Animal update(Animal animal) {
        return repo.update(animal);
    }

	@DELETE
	@Path("/{id:[0-9]+}")
	public Response deleteEnclosureById(@PathParam("id") int id) {
		
		WebServiceResponse response =  repo.delete(id);
		
		if (response.equals(WebServiceResponse.REQUESTED_RANGE_NOT_SATISFIABLE)) {
			return Response.status(Status.REQUESTED_RANGE_NOT_SATISFIABLE).build();
		}
		
		return Response.status(Status.ACCEPTED).build();
	}
}
