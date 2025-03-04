package com.parking.service;

import com.parking.dao.VehicleDAO;
import com.parking.dao.VehicleDAOImpl;
import com.parking.entity.Vehicle;
import com.parking.exception.ParkingException;
import com.parking.utility.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class VehicleServiceImpl implements VehicleService{
    private VehicleDAO vehicleDAO=new VehicleDAOImpl();
    @Override
    public void addVehicle(Vehicle vehicle) throws ParkingException {
        vehicleDAO.addVehicle(vehicle);
    }

    @Override
    public Vehicle getVehicleById(int vehicleId) throws ParkingException {
        return vehicleDAO.getVehicleById(vehicleId);
    }

    @Override
    public List<Vehicle> getAllVehicles() throws ParkingException {
        return vehicleDAO.getAllVehicles();
    }

    @Override
    public void updateVehicle(Vehicle vehicle) throws ParkingException {
        vehicleDAO.updateVehicle(vehicle);
    }

    @Override
    public void deleteVehicle(int vehicleId) throws ParkingException {
        vehicleDAO.deleteVehicle(vehicleId);
    }
    @Override
    public Vehicle getVehicleByPlateNumber(String plateNumber) throws ParkingException {
        return vehicleDAO.getVehicleByPlateNumber(plateNumber);
    }




}
