package com.example.hb.zoojumanji.ticket.manager;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Resources;

import com.example.hb.zoojumanji.R;
import com.example.hb.zoojumanji.ticket.Ticket;
import com.example.hb.zoojumanji.ticket.TicketType;
import com.example.hb.zoojumanji.ticket.service.TicketService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Poulpy (from motofondue's enclosure) on 07/06/2016.
 */
public class TicketManager {
    // Static tickets list

    public static final int DAY_IN_MILLISECONDS = 1000 * 60 * 60 * 24;
    public static final Date TODAY = new Date(System.currentTimeMillis());
    public static final Date YESTERDAY = new Date(System.currentTimeMillis() - DAY_IN_MILLISECONDS);
    public static final Date BEFORE_YESTERDAY = new Date(System.currentTimeMillis() - 2 * DAY_IN_MILLISECONDS);

    public static final Ticket TICKET_NORMAL_TODAY = new Ticket(TicketType.NORMAL, 8.50, 12, TODAY);
    public static final Ticket TICKET_NORMAL_YESTERDAY = new Ticket(TicketType.NORMAL, 8.50, 120, YESTERDAY);
    public static final Ticket TICKET_VIP_TODAY = new Ticket(TicketType.VIP, 18.00, 22, TODAY);
    public static final Ticket TICKET_STUDENT_TODAY = new Ticket(TicketType.STUDENT, 6.50, 34, TODAY);
    public static final Ticket TICKET_GROUP_YESTERDAY = new Ticket(TicketType.GROUP, 7.00, 2, YESTERDAY);
    public static final Ticket TICKET_CHILD_TODAY = new Ticket(TicketType.CHILD, 4.50, 16, TODAY);
    public static final Ticket TICKET_CHILD_YESTERDAY = new Ticket(TicketType.CHILD, 4.50, 3, YESTERDAY);
    public static final Ticket TICKET_CHILD_BEFORE_YESTERDAY = new Ticket(TicketType.CHILD, 4.50, 5, BEFORE_YESTERDAY);
    protected Context context;
    private ServiceConnection connection;

    protected static Ticket deletedTicket;
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

    // Get Tickets from type
    public static List<Ticket> getTickets(TicketType type) {
        List<Ticket> list = getTickets();
        List<Ticket> returnList = new ArrayList<>();

        for (Ticket ticket : list) {
            if (ticket.getType() == type) {
                returnList.add(ticket);
            }
        }

        return returnList;
    }

    // Get Ticket from id
    public static Ticket getTicket(int id) {
        List<Ticket> list = getTickets();
        for (Ticket ticket : list) {
            if (ticket.getId() == id) {
                return ticket;
            }
        }

        throw new IllegalArgumentException(Resources.getSystem().getString(R.string.exception_unknown_ticket));
    }

    public static void createTicket(Context context, TicketType type, Double price, Integer quantity) {
        //Creates the ticket locally
        Ticket ticket = new Ticket(type, price, quantity, new Date());
        ticketsList.add(ticket);

        //Ask the TicketService to ask the server to create the ticket
        Intent ticketServiceIntent = new Intent(context, TicketService.class);
        TicketService.startActionCreateTicket(context,ticket.getId());
    }
    public static void deleteTicket(Ticket ticket) {
        if (ticketsList.contains(ticket)) {
            ticketsList.remove(ticket);
        }

        deletedTicket = ticket;
    }

    public static void restoreTicket() {
        if (deletedTicket != null && !ticketsList.contains(deletedTicket)) {
            ticketsList.add(deletedTicket);
        }

        cleanTicket();
    }

    public static Boolean isInDeletion() {
        return deletedTicket != null;
    }

    public static void cleanTicket() {
        deletedTicket = null;
    }

    public static void modifyTicket(int id, TicketType type, Double price, Integer quantity) {
        Ticket ticket = getTicket(id);
        ticket.setType(type)
                .setPrice(price)
                .setQuantity(quantity);
    }
}
