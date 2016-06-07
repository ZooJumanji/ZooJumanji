package com.example.hb.zoojumanji.object;

/**
 * Created by hb on 07/06/2016.
 */
public class Enclosure {

    /*
    public static final int ENCLOSURE_TYPE_CAGE = "cage";
    public static final int ENCLOSURE_TYPE_PADDOCK = "paddock";
    public static final int ENCLOSURE_TYPE_POOL = "pool";
    public static final int ENCLOSURE_TYPE_AQUARIUM = "aquarium";
    public static final int ENCLOSURE_TYPE_VIVARIUM = "vivarium";
    //*/

    protected int id;
    protected String name;
    protected int nombreMax;
    protected int type;

    public int getId() {
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

    public int getNombreMax() {
        return nombreMax;
    }

    public Enclosure setNombreMax(int nombreMax) {
        this.nombreMax = nombreMax;
        return this;
    }

    public int getType() {
        return type;
    }

    public Enclosure setType(int type) {
        this.type = type;
        return this;
    }

    public Enclosure(int id, String name, int nombreMax, int type) {
        setId(id)
            .setName(name)
            .setNombreMax(nombreMax)
            .setType(type);
    }
}
