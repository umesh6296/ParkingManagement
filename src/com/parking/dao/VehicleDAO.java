package com.parking.dao;

import com.parking.entity.Vehicle;
import com.parking.exception.ParkingException;

import java.util.List;

public interface VehicleDAO {
    void addVehicle(Vehicle vehicle)throws ParkingException;
    Vehicle getVehicleById(int vehicleId)throws ParkingException;
    List<Vehicle> getAllVehicles()throws ParkingException;
    void updateVehicle(Vehicle vehicle)throws ParkingException;
    void deleteVehicle(int vehicleId)throws ParkingException;
}