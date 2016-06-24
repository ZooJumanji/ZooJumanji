package util;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import dao.entity.enclosure.EnclosureDao;
import enclosure.Enclosure;
import enclosure.EnclosureType;

@Singleton
@Startup
public class Fixtures {

	@Inject
	EnclosureDao enclosureDao;
	
	@PostConstruct
	public void init() {
		
		createEnclosure("lion foss", 2, EnclosureType.PADDOCK);
		createEnclosure("Rafikki cage", 12, EnclosureType.CAGE);
		createEnclosure("Timon pool", 4, EnclosureType.POOL);
	}

	private void createEnclosure(String name, int max, EnclosureType type) {
		Enclosure enclosure = new Enclosure();
		
		enclosure.setName(name);
		enclosure.setMax(max);
		enclosure.setType(type);
		
		enclosureDao.persist(enclosure);
	}
}
