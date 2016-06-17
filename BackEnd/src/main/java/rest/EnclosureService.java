package rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import enclosures.Enclosure;
import enclosures.EnclosureType;

@Path("/")
public class EnclosureService {

	public static final Enclosure LION_FOSS = new Enclosure("lion foss", 2, EnclosureType.PADDOCK);
	public static final Enclosure MONKEY_CAGE = new Enclosure("Rafikki cage", 12, EnclosureType.CAGE);
	public static final Enclosure TIMON_POOL = new Enclosure("Timon pool", 4, EnclosureType.POOL);

	private static List<Enclosure> list = new ArrayList<>();

	@GET
	@Path("enclosures")
	@Produces({ "application/json" })
	public List<Enclosure> getEnclosuresList() {
		
		if (list.isEmpty()) {
			list.add(LION_FOSS);
			list.add(MONKEY_CAGE);
			list.add(TIMON_POOL);
		}
		
		return list;
	}
}
