package com.parking.usecase;

import com.parking.entity.ParkingTicket;
import com.parking.service.ParkingTicketService;
import com.parking.service.ParkingTicketServiceImpl;
import com.parking.exception.ParkingException;
import java.util.List;
import java.util.Scanner;

public class ParkingTicketUseCase {
    private ParkingTicketService parkingTicketService = new ParkingTicketServiceImpl();
    private Scanner scanner = new Scanner(System.in);

    public void generateParkingTicket() {
        System.out.print("Enter vehicle ID: ");
        int vehicleId = scanner.nextInt();
        System.out.print("Enter parking lot ID: ");
        int lotId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        ParkingTicket parkingTicket = new ParkingTicket(vehicleId, lotId);
        try {
            parkingTicketService.addParkingTicket(parkingTicket);

            // Fetch the ticket details after insertion
            ParkingTicket savedTicket = parkingTicketService.getParkingTicketById(vehicleId);
            if (savedTicket != null) {
                System.out.println("Parking ticket generated successfully!");
                System.out.println("Ticket ID: " + savedTicket.getTicketId());
            } else {
                System.out.println("Parking ticket generated, but unable to fetch ticket ID.");
            }

        } catch (ParkingException e) {
            System.out.println("Error generating parking ticket: " + e.getMessage());
        }
    }

    public void displayAllParkingTickets() {
        try {
            List<ParkingTicket> parkingTickets = parkingTicketService.getAllParkingTickets();
            if (parkingTickets.isEmpty()) {
                System.out.println("No parking tickets found.");
                return;
            }
            System.out.println("===== Parking Tickets =====");
            for (ParkingTicket ticket : parkingTickets) {
                System.out.println("Ticket ID: " + ticket.getTicketId() +
                        " | Vehicle ID: " + ticket.getVehicleId() +
                        " | Parking Lot ID: " + ticket.getLotId());
            }
        } catch (ParkingException e) {
            System.out.println("Error fetching parking tickets: " + e.getMessage());
        }
    }
    public void findParkingTicketById() {
        System.out.print("Enter Parking Ticket ID to find: ");
        int ticketId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            ParkingTicket ticket = parkingTicketService.getParkingTicketById(ticketId);
            if (ticket != null) {
                System.out.println("Parking Ticket found:");
                System.out.println("Ticket ID: " + ticket.getTicketId());
                System.out.println("Vehicle ID: " + ticket.getVehicleId());
                System.out.println("Parking Lot ID: " + ticket.getLotId());
                System.out.println("Entry Time: " + ticket.getEntryTime());
            } else {
                System.out.println("Parking Ticket not found!");
            }
        } catch (ParkingException e) {
            System.out.println("Error finding parking ticket: " + e.getMessage());
        }
    }

}
