package enclosure;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Enclosure {

    protected int id;
    protected String name;
    protected int max;
    protected EnclosureType type;

    private static int currentId = 0;

    private static int getCurrentId() {
        currentId++;
        return currentId;
    }

    public int getId() {
        return id;
    }

    public Enclosure setId(int id) {
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

    public String getName() {
        return name;
    }

    public Enclosure setName(String name) {
        this.name = name;
        return this;
    }

    public int getMax() {
        return max;
    }

    public Enclosure setMax(int max) {
        this.max = max;
        return this;
    }

    public EnclosureType getType() {
        return type;
    }

    public Enclosure setType(EnclosureType type) {
        this.type = type;
        return this;
    }
    
    public Enclosure() {
    	
    }

    public Enclosure(String name, int max, EnclosureType type) {
        this.id = getCurrentId();
        this.name = name;
        this.max = max;
        this.type = type;
    }
    
    public void copyFrom(Enclosure enclosure) {
    	this.id = enclosure.getId();
    	this.name = enclosure.getName();
    	this.max = enclosure.getMax();
    	this.type = enclosure.getType();
    }
}
