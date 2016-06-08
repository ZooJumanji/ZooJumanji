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

    public static final int DAY_IN_MILLISECONDS = 1000 * 60 * 60 * 24;
    public static final Date TODAY = new Date(System.currentTimeMillis());
    public static final Date YESTERDAY = new Date(System.currentTimeMillis() - DAY_IN_MILLISECONDS);
    public static final Date BEFORE_YESTERDAY = new Date(System.currentTimeMillis() - 2 * DAY_IN_MILLISECONDS);

    public static final Ticket TICKET_NORMAL_TODAY = new Ticket(0, Ticket.TICKET_TYPE_NORMAL, 8.50, 12, TODAY);
    public static final Ticket TICKET_NORMAL_YESTERDAY = new Ticket(1, Ticket.TICKET_TYPE_NORMAL, 8.50, 120, YESTERDAY);
    public static final Ticket TICKET_VIP_TODAY = new Ticket(2, Ticket.TICKET_TYPE_VIP, 18.00, 22, TODAY);
    public static final Ticket TICKET_STUDENT_TODAY = new Ticket(3, Ticket.TICKET_TYPE_STUDENT, 6.50, 34, TODAY);
    public static final Ticket TICKET_GROUP_YESTERDAY = new Ticket(4, Ticket.TICKET_TYPE_GROUP, 7.00, 2, YESTERDAY);
    public static final Ticket TICKET_CHILD_TODAY = new Ticket(5, Ticket.TICKET_TYPE_CHILD, 4.50, 16, TODAY);
    public static final Ticket TICKET_CHILD_YESTERDAY = new Ticket(6, Ticket.TICKET_TYPE_CHILD, 4.50, 3, YESTERDAY);
    public static final Ticket TICKET_CHILD_BEFORE_YESTERDAY = new Ticket(7, Ticket.TICKET_TYPE_CHILD, 4.50, 5, BEFORE_YESTERDAY);

    protected static List<Ticket> ticketsList = new ArrayList<>();

    public static List<Ticket> getTickets() {

        // Initialize list if is empty
        if (ticketsList.isEmpty()) {
            ticketsList.add(TICKET_NORMAL_TODAY);
            ticketsList.add(TICKET_NORMAL_YESTERDAY);
            ticketsList.add(TICKET_VIP_TODAY);
            ticketsList.add(TICKET_STUDENT_TODAY);
            ticketsList.add(TICKET_GROUP_YESTERDAY);
            ticketsList.add(TICKET_CHILD_TODAY);
            ticketsList.add(TICKET_CHILD_YESTERDAY);
            ticketsList.add(TICKET_CHILD_BEFORE_YESTERDAY);
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
