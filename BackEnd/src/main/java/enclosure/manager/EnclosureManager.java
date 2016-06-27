package enclosure.manager;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import dao.entity.enclosure.EnclosureDao;
import enclosure.Enclosure;
import rest.WebServiceResponse;
import rest.interfacemanager.IEnclosureManager;

@Stateless
public class EnclosureManager implements IEnclosureManager {
	
	@Inject
	private EnclosureDao enclosureDao;

	private static List<Enclosure> list = new ArrayList<>();

	@Override
	public List<Enclosure> getAll() {
		
		return enclosureDao.findAll();
	}

	@Override
	public Enclosure get(long id) {
		return enclosureDao.findById(id);
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
		/*
		if (get(enclosure.getId()) != null) {
			return WebServiceResponse.UNAUTHORIZED;
		}
		
		list.add(enclosure);	
		return WebServiceResponse.ACCEPTED;
		/*/
		enclosureDao.persist(enclosure);
		return WebServiceResponse.ACCEPTED;		
		//*/
	}

	@Override
	public WebServiceResponse modify(Enclosure enclosure) {
		
		/*
		Enclosure realEnclosure = get(enclosure.getId());
		
		if (realEnclosure == null) {
			return WebServiceResponse.REQUESTED_RANGE_NOT_SATISFIABLE;
		}
		
		realEnclosure.copyFrom(enclosure);
		return WebServiceResponse.ACCEPTED;
		/*/
		Enclosure realEnclosure = enclosureDao.findById(enclosure.getId());
		realEnclosure.copyFrom(enclosure);
		enclosureDao.persist(realEnclosure);
		return WebServiceResponse.ACCEPTED;
		//*/
	}

	@Override
	public WebServiceResponse delete(long id) {
		
		/*
		Enclosure enclosure = get(id);
		
		if (enclosure == null) {
			return WebServiceResponse.REQUESTED_RANGE_NOT_SATISFIABLE;
		}
		
		list.remove(enclosure);
		return WebServiceResponse.ACCEPTED;
		/*/
		enclosureDao.remove(enclosureDao.findById(id));
		return WebServiceResponse.ACCEPTED;		
		//*/
	}

}
