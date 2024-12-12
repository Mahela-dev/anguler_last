package com.oop.event_ticket_system.cli;



import com.oop.event_ticket_system.domain.TicketPool;

import java.util.Scanner;
import java.util.logging.Logger;

import static java.lang.System.exit;

    public class Cli {
        private static final Logger logger = Logger.getLogger(Cli.class.getName());
        private final transient Scanner scanner;
        private TicketConfig ticketConfig;

        public Cli() {
            scanner = new Scanner(System.in);
            ticketConfig = new TicketConfig();
        }

        public static void main(String[] args) {
            Cli cli = new Cli();
            cli.systemStart();
        }

        private void systemStart() {
            System.out.println("|-----------------------------------------------|");
            System.out.println("|  Welcome to the Ticket System Configuration!  |");
            System.out.println("|-----------------------------------------------|");

            boolean exit = false;
            while (!exit) {
                System.out.println("\n1-View Configurations\n2-Edit Configurations\n3-Start Simulation\n4-Log Out");
                int choice = -1;
                try {
                    choice = scanner.nextInt();
                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter a number between 1 and 4.");
                    scanner.nextLine(); // Clear the scanner buffer
                    continue;
                }

                switch (choice) {
                    case 1:
                        viewConfigurations();
                        break;
                    case 2:
                        editConfigurations();
                        break;
                    case 3:
                        System.out.println("Starting simulation...");
                        simulation();
                        break;
                    case 4:
                        System.out.println("Logging out...");
                        scanner.close();
                        exit(1);
                    default:
                        System.out.println("Invalid choice.");
                }
            }
        }

        private void viewConfigurations() {
            ticketConfig = TicketConfig.loadFromFile("src/main/java/config.json");
            if (ticketConfig != null) {
                System.out.println("Configuration loaded successfully:");
                System.out.println("Total Tickets: " + ticketConfig.getTotalTickets());
                System.out.println("Ticket Release Rate: " + ticketConfig.getTicketReleaseRate());
                System.out.println("Customer Retrieval Rate: " + ticketConfig.getCustomerRetrievalRate());
                System.out.println("Max Ticket Capacity: " + ticketConfig.getMaxTicketCapacity());
            } else {
                System.out.println("Failed to load configuration. Please try again.");
            }
        }

        private void editConfigurations() {
            ticketConfig.configureSystem(scanner);
            try {
                ticketConfig.saveToFile("src/main/java/config.json");
                System.out.println("Configuration saved successfully.");
                logger.info("Configuration saved successfully: " + ticketConfig);
            } catch (Exception e) {
                logger.severe("Failed to save configuration: " + e.getMessage());
            }
        }

        private void simulation(){
            Customer customer =  new Customer();
            customer.addCustomer(scanner);

            Vendor vendor = new Vendor();
            vendor.addvendor(scanner);

            TicketConfig ticketConfig1 = new TicketConfig();

            TicketPool ticketPool = new TicketPool(ticketConfig1,0,0);

            for (int i=0;i< vendor.getvendor();i++){
                Vendor vendor1 = new Vendor();
                Thread venderthread =new Thread(vendor1);
                venderthread.start();
            }


            for (int i=0;i<customer.getCustomer();i++){
                Customer customer1 = new Customer();
                Thread customerthread =new Thread(customer1);
                customerthread.start();
            }
        }
    }


