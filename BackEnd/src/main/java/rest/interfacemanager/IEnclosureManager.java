package rest.interfacemanager;

import java.util.List;

import enclosure.Enclosure;
import rest.WebServiceResponse;

public interface IEnclosureManager {
	
	public List<Enclosure> getAll(); 

	public List<Enclosure> getByName(String subname);

	public Enclosure get(int id); 
	
	public WebServiceResponse add(Enclosure enclosure); 

	public WebServiceResponse modify(Enclosure enclosure); 

	public WebServiceResponse delete(int id); 
}
