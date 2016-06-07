package com.example.hb.zoojumanji.object;

import com.example.hb.zoojumanji.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by hb on 07/06/2016.
 */
public class Ticket {

    public static final int TICKET_PRIVILEGE_FEED_TIMON = R.string.ticket_privilege_feed_timon;
    public static final int TICKET_PRIVILEGE_CARE_ENTRANCE = R.string.ticket_privilege_care_entrance;
    public static final int TICKET_PRIVILEGE_ENCLOSURE_ENTRANCE = R.string.ticket_privilege_enclosure_entrance;
    public static final int TICKET_PRIVILEGE_NURSERY_ENTRANCE = R.string.ticket_privilege_nursery_entrance;

    public static final int TICKET_TYPE_NORMAL = R.string.ticket_privilege_feed_timon;
    public static final int TICKET_TYPE_VIP = R.string.ticket_privilege_care_entrance;
    public static final int TICKET_TYPE_STUDENT = R.string.ticket_privilege_enclosure_entrance;
    public static final int TICKET_TYPE_CHILD = R.string.ticket_privilege_nursery_entrance;
    public static final int TICKET_TYPE_GROUP = R.string.ticket_privilege_nursery_entrance;

    protected int id;
    protected double price;
    protected int type;
    protected Date saleDate;
    protected List<Integer> privileges = new ArrayList<>();

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

    public int getType() {
        return type;
    }

    public Ticket setType(int type) {
        this.type = type;
        return this;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public Ticket setDate(Date date) {
        this.saleDate = date;
        return this;
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

}
