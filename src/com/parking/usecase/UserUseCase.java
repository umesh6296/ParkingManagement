package com.parking.usecase;

import com.parking.entity.User;
import com.parking.service.UserService;
import com.parking.service.UserServiceImpl;
import com.parking.exception.ParkingException;
import java.util.List;
import java.util.Scanner;

public class UserUseCase {
    private UserService userService = new UserServiceImpl();
    private Scanner scanner = new Scanner(System.in);

    public void addUser() {
        System.out.print("Enter user name: ");
        String name = scanner.nextLine();
        System.out.print("Enter contact number: ");
        String contactNumber = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter role (U for User, A for Admin): ");
        String role = scanner.nextLine();
        System.out.print("Enter vehicle ID: ");
        int vehicleId = scanner.nextInt();

        scanner.nextLine(); // Consume newline

        User user = new User(name, contactNumber, email, password, role, vehicleId);
        try {
            userService.addUser(user);
            System.out.println("User added successfully!");
        } catch (ParkingException e) {
            System.out.println(e.getMessage());
        }
    }

    public void login() {
        System.out.println("Enter your Email: ");
        String email = scanner.next();
        System.out.println("Enter password: ");
        String password = scanner.next();

        try {
            User user = userService.getUserByEmail(email, password);
            if (user != null) {
                System.out.println("Login successful!");
                if (user.getRole().equals("U")) {
                    showUserMenu();
                } else if (user.getRole().equals("A")) {
                    showAdminMenu();
                }
            } else {
                System.out.println("User not found. Would you like to add a new user? (Y/N)");
                String choice = scanner.next();
                if (choice.equalsIgnoreCase("Y")) {
                    addUser();
                }
            }
        } catch (ParkingException e) {
            System.out.println(e.getMessage());
        }
    }

    private void showUserMenu() {
        ParkingLotUseCase parkingLotUseCase = new ParkingLotUseCase();
        VehicleUseCase vehicleUseCase = new VehicleUseCase();
        UserUseCase userUseCase = new UserUseCase();
        ParkingTicketUseCase parkingTicketUseCase = new ParkingTicketUseCase();
        PaymentUseCase paymentUseCase = new PaymentUseCase();
        System.out.println("========Park Vehicle============");
            vehicleUseCase.parkVehicle();
            System.out.println("========Generate Parking Ticket===========");
            parkingTicketUseCase.generateParkingTicket();
            System.out.println("=======Make Payment========");
            paymentUseCase.makePayment();
    }

    private void showAdminMenu() {
        while (true) {
            System.out.println("Admin Menu:");
            System.out.println("1. Add Parking Lot");
            System.out.println("2. Display All Parking Lots");
            System.out.println("3. Display All Vehicles");
            System.out.println("4. Display All Users");
            System.out.println("5. Display All Parking Tickets");
            System.out.println("6. Display All Payments");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Add Parking Lot logic
                    break;
                case 2:
                    // Display All Parking Lots logic
                    break;
                case 3:
                    // Display All Vehicles logic
                    break;
                case 4:
                    displayAllUsers();
                    break;
                case 5:
                    // Display All Parking Tickets logic
                    break;
                case 6:
                    // Display All Payments logic
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public void displayAllUsers() {
        try {
            List<User> users = userService.getAllUsers();
            for (User user : users) {
                System.out.println(user.getName() + " - " + user.getEmail() + " - " + user.getRole());
            }
        } catch (ParkingException e) {
            System.out.println(e.getMessage());
        }
    }
}