package com.oop.event_ticket_system.cli;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TicketConfig {
    private transient  Scanner scanner;
    private int totalTickets;
    private int maxTicketCapacity;
    private int ticketReleaseRate;
    private int customerRetrievalRate;

    // Existing configuration logic
    public void configureSystem(Scanner scanner) {
        System.out.println("Enter total tickets to be produced:");
        totalTickets = getValidatedInput(scanner);

        System.out.println("Enter ticket release rate:");
        ticketReleaseRate = getValidatedInput(scanner);

        System.out.println("Enter customer retrieval rate:");
        customerRetrievalRate = getValidatedInput(scanner);

        System.out.println("Enter max ticket capacity:");
        maxTicketCapacity = getValidatedInput(scanner);
    }

    public int getValidatedInput(Scanner scanner) {
        int input = -1;
        while (input < 0) {
            try {
                input = scanner.nextInt();
                if (input < 0) {
                    System.out.println("Please enter a non-negative number:");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number:");
                scanner.nextLine(); // Clear the buffer
            }
        }
        return input;
    }

    // Getters and Setters with Validation
    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        if (totalTickets <= 0) {
            throw new IllegalArgumentException("Total tickets must be greater than 0.");
        }
        this.totalTickets = totalTickets;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public void setMaxTicketCapacity(int maxTicketCapacity) {
        if (maxTicketCapacity <= 0 || maxTicketCapacity > totalTickets) {
            throw new IllegalArgumentException("Max ticket capacity must be between 1 and total tickets.");
        }
        this.maxTicketCapacity = maxTicketCapacity;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public void setTicketReleaseRate(int ticketReleaseRate) {
        if (ticketReleaseRate <= 0 || ticketReleaseRate > maxTicketCapacity) {
            throw new IllegalArgumentException("Ticket release rate must be between 1 and max ticket capacity.");
        }
        this.ticketReleaseRate = ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        if (customerRetrievalRate <= 0 || customerRetrievalRate > maxTicketCapacity) {
            throw new IllegalArgumentException("Customer retrieval rate must be between 1 and max ticket capacity.");
        }
        this.customerRetrievalRate = customerRetrievalRate;
    }




    public void saveToFile(String filename) {
        Gson gson = new Gson();
        File file = new File(filename);

        try {
            if (file.exists()) {
                System.out.println("File exists. Updating...");
            } else {
                System.out.println("File does not exist. Creating a new one...");
            }

            try (FileWriter fileWriter = new FileWriter(file)) {
                gson.toJson(this, fileWriter);
                System.out.println("File saved successfully: " + filename);
            }
        } catch (IOException e) {
            System.err.println("Failed to save file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static TicketConfig loadFromFile(String filename) {
        Gson gson = new Gson();
        try (FileReader fileReader = new FileReader(filename);){
            return gson.fromJson(fileReader, TicketConfig.class);
        }catch (IOException e) {
            System.out.println("Error while Reading from file");
        }
        return null;
    }



}

