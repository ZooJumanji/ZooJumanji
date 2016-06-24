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

	private static List<Enclosure> list = new ArrayList<>();

	@Override
	public List<Enclosure> getAll() {
		return list;
	}

	@Override
	public Enclosure get(long l) {
		for (Enclosure enclosure : list) {
			if (enclosure.getId() == l) {
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
	public WebServiceResponse delete(long id) {
		Enclosure enclosure = get(id);
		
		if (enclosure == null) {
			return WebServiceResponse.REQUESTED_RANGE_NOT_SATISFIABLE;
		}
		
		list.remove(enclosure);
		return WebServiceResponse.ACCEPTED;
	}

}
