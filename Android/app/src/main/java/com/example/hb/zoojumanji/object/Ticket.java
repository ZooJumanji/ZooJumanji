package com.example.hb.zoojumanji.object;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hb on 07/06/2016.
 */
public class Ticket {

    protected int id;
    protected double price;
    protected int quantity;
    protected List<String> privileges = new ArrayList<>();

    public int getId() {
        return id;
    }

    public Ticket setId(int id) {
        this.id = id;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public Ticket setPrice(double price) {
        this.price = price;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public Ticket setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public List<String> getPrivileges() {
        return privileges;
    }

    public Ticket addPrivilege(String privilege) {
        if (!privileges.contains(privilege)) {
            privileges.add(privilege);
        }
        return this;
    }

    public Ticket removePrivilege(String privilege) {
        if (privileges.contains(privilege)) {
            privileges.remove(privilege);
        }
        return this;
    }

}
