package enclosure.manager;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import enclosure.Enclosure;
import enclosure.EnclosureType;
import rest.WebServiceResponse;
import rest.interfacemanager.IEnclosureManager;

@Stateless
public class EnclosureManager implements IEnclosureManager {

	// static Enclosure list
	public static final Enclosure LION_FOSS = new Enclosure("lion foss", 2, EnclosureType.PADDOCK);
	public static final Enclosure MONKEY_CAGE = new Enclosure("Rafikki cage", 12, EnclosureType.CAGE);
	public static final Enclosure TIMON_POOL = new Enclosure("Timon pool", 4, EnclosureType.POOL);

	private static List<Enclosure> list = new ArrayList<>();
	private static boolean initialize = false;

	@Override
	public List<Enclosure> getAll() {
		
		if (!initialize) {
			list.add(LION_FOSS);
			list.add(MONKEY_CAGE);
			list.add(TIMON_POOL);
			
			initialize = true;
		}
		
		return list;
	}

	@Override
	public Enclosure get(int id) {
		for (Enclosure enclosure : list) {
			if (enclosure.getId() == id) {
				return enclosure;
			}
		}
		
		return null;
	}

	@Override
	public List<Enclosure> getByName(String subname) {
		List<Enclosure> enclosures = new ArrayList<>();
		
		for (Enclosure enclosure : list) {
			if (enclosure.getName().contains(subname)) {
				enclosures.add(enclosure);
			}
		}
		
		return enclosures;
	}
	
	@Override
	public WebServiceResponse add(Enclosure enclosure) {
		if (get(enclosure.getId()) != null) {
			return WebServiceResponse.UNAUTHORIZED;
		}
		
		list.add(enclosure);	
		return WebServiceResponse.ACCEPTED;
	}

	@Override
	public WebServiceResponse modify(Enclosure enclosure) {
		
		Enclosure realEnclosure = get(enclosure.getId());
		
		if (realEnclosure == null) {
			return WebServiceResponse.REQUESTED_RANGE_NOT_SATISFIABLE;
		}
		
		realEnclosure.copyFrom(enclosure);
		return WebServiceResponse.ACCEPTED;
	}

	@Override
	public WebServiceResponse delete(int id) {
		Enclosure enclosure = get(id);
		
		if (enclosure == null) {
			return WebServiceResponse.REQUESTED_RANGE_NOT_SATISFIABLE;
		}
		
		list.remove(enclosure);
		return WebServiceResponse.ACCEPTED;
	}

}
