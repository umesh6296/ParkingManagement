package com.parking.usecase;

import com.parking.dao.UserDAO;
import com.parking.entity.User;
import com.parking.exception.ParkingException;
import com.parking.service.UserService;
import com.parking.service.UserServiceImpl;
import com.parking.usecase.ParkingLotUseCase;
import com.parking.usecase.ParkingTicketUseCase;
import com.parking.usecase.PaymentUseCase;
import com.parking.usecase.VehicleUseCase;

import java.util.Scanner;

public class UserUseCase {
    private static final Scanner scanner = new Scanner(System.in);
    private static final UserService userDAO = new UserServiceImpl();
    private static final ParkingLotUseCase parkingLotUseCase = new ParkingLotUseCase();
    private static final VehicleUseCase vehicleUseCase = new VehicleUseCase();
    private static final ParkingTicketUseCase parkingTicketUseCase = new ParkingTicketUseCase();
    private static final PaymentUseCase paymentUseCase = new PaymentUseCase();

    // Method to register a new user
    public static void registerUser() {
        System.out.println("Enter Name:");
        String name = scanner.nextLine();

        System.out.println("Enter Contact Number:");
        String contactNumber = scanner.nextLine();

        System.out.println("Enter Email:");
        String email = scanner.nextLine();

        System.out.println("Enter Password:");
        String password = scanner.nextLine();

        System.out.println("Enter Role (A for Admin, U for User):");
        String role = scanner.nextLine().toUpperCase();

        if (!role.equals("A") && !role.equals("U")) {
            System.out.println("Invalid role! Please enter 'A' for Admin or 'U' for User.");
            return;
        }

        User user = new User(name, contactNumber, email, password, role);

        try {
            userDAO.addUser(user);
            System.out.println("User registered successfully!");
        } catch (ParkingException e) {
            System.out.println("Error registering user: " + e.getMessage());
        }
    }
// Admin menu
    public static void adminMenu(User admin) {
        while (true) {
            System.out.println("\n===== Admin Menu =====");
            System.out.println("1. Add Parking Lot");
            System.out.println("2. Display All Parking Lots");
            System.out.println("3. Display All Vehicles");
            System.out.println("4. Display All Users");
            System.out.println("5. Display All Parking Tickets");
            System.out.println("6. Display All Payments");
            System.out.println("7. Logout");

            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    parkingLotUseCase.createParkingLot();
                    break;
                case 2:
                    parkingLotUseCase.displayAllParkingLots();
                    break;
                case 3:
                    vehicleUseCase.displayAllVehicles();
                    break;
                case 4:
                    viewAllUsers();
                    break;
                case 5:
                    parkingTicketUseCase.displayAllParkingTickets();
                    break;
                case 6:
                    paymentUseCase.displayAllPayments();
                    break;
                case 7:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // User menu
    public static void userMenu(User user) {
        while (true) {
            System.out.println("===== User Menu =====");
            System.out.println("1. Park Vehicle");
            System.out.println("2. Generate Parking Ticket");
            System.out.println("3. Make Payment");
            System.out.println("4. Logout");

            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.println("========Park Vehicle============");
                    vehicleUseCase.parkVehicle();
                    break;
                case 2:
                    System.out.println("========Generate Parking Ticket===========");
                    parkingTicketUseCase.generateParkingTicket();
                    break;
                case 3:
                    System.out.println("=======Make Payment========");
                    paymentUseCase.makePayment();
                    break;
                case 4:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // Method for admin to view all users
    public static void viewAllUsers() {
        try {
            userDAO.getAllUsers().forEach(user -> {
                System.out.println("User ID: " + user.getUserId());
                System.out.println("Name: " + user.getName());
                System.out.println("Contact: " + user.getContactNumber());
                System.out.println("Email: " + user.getEmail());
                System.out.println("Role: " + user.getRole());
                System.out.println("----------------------");
            });
        } catch (ParkingException e) {
            System.out.println("Error fetching users: " + e.getMessage());
        }
    }


}
