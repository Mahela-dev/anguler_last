package com.oop.event_ticket_system.dto;

public class TicketResponse {
    private int currentTickets; // Current number of tickets
    private String message; // Message to be displayed

    // Constructor
    public TicketResponse(int currentTickets, String message) {
        this.currentTickets = currentTickets;
        this.message = message;
    }

    public int getCurrentTickets() {
        return currentTickets;
    }

    public void setCurrentTickets(int currentTickets) {
        this.currentTickets = currentTickets;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
