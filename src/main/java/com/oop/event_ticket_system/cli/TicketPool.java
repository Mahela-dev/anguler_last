package com.oop.event_ticket_system.cli;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TicketPool {
    private TicketConfig ticketConfig;
    private int releaseTicketCount;
    private int buyTicketCount;
    private final List<String> tickets;

    public TicketPool(TicketConfig  ticketConfig, int buyTicketCount, int releaseTicketCount) {
        this.ticketConfig = ticketConfig;
        this.tickets =  Collections.synchronizedList(new ArrayList<>());;
        this.buyTicketCount = 0;
        this.releaseTicketCount = 0;
    }

    public TicketConfig getTicketConfig() {
        return ticketConfig;
    }

    public void setTicketConfig(TicketConfig ticketConfig) {
        this.ticketConfig = ticketConfig;
    }

    public int getReleaseTicketCount() {
        return releaseTicketCount;
    }

    public void setReleaseTicketCount(int releaseTicketCount) {
        this.releaseTicketCount = releaseTicketCount;
    }

    public int getBuyTicketCount() {
        return buyTicketCount;
    }

    public void setBuyTicketCount(int buyTicketCount) {
        this.buyTicketCount = buyTicketCount;
    }

    public String addTicket(int vendor, int ticket) {
        if (releaseTicketCount >= ticketConfig.getTotalTickets()) {
            return "Tickets have already reached the maximum ticket limit.";
        }
        if (ticket > ticketConfig.getTotalTickets() - releaseTicketCount) {
            return "Ticket quantity exceeds the remaining available tickets.";
        }

        int ticketsToAdd = Math.min(ticket, ticketConfig.getMaxTicketCapacity() - tickets.size());
        if (ticketsToAdd <= 0) {
            return "Released tickets reached the maximum capacity of the pool. Please wait...";
        }

        for (int i = 0; i < ticketsToAdd; i++) {
            String ticketData = "Ticket number: " + (tickets.size() + 1) + " by vendor: " + vendor;
            tickets.add(ticketData);
        }

        setReleaseTicketCount(releaseTicketCount + ticketsToAdd);

        return ticketsToAdd + " ticket(s) added to the pool by: " + vendor + "Total ticket is : "+getReleaseTicketCount();
    }

    public String buyTicket(int customer, int ticket) {
        if (tickets.isEmpty()) {
            return "No tickets available to purchase.";
        }

        int ticketsToBuy = Math.min(ticket, tickets.size()); // Determine the number of tickets that can be bought
        StringBuilder purchasedTickets = new StringBuilder();

        for (int i = 0; i < ticketsToBuy; i++) {
            String purchasedTicket = tickets.remove(0); // Remove the ticket from the pool (FIFO)
            purchasedTickets.append(purchasedTicket).append("\n");
        }

        setBuyTicketCount(getBuyTicketCount() + ticketsToBuy); // Update the count of bought tickets

        return "Customer :" + customer + " successfull y purchased ." + ticketsToBuy + " ticket(s):\n" + purchasedTickets;
    }

}


