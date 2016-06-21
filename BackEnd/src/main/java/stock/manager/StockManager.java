package stock.manager;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import rest.WebServiceResponse;
import stock.Stock;
import stock.StockType;
import stock.StockUnity;

@Stateless
public class StockManager implements IStockManager {

	// static Stock list
    public static final Stock MEAT = new Stock(StockType.MEAT, 82, 100, StockUnity.PIECE);
    public static final Stock APPLE = new Stock(StockType.APPLE, 3, 80, StockUnity.KILO);
    public static final Stock BANANA = new Stock(StockType.BANANA, 50, 80, StockUnity.PIECE);
    public static final Stock BUG = new Stock(StockType.BUG, 400, 400, StockUnity.GRAMME);
    public static final Stock POPCORN = new Stock(StockType.POPCORN, 1223, 1500, StockUnity.KILO);

	private static List<Stock> list = new ArrayList<>();

	@Override
	public List<Stock> getAll() {
		
		if (list.isEmpty()) {
			list.add(MEAT);
			list.add(APPLE);
			list.add(BANANA);
			list.add(BUG);
			list.add(POPCORN);
		}
		
		return list;
	}

	@Override
	public Stock get(int id) {
		for (Stock stock : list) {
			if (stock.getId() == id) {
				return stock;
			}
		}
		
		return null;
	}

	@Override
	public WebServiceResponse add(Stock stock) {
		if (get(stock.getId()) != null) {
			return WebServiceResponse.UNAUTHORIZED;
		}
		
		list.add(stock);	
		return WebServiceResponse.ACCEPTED;
	}

	@Override
	public WebServiceResponse modify(Stock stock) {
		
		Stock realStock = get(stock.getId());
		
		if (realStock == null) {
			return WebServiceResponse.REQUESTED_RANGE_NOT_SATISFIABLE;
		}
		
		realStock.copyFrom(stock);
		return WebServiceResponse.ACCEPTED;
	}

	@Override
	public WebServiceResponse delete(int id) {
		Stock stock = get(id);
		
		if (stock == null) {
			return WebServiceResponse.REQUESTED_RANGE_NOT_SATISFIABLE;
		}
		
		list.remove(stock);
		return WebServiceResponse.ACCEPTED;
	}

}
