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
            System.out.println("Parking ticket generated successfully!");
        } catch (ParkingException e) {
            System.out.println(e.getMessage());
        }
    }

    public void displayAllParkingTickets() {
        try {
            List<ParkingTicket> parkingTickets = parkingTicketService.getAllParkingTickets();
            for (ParkingTicket ticket : parkingTickets) {
                System.out.println("Ticket ID: " + ticket.getTicketId() + " - Vehicle ID: " + ticket.getVehicleId());
            }
        } catch (ParkingException e) {
            System.out.println(e.getMessage());
        }
    }
}