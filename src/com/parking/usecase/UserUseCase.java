package com.parking.usecase;

import com.parking.entity.User;
import com.parking.exception.ParkingException;
import com.parking.service.UserService;
import com.parking.service.UserServiceImpl;


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
            System.out.println("===== Admin Menu =====");
            System.out.println("1. Add Parking Lot");
            System.out.println("2. Display All Parking Lots, Vehicles, Users, Parking Tickets, Payments");
            System.out.println("3. Delete Parking Lots, Users");
            System.out.println("4. Update Parking Lots, Users");
            System.out.println("5. Show Parking Lots, Vehicles, Users, Parking Tickets, Payments");
            System.out.println("6. Logout");

            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    parkingLotUseCase.createParkingLot();
                    break;
                case 2:
                    boolean displayFlag = true;
                    while (displayFlag) {
                        System.out.println("Enter your choice for display details....");
                        System.out.println("1. Display All Parking Lots");
                        System.out.println("2. Display All Vehicles");
                        System.out.println("3. Display All Users");
                        System.out.println("4. Display All Parking Tickets");
                        System.out.println("5. Display All Payments");
                        System.out.println("6. Exit to Admin Menu");

                        System.out.print("Enter your choice: ");
                        int disChoice = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        switch (disChoice) {
                            case 1:
                                System.out.println("========All Parking Lots========");
                                parkingLotUseCase.displayAllParkingLots();
                                break;
                            case 2:
                                System.out.println("========All Vehicles========");
                                vehicleUseCase.displayAllVehicles();
                                break;
                            case 3:
                                System.out.println("=========All Users==========");
                                viewAllUsers();
                                break;
                            case 4:
                                System.out.println("========All Parking Tickets========");
                                parkingTicketUseCase.displayAllParkingTickets();
                                break;
                            case 5:
                                System.out.println("========All Parking Payments========");
                                paymentUseCase.displayAllPayments();
                                break;
                            case 6:
                                displayFlag = false;
                                break;
                            default:
                                System.out.println("Invalid choice. Try again.");
                                break;
                        }
                    }
                    break;
                case 3:
                    boolean delFlag = true;
                    while (delFlag) {
                        System.out.println("Enter your choice for Delete....");
                        System.out.println("1. Delete Parking Lot");
                        System.out.println("2. Delete User");
                        System.out.println("3. Exit to Admin Menu");

                        System.out.print("Enter your choice: ");
                        int disChoice = scanner.nextInt();
                        scanner.nextLine();

                        switch (disChoice) {
                            case 1:
                                System.out.println("========Update Parking Lot========");
                                parkingLotUseCase.deleteParkingLot();
                                break;
                            case 2:
                                System.out.println("========Update User========");
                                deleteUser();
                                break;
                            case 3:
                                delFlag = false;
                                break;
                            default:
                                System.out.println("Invalid choice. Try again.");
                                break;
                        }
                    }
                    break;

                case 4:
                    boolean updFlag = true;
                    while (updFlag) {
                        System.out.println("Enter your choice for Update....");
                        System.out.println("1. Update Parking Lots");
                        System.out.println("2. Update User");
                        System.out.println("3. Exit to Admin Menu");

                        System.out.print("Enter your choice: ");
                        int disChoice = scanner.nextInt();
                        scanner.nextLine();

                        switch (disChoice) {
                            case 1:
                                System.out.println("========Update Parking Lot========");
                                parkingLotUseCase.updateParkingLot();
                                break;
                            case 2:
                                System.out.println("========Update User========");
                                updateUser();
                                break;
                            case 3:
                                updFlag = false;
                                break;
                            default:
                                System.out.println("Invalid choice. Try again.");
                                break;
                        }
                    }
                    break;

                case 5:
                    boolean findFlag = true;
                    while (findFlag) {
                        System.out.println("Enter your choice for Find....");
                        System.out.println("1. Find Parking Lots");
                        System.out.println("2. Find Vehicles");
                        System.out.println("3. Find User");
                        System.out.println("4. Parking Tickets");
                        System.out.println("5. Payments");
                        System.out.println("6. Exit to Admin Menu");

                        System.out.print("Enter your choice: ");
                        int disChoice = scanner.nextInt();
                        scanner.nextLine();

                        switch (disChoice) {
                            case 1:
                                System.out.println("========Parking Lot========");
                                parkingLotUseCase.findParkingLotById();
                                break;
                            case 2:
                                System.out.println("========Vehicle========");
                                vehicleUseCase.findVehicleById();
                                break;
                            case 3:
                                System.out.println("=========User==========");
                                findUserById();
                                break;
                            case 4:
                                System.out.println("========Parking Ticket========");
                                parkingTicketUseCase.findParkingTicketById();
                                break;
                            case 5:
                                System.out.println("========Parking Payment========");
                                paymentUseCase.findPaymentById();
                                break;
                            case 6:
                                findFlag = false;
                                break;
                            default:
                                System.out.println("Invalid choice. Try again.");
                                break;
                        }
                    }
                    break;

                case 6:
                    System.out.println("Logging out...");
                    return; // Exit the admin menu loop
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // User menu
    public static void userMenu(User user) {
        System.out.println("Please park the vehicle....");
        vehicleUseCase.parkVehicle();
        System.out.println("Generate parking ticket");
        parkingTicketUseCase.generateParkingTicket();
        System.out.println("Please make payment....");
        paymentUseCase.makePayment();
        System.out.println("Enter 1 for exit");
        int choice = Integer.parseInt(scanner.nextLine());
        if(choice==1){
            return;
        }else {
            System.out.println("Please enter 1 for exit...");
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


    public static void updateUser() {
        System.out.print("Enter User ID to update: ");
        int userId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            User existingUser = userDAO.getUserById(userId);
            if (existingUser == null) {
                System.out.println("User ID not found!");
                return;
            }

            System.out.print("Enter new Contact Number: ");
            String contactNumber = scanner.nextLine();

            System.out.print("Enter new Email: ");
            String email = scanner.nextLine();

            existingUser.setContactNumber(contactNumber);
            existingUser.setEmail(email);

            userDAO.updateUser(existingUser);
            System.out.println("User details updated successfully!");
        } catch (ParkingException e) {
            System.out.println("Error updating user: " + e.getMessage());
        }
    }

    public static void deleteUser() {
        System.out.print("Enter User ID to delete: ");
        int userId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            User existingUser = userDAO.getUserById(userId);
            if (existingUser == null) {
                System.out.println("User ID not found!");
                return;
            }

            userDAO.deleteUser(userId);
            System.out.println("User deleted successfully!");
        } catch (ParkingException e) {
            System.out.println("Error deleting user: " + e.getMessage());
        }
    }



    public static void findUserById() {
        System.out.print("Enter User ID to find: ");
        int userId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            User user = userDAO.getUserById(userId);
            if (user != null) {
                System.out.println("User found:");
                System.out.println("User ID: " + user.getUserId());
                System.out.println("Name: " + user.getName());
                System.out.println("Contact: " + user.getContactNumber());
                System.out.println("Email: " + user.getEmail());
                System.out.println("Role: " + user.getRole());
            } else {
                System.out.println("User not found!");
            }
        } catch (ParkingException e) {
            System.out.println("Error finding user: " + e.getMessage());
        }
    }







}
