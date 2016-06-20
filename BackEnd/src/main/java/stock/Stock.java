package stock;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Stock {

    protected int id;
    protected StockType type;
    protected int quantity;
    protected int capacity;
    protected StockUnity unity;

    private static int currentId = 0;

    private static int getCurrentId() {
        currentId++;
        return currentId;
    }

    public Stock setId(int id) {
    	if (id > currentId) {
    		currentId = id + 1;
    	}
    	
    	if (id == 0) {
    		this.id = getCurrentId();
    	}
    	else {
    		this.id = id;
    	}
    	
        return this;
    }

    public int getId() {
        return id;
    }

    public StockType getType() {
        return type;
    }

    public Stock setType(StockType type) {
        this.type = type;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public Stock setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public int getCapacity() {
        return capacity;
    }

    public Stock setCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    public StockUnity getUnity() {
        return unity;
    }

    public Stock setUnity(StockUnity unity) {
        this.unity = unity;
        return this;
    }
    
    public Stock() {
    	
    }

    public Stock(StockType type, int quantity, int capacity, StockUnity unity) {
        this.id = getCurrentId();
        this.type = type;
        this.quantity = quantity;
        this.capacity = capacity;
        this.unity = unity;
    }

    
    public void copyFrom(Stock stock) {
    	this.id = stock.getId();
    	this.type = stock.getType();
    	this.quantity = stock.getQuantity();
    	this.capacity = stock.getCapacity();
    	this.unity = stock.getUnity();
    }
}
