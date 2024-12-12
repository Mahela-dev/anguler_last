package com.oop.event_ticket_system.cli;

import java.util.Scanner;

public class Vendor implements Runnable{
    private TicketConfig ticketConfig;
    private TicketPool ticketPool;
    private int vendor;

    public Vendor() {
        this.ticketConfig = ticketConfig;
        this.vendor = vendor;
        this.ticketPool = ticketPool;
    }

    public int getvendor() {
        return vendor;
    }

    public void setvendor(int vendor) {
        this.vendor = vendor;
    }

    public void addvendor(Scanner scanner){
        System.out.println("Enter your venders: ");
        int venNum =scanner.nextInt();
        setvendor(venNum);
    }

    @Override
    public void run(){

        ticketPool.addTicket(vendor,ticketConfig.getTicketReleaseRate());


    }
}


