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
import stock.Stock;
import stock.StockType;
import stock.StockUnity;

@Path("/stocks")
public class StockService {

    // static Stock list
    public static final Stock MEAT = new Stock(StockType.MEAT, 82, 100, StockUnity.PIECE);
    public static final Stock APPLE = new Stock(StockType.APPLE, 3, 80, StockUnity.KILO);
    public static final Stock BANANA = new Stock(StockType.BANANA, 50, 80, StockUnity.PIECE);
    public static final Stock BUG = new Stock(StockType.BUG, 400, 400, StockUnity.GRAMME);
    public static final Stock POPCORN = new Stock(StockType.POPCORN, 1223, 1500, StockUnity.KILO);

	private static List<Stock> list = new ArrayList<>();

	@GET
	@Path("/")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Stock> getStocksList() {
		
		if (list.isEmpty()) {
			list.add(MEAT);
			list.add(APPLE);
			list.add(BANANA);
			list.add(BUG);
			list.add(POPCORN);
		}
		
		return list;
	}
	
	public Stock searchStockById(int id) {
		
		for (Stock stock : list) {
			if (stock.getId() == id) {
				return stock;
			}
		}
		
		return null;
	}
	
	@GET
	@Path("/{id:[0-9]+}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Stock getStockById(@PathParam("id") int id) {
		
		return searchStockById(id);
	}
	
	@PUT
	@Path("/{id:[0-9]+}")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response modifyStockById(Stock stock, @PathParam("id") int id) {
		
		Stock realStock = searchStockById(id);
		
		if (realStock == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		if (realStock.getId() != stock.getId()) {
			return Response.status(Status.CONFLICT).build();
		}
		
		realStock.copyFrom(stock);
		return Response.status(Status.ACCEPTED).build();
	}
	
	@DELETE
	@Path("/{id:[0-9]+}")
	public Response deleteStockById(@PathParam("id") int id) {
		
		Stock stock = searchStockById(id);
		
		if (stock == null) {
			return Response.status(Status.REQUESTED_RANGE_NOT_SATISFIABLE).build();
		}
		
		list.remove(stock);
		
		return Response.status(Status.ACCEPTED).build();
	}
	
	@POST
	@Path("/new")
	public Response addStock(Stock stock) {
		
		if (stock == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		if (searchStockById(stock.getId()) != null) {
			return Response.status(Status.UNAUTHORIZED).build();
		}
		
		list.add(stock);	
		return Response.status(Status.ACCEPTED).build();
	}
}
