package com.parking.usecase;

import com.parking.entity.Vehicle;
import com.parking.service.VehicleService;
import com.parking.service.VehicleServiceImpl;
import com.parking.exception.ParkingException;
import java.util.List;
import java.util.Scanner;

public class VehicleUseCase {
    private VehicleService vehicleService = new VehicleServiceImpl();
    private Scanner scanner = new Scanner(System.in);

    public void parkVehicle() {
        System.out.print("Enter vehicle plate number: ");
        String plateNumber = scanner.nextLine();
        System.out.print("Enter owner name: ");
        String ownerName = scanner.nextLine();
        System.out.print("Enter vehicle type: ");
        String vehicleType = scanner.nextLine();
        System.out.print("Enter parking lot ID: ");
        int lotId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Vehicle vehicle = new Vehicle(plateNumber, ownerName, vehicleType, lotId);
        try {
            vehicleService.addVehicle(vehicle);
            System.out.println("Vehicle parked successfully!");
        } catch (ParkingException e) {
            System.out.println(e.getMessage());
        }
    }

    public void displayAllVehicles() {
        try {
            List<Vehicle> vehicles = vehicleService.getAllVehicles();
            for (Vehicle vehicle : vehicles) {
                System.out.println(vehicle.getPlateNumber() + " - " + vehicle.getOwnerName());
            }
        } catch (ParkingException e) {
            System.out.println(e.getMessage());
        }
    }
}