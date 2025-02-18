package com.parking;

import com.parking.usecase.*;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ParkingLotUseCase parkingLotUseCase = new ParkingLotUseCase();
        VehicleUseCase vehicleUseCase = new VehicleUseCase();
        UserUseCase userUseCase = new UserUseCase();
        ParkingTicketUseCase parkingTicketUseCase = new ParkingTicketUseCase();
        PaymentUseCase paymentUseCase = new PaymentUseCase();
        Scanner scanner = new Scanner(System.in);
  //      userUseCase.login();

        while (true) {
            // Display the menu
            System.out.println("===== Parking Management System =====");
            System.out.println("1. Add Parking Lot");
            System.out.println("2. Display All Parking Lots");
            System.out.println("3. Park Vehicle");
            System.out.println("4. Display All Vehicles");
            System.out.println("5. Add User");
            System.out.println("6. Display All Users");
            System.out.println("7. Generate Parking Ticket");
            System.out.println("8. Display All Parking Tickets");
            System.out.println("9. Make Payment");
            System.out.println("10. Display All Payments");
            System.out.println("11. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    parkingLotUseCase.createParkingLot();
                    break;
                case 2:
                    parkingLotUseCase.displayAllParkingLots();
                    break;
                case 3:
                    vehicleUseCase.parkVehicle();
                    break;
                case 4:
                    vehicleUseCase.displayAllVehicles();
                    break;
                case 5:
                    userUseCase.addUser();
                    break;
                case 6:
                    userUseCase.displayAllUsers();
                    break;
                case 7:
                    parkingTicketUseCase.generateParkingTicket();
                    break;
                case 8:
                    parkingTicketUseCase.displayAllParkingTickets();
                    break;
                case 9:
                    paymentUseCase.makePayment();
                    break;
                case 10:
                    paymentUseCase.displayAllPayments();
                    break;
                case 11:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }

            // Display the menu again after each operation
            System.out.println("\nPress Enter to continue...");
            scanner.nextLine(); // Wait for user input
        }
    }
}