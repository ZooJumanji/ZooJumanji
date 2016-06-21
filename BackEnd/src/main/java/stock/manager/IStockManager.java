package stock.manager;

import java.util.List;

import rest.WebServiceResponse;
import stock.Stock;

public interface IStockManager {
	
	public List<Stock> getAll(); 

	public Stock get(int id); 

	public WebServiceResponse add(Stock stock); 

	public WebServiceResponse modify(Stock stock); 

	public WebServiceResponse delete(int id); 
}
