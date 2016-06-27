package enclosure;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Enclosure {

	@Id
	@GeneratedValue
    protected long id;
	
    protected String name;
    protected int max;
    protected EnclosureType type;

    public long getId() {
        return id;
    }

    public Enclosure setId(int id) {
   		this.id = id;
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
    	super();
    }

    public Enclosure(String name, int max, EnclosureType type) {
        this.id = 0;
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
    
    public boolean isValid() {
    	if (id < 1) {
    		return false;
    	}
    	if (max < 0) {
    		return false;
    	}
    	if (name == null) {
    		return false;
    	}
    	if (type == null) {
    		return false;
    	}
    	
    	return true;
    }
}
