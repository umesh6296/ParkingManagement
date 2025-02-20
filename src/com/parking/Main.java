package com.parking;

import com.parking.entity.User;
import com.parking.service.UserService;
import com.parking.service.UserServiceImpl;

import com.parking.exception.ParkingException;
import com.parking.usecase.UserUseCase;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final UserService userService = new UserServiceImpl();

    public static void main(String[] args) {
        System.out.println("===== Welcome to Parking Management System =====");

        while (true) {
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    UserUseCase.registerUser();
                    break;
                case 3:
                    System.out.println("Exiting system...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void login() {
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        try {
            User user = userService.getUserByEmail(email, password);
            if (user != null) {
                System.out.println("Login successful! Welcome, " + user.getName());

                if (user.getRole().equalsIgnoreCase("A")) {
                    UserUseCase.adminMenu(user);
                } else if (user.getRole().equalsIgnoreCase("U")) {
                    UserUseCase.userMenu(user);
                }
            } else {
                System.out.println("User not found. Would you like to create a new account? (Y/N)");
                String choice = scanner.next();
                if (choice.equalsIgnoreCase("Y")) {
                    UserUseCase.registerUser();
                } else {
                    System.out.println("Returning to main menu...");
                }
            }
        } catch (ParkingException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
