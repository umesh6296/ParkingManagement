package com.parking.dao;

import com.parking.entity.Vehicle;
import com.parking.utility.DBUtil;
import com.parking.exception.ParkingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAOImpl implements VehicleDAO {
    @Override
    public void addVehicle(Vehicle vehicle) throws ParkingException {
        String sql = "INSERT INTO vehicles (plate_number, owner_name, vehicle_type, lot_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, vehicle.getPlateNumber());
            stmt.setString(2, vehicle.getOwnerName());
            stmt.setString(3, vehicle.getVehicleType());
            stmt.setInt(4, vehicle.getLotId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new ParkingException("Error adding vehicle: " + e.getMessage());
        }
    }

    @Override
    public Vehicle getVehicleById(int vehicleId) throws ParkingException {
        String sql = "SELECT * FROM vehicles WHERE vehicle_id = ?";
        Vehicle vehicle = null;
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, vehicleId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                vehicle = new Vehicle();
                vehicle.setVehicleId(rs.getInt("vehicle_id"));
                vehicle.setPlateNumber(rs.getString("plate_number"));
                vehicle.setOwnerName(rs.getString("owner_name"));
                vehicle.setVehicleType(rs.getString("vehicle_type"));
                vehicle.setLotId(rs.getInt("lot_id"));
            }
        } catch (SQLException e) {
            throw new ParkingException("Error fetching vehicle: " + e.getMessage());
        }
        return vehicle;
    }

    @Override
    public List<Vehicle> getAllVehicles() throws ParkingException {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM vehicles";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Vehicle vehicle = new Vehicle();
                vehicle.setVehicleId(rs.getInt("vehicle_id"));
                vehicle.setPlateNumber(rs.getString("plate_number"));
                vehicle.setOwnerName(rs.getString("owner_name"));
                vehicle.setVehicleType(rs.getString("vehicle_type"));
                vehicle.setLotId(rs.getInt("lot_id"));
                vehicles.add(vehicle);
            }
        } catch (SQLException e) {
            throw new ParkingException("Error fetching all vehicles: " + e.getMessage());
        }
        return vehicles;
    }

    @Override
    public void updateVehicle(Vehicle vehicle) throws ParkingException {
        String sql = "UPDATE vehicles SET plate_number = ?, owner_name = ?, vehicle_type = ?, lot_id = ? WHERE vehicle_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, vehicle.getPlateNumber());
            stmt.setString(2, vehicle.getOwnerName());
            stmt.setString(3, vehicle.getVehicleType());
            stmt.setInt(4, vehicle.getLotId());
            stmt.setInt(5, vehicle.getVehicleId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new ParkingException("Error updating vehicle: " + e.getMessage());
        }
    }



    @Override
    public Vehicle getVehicleByPlateNumber(String plateNumber) throws ParkingException {
        String sql = "SELECT * FROM vehicles WHERE plate_number = ?";
        Vehicle vehicle = null;
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, plateNumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                vehicle = new Vehicle();
                vehicle.setVehicleId(rs.getInt("vehicle_id"));
                vehicle.setPlateNumber(rs.getString("plate_number"));
                vehicle.setOwnerName(rs.getString("owner_name"));
                vehicle.setVehicleType(rs.getString("vehicle_type"));
                vehicle.setLotId(rs.getInt("lot_id"));
            }
        } catch (SQLException e) {
            throw new ParkingException("Error fetching vehicle by plate number: " + e.getMessage());
        }
        return vehicle;
    }


    @Override
    public void deleteVehicle(int vehicleId) throws ParkingException {
        String sql = "DELETE FROM vehicles WHERE vehicle_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, vehicleId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new ParkingException("Error deleting vehicle: " + e.getMessage());
        }
    }
}