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
		enclosureDao.persist(enclosure);
		return WebServiceResponse.ACCEPTED;		
	}

	@Override
	public WebServiceResponse modify(Enclosure enclosure) {
		
		Enclosure realEnclosure = enclosureDao.findById(enclosure.getId());
		realEnclosure.copyFrom(enclosure);
		enclosureDao.persist(realEnclosure);
		return WebServiceResponse.ACCEPTED;
	}

	@Override
	public WebServiceResponse delete(long id) {
		
		enclosureDao.remove(enclosureDao.findById(id));
		return WebServiceResponse.ACCEPTED;		
	}

}
