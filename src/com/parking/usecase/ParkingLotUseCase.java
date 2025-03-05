package com.parking.usecase;

import com.parking.entity.ParkingLot;
import com.parking.service.ParkingLotService;
import com.parking.service.ParkingLotServiceImpl;
import com.parking.exception.ParkingException;
import java.util.List;
import java.util.Scanner;

public class ParkingLotUseCase {
    private ParkingLotService parkingLotService = new ParkingLotServiceImpl();
    private Scanner scanner = new Scanner(System.in);

    public void createParkingLot() {
        System.out.print("Enter parking lot name: ");
        String lotName = scanner.nextLine();
        System.out.print("Enter parking lot capacity: ");
        int capacity = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        ParkingLot parkingLot = new ParkingLot(lotName, capacity);
        try {
            parkingLotService.addParkingLot(parkingLot);
            System.out.println("Parking lot added successfully!");
        } catch (ParkingException e) {
            System.out.println(e.getMessage());
        }
    }

    public void displayAllParkingLots() {
        try {
            List<ParkingLot> parkingLots = parkingLotService.getAllParkingLots();
            for (ParkingLot lot : parkingLots) {
                System.out.println(lot.getLotName() + " - Capacity: " + lot.getCapacity());
            }
        } catch (ParkingException e) {
            System.out.println(e.getMessage());
        }
    }
    public void updateParkingLot() {
        System.out.print("Enter Parking Lot Id to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            ParkingLot existingParkingLot = parkingLotService.getParkingLotById(id);
            if (existingParkingLot == null) {
                System.out.println("Parking Lot ID not found!");
                return;
            }

            System.out.print("Enter new Parking Lot Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter new Parking Lot Capacity: ");
            int capacity = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            existingParkingLot.setLotName(name);
            existingParkingLot.setCapacity(capacity);

            parkingLotService.updateParkingLot(existingParkingLot);
            System.out.println("Parking lot updated successfully!");
        } catch (ParkingException e) {
            System.out.println("Error updating parking lot: " + e.getMessage());
        }
    }

    public void deleteParkingLot() {
        System.out.print("Enter Parking Lot Id to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            ParkingLot existingParkingLot = parkingLotService.getParkingLotById(id);
            if (existingParkingLot == null) {
                System.out.println("Parking Lot ID not found!");
                return;
            }

            parkingLotService.deleteParkingLot(id);
            System.out.println("Parking lot deleted successfully!");
        } catch (ParkingException e) {
            System.out.println("Error deleting parking lot: " + e.getMessage());
        }
    }

    public void findParkingLotById() {
        System.out.print("Enter Parking Lot ID to find: ");
        int parkingLotId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            ParkingLot parkingLot = parkingLotService.getParkingLotById(parkingLotId);
            if (parkingLot != null) {
                System.out.println("Parking Lot found:");
                System.out.println("ID: " + parkingLot.getLotId());
                System.out.println("Lot Name: " + parkingLot.getLotName());
                System.out.println("Capacity: " + parkingLot.getCapacity());
            } else {
                System.out.println("Parking Lot not found!");
            }
        } catch (ParkingException e) {
            System.out.println("Error finding parking lot: " + e.getMessage());
        }
    }





}