package com.example.hb.zoojumanji.ticket;

import com.example.hb.zoojumanji.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by hb on 07/06/2016.
 */
public class Ticket {

    protected int id;
    protected TicketType type;
    protected double price;
    protected int quantity;
    protected Date date;
    protected List<Integer> privileges = new ArrayList<>();

    private static int currentId = 0;

    private static int getCurrentId() {
        currentId++;
        return currentId;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public TicketType getType() {
        return type;
    }

    public Date getDate() {
        return date;
    }

    public List<Integer> getPrivileges() {
        return privileges;
    }

    public Ticket addPrivilege(int privilege) {
        if (!privileges.contains(privilege)) {
            privileges.add(privilege);
        }
        return this;
    }

    public Ticket removePrivilege(int privilege) {
        if (privileges.contains(privilege)) {
            privileges.remove(privilege);
        }
        return this;
    }

    public Ticket(TicketType type, double price, int quantity, Date date) {
        this.id = getCurrentId();
        this.type = type;
        this.price = price;
        this.quantity = quantity;
        this.date = date;
    }

    public String getDateString() {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(date);
    }
}
