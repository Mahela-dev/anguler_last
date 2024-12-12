package com.oop.event_ticket_system.cli;

import com.oop.event_ticket_system.domain.TicketPool;

import java.util.Scanner;

public class Customer implements Runnable{
    private TicketConfig ticketConfig;
    private TicketPool ticketPool;
    private int customer;

    public int getCustomer() {
        return customer;
    }

    public void setCustomer(int customer) {
        this.customer = customer;
    }

    public void addCustomer(Scanner scanner){
        System.out.println("Enter your customers: ");
        int cusNum =scanner.nextInt();
        setCustomer(cusNum);
    }

    @Override
    public void run(){

        ticketPool.buyTicket(customer,ticketConfig.getCustomerRetrievalRate());


    }
}

