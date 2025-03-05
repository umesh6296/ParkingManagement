package com.parking.usecase;

import com.parking.dao.ParkingTicketDAO;
import com.parking.dao.ParkingTicketDAOImpl;
import com.parking.entity.ParkingLot;
import com.parking.entity.ParkingTicket;
import com.parking.entity.Vehicle;
import com.parking.service.ParkingLotService;
import com.parking.service.ParkingLotServiceImpl;
import com.parking.service.VehicleService;
import com.parking.service.VehicleServiceImpl;
import com.parking.exception.ParkingException;
import java.util.List;
import java.util.Scanner;

public class VehicleUseCase {
    private VehicleService vehicleService = new VehicleServiceImpl();
    private ParkingLotService parkingLotService=new ParkingLotServiceImpl();
    private ParkingTicket parkingTicket=new ParkingTicket();
    private ParkingLot parkingLot=new ParkingLot();
    private Scanner scanner = new Scanner(System.in);

    public void parkVehicle() {
        System.out.print("Enter vehicle plate number: ");
        String plateNumber = scanner.nextLine();
        System.out.print("Enter owner name: ");
        String ownerName = scanner.nextLine();
        System.out.print("Enter vehicle type: ");
        String vehicleType = scanner.nextLine();

        System.out.println("Choose Parking Lot ID from the available lots:");
        int lotId = -1;
        try {
            List<ParkingLot> parkingLots = parkingLotService.getAllParkingLots();
            for (ParkingLot p : parkingLots) {
                System.out.println("ID: " + p.getLotId() + " - Parking Lot Name: " + p.getLotName());
            }
            lotId = scanner.nextInt();
            scanner.nextLine(); // Consume newline
        } catch (ParkingException e) {
            System.out.println("Error fetching parking lots: " + e.getMessage());
            return;
        }

        Vehicle vehicle = new Vehicle(plateNumber, ownerName, vehicleType, lotId);
        try {
            vehicleService.addVehicle(vehicle); // Insert vehicle into DB
            Vehicle savedVehicle = vehicleService.getVehicleByPlateNumber(plateNumber); // Fetch vehicle ID
            if (savedVehicle != null) {
                System.out.println("Vehicle parked successfully!");
                System.out.println("Vehicle ID: " + savedVehicle.getVehicleId() + ", Parking Lot ID: " + lotId);
            } else {
                System.out.println("Error retrieving vehicle details.");
            }
        } catch (ParkingException e) {
            System.out.println("Error parking vehicle: " + e.getMessage());
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
    public void findVehicleById() {
        System.out.print("Enter Vehicle ID to find: ");
        int vehicleId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            Vehicle vehicle = vehicleService.getVehicleById(vehicleId);
            if (vehicle != null) {
                System.out.println("Vehicle found:");
                System.out.println("Vehicle ID: " + vehicle.getVehicleId());
                System.out.println("Plate Number: " + vehicle.getPlateNumber());
                System.out.println("Vehicle Type: "+vehicle.getVehicleType());
                System.out.println("Parking Lot Id: "+vehicle.getLotId());
                System.out.println("Owner: " + vehicle.getOwnerName());
            } else {
                System.out.println("Vehicle not found!");
            }
        } catch (ParkingException e) {
            System.out.println("Error finding vehicle: " + e.getMessage());
        }
    }

}