package com.example.hb.zoojumanji.dataManager;

import com.example.hb.zoojumanji.object.Animal;
import com.example.hb.zoojumanji.object.Ticket;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Poulpy on 07/06/2016.
 */
public class TicketManager {
    // Static tickets list
    public static final Ticket TICKET_1 = new Ticket(0, Ticket.TICKET_TYPE_NORMAL, 8.50, 12, new Date(1478375387));
    public static final Ticket TICKET_2 = new Ticket(1, Ticket.TICKET_TYPE_NORMAL, 8.50, 120, new Date(1478375753));
    public static final Ticket TICKET_3 = new Ticket(2, Ticket.TICKET_TYPE_VIP, 18.00, 22, new Date(1478375852));
    public static final Ticket TICKET_4 = new Ticket(3, Ticket.TICKET_TYPE_STUDENT, 6.50, 34, new Date(1478375987));
    public static final Ticket TICKET_5 = new Ticket(4, Ticket.TICKET_TYPE_CHILD, 4.50, 16, new Date(1478375321));
    public static final Ticket TICKET_6 = new Ticket(5, Ticket.TICKET_TYPE_GROUP, 7.00, 2, new Date(1478375741));
    public static final Ticket TICKET_7 = new Ticket(6, Ticket.TICKET_TYPE_CHILD, 4.50, 3, new Date(1478375852));
    public static final Ticket TICKET_8 = new Ticket(7, Ticket.TICKET_TYPE_CHILD, 4.50, 5, new Date(1478375654));

    protected static List<Ticket> ticketsList = new ArrayList<>();

    public static List<Ticket> getTickets() {

        // Initialize list if is empty
        if (ticketsList.isEmpty()) {
            ticketsList.add(TICKET_1);
            ticketsList.add(TICKET_2);
            ticketsList.add(TICKET_3);
            ticketsList.add(TICKET_4);
            ticketsList.add(TICKET_5);
            ticketsList.add(TICKET_6);
            ticketsList.add(TICKET_7);
            ticketsList.add(TICKET_8);
        }

        return ticketsList;
    }

    // Get Ticket from id
    public static Ticket getTicket(int id) {
        List<Ticket> list = getTickets();
        for (Ticket ticket : list) {
            if (ticket.getId() == id) {
                return ticket;
            }
        }

        throw new IllegalArgumentException("Unknown ticket");
    }
}
