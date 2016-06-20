package rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import enclosures.Enclosure;
import enclosures.EnclosureType;

@Path("/enclosures")
public class EnclosureService {

	public static final Enclosure LION_FOSS = new Enclosure("lion foss", 2, EnclosureType.PADDOCK);
	public static final Enclosure MONKEY_CAGE = new Enclosure("Rafikki cage", 12, EnclosureType.CAGE);
	public static final Enclosure TIMON_POOL = new Enclosure("Timon pool", 4, EnclosureType.POOL);

	private static List<Enclosure> list = new ArrayList<>();

	@GET
	@Path("/")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Enclosure> getEnclosuresList() {
		
		if (list.isEmpty()) {
			list.add(LION_FOSS);
			list.add(MONKEY_CAGE);
			list.add(TIMON_POOL);
		}
		
		return list;
	}
	public List<Enclosure> searchEnclosureByName(String subname) {
		
		List<Enclosure> enclosures = new ArrayList<>();
		
		for (Enclosure enclosure : list) {
			if (enclosure.getName().contains(subname)) {
				enclosures.add(enclosure);
			}
		}
		
		return enclosures;
	}
	
	public Enclosure searchEnclosureById(int id) {
		
		for (Enclosure enclosure : list) {
			if (enclosure.getId() == id) {
				return enclosure;
			}
		}
		
		return null;
	}
	
	@GET
	@Path("/{name:[a-zA-Z]+}")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Enclosure> getEnclosureByName(@PathParam("name") String subname) {
		
		return searchEnclosureByName(subname);
	}
	
	@GET
	@Path("/{id:[0-9]+}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Enclosure getEnclosureById(@PathParam("id") int id) {
		
		return searchEnclosureById(id);
	}
	
	@PUT
	@Path("/{id:[0-9]+}")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response modifyEnclosureById(Enclosure enclosure, @PathParam("id") int id) {
		
		Enclosure realEnclosure = searchEnclosureById(id);
		
		if (realEnclosure == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		if (realEnclosure.getId() != enclosure.getId()) {
			return Response.status(Status.CONFLICT).build();
		}
		
		realEnclosure.copyFrom(enclosure);
		return Response.status(Status.ACCEPTED).build();
	}
	
	@DELETE
	@Path("/{id:[0-9]+}")
	public Response deleteEnclosureById(@PathParam("id") int id) {
		
		Enclosure enclosure = searchEnclosureById(id);
		
		if (enclosure == null) {
			return Response.status(Status.REQUESTED_RANGE_NOT_SATISFIABLE).build();
		}
		
		list.remove(enclosure);
		
		return Response.status(Status.ACCEPTED).build();
	}
	
	@POST
	@Path("/new")
	public Response addEnclosure(Enclosure enclosure) {
		
		if (enclosure == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		if (searchEnclosureById(enclosure.getId()) != null) {
			return Response.status(Status.UNAUTHORIZED).build();
		}
		
		list.add(enclosure);	
		return Response.status(Status.ACCEPTED).build();
	}
}
