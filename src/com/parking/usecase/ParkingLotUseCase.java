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
}