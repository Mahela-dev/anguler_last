package com.oop.event_ticket_system.controller;

import com.oop.event_ticket_system.resources.RegisterCustomerResource;
import com.oop.event_ticket_system.resources.CustomerLoginResours;
import com.oop.event_ticket_system.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:65230")
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    // Customer service
    private final CustomerService customerService;

    // Constructor
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @PostMapping("/register") // Register a customer
    public ResponseEntity<String> registerCustomer(@RequestBody RegisterCustomerResource registerCustomerResource) {
        try {
            String result = customerService.registerCustomer(registerCustomerResource);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) { // If there is an error
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Login a customer
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody CustomerLoginResours customerLoginResours) {
        try {
            return ResponseEntity.ok(customerService.login(customerLoginResours));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // If there is an error
        }
    }

}