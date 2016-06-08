package com.example.hb.zoojumanji.ticket.adapter.ceil;

import com.example.hb.zoojumanji.ticket.Ticket;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by hb on 07/06/2016.
 */
public class TicketDateCeil {

    protected int type;
    protected Date date;

    protected List<Ticket> tickets = new ArrayList<>();

    public int getType() {
        return type;
    }

    public TicketDateCeil setType(int type) {
        this.type = type;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public TicketDateCeil setDate(Date date) {
        this.date = date;
        return this;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public TicketDateCeil addTicket(Ticket ticket) {
        if (!tickets.contains(ticket)) {
            tickets.add(ticket);
        }
        return this;
    }

    public TicketDateCeil removeTicket(Ticket ticket) {
        if (tickets.contains(ticket)) {
            tickets.remove(ticket);
        }
        return this;
    }

    public TicketDateCeil(int ticketType, Date ticketDate) {
        setType(ticketType)
                .setDate(ticketDate);
    }

    public int getTicketsCount() {
        int count = 0;

        for (Ticket ticket : tickets) {
            count += ticket.getQuantity();
        }

        return count;
    }

    public String getDateString() {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(date);
    }
}
