package com.parking.dao;

import com.parking.entity.ParkingLot;
import com.parking.exception.ParkingException;
import com.parking.utility.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParkingLotDAOImpl implements ParkingLotDAO {
    @Override
    public void addParkingLot(ParkingLot parkingLot) throws ParkingException {
        String sql = "INSERT INTO parking_lots (lot_name, capacity) VALUES (?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, parkingLot.getLotName());
            stmt.setInt(2, parkingLot.getCapacity());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ParkingLot getParkingLotById(int lotId)throws ParkingException  {
        String sql = "SELECT * FROM parking_lots WHERE lot_id = ?";
        ParkingLot parkingLot = null;
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, lotId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                parkingLot = new ParkingLot();
                parkingLot.setLotId(rs.getInt("lot_id"));
                parkingLot.setLotName(rs.getString("lot_name"));
                parkingLot.setCapacity(rs.getInt("capacity"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return parkingLot;
    }

    @Override
    public List<ParkingLot> getAllParkingLots()throws ParkingException  {
        List<ParkingLot> parkingLots = new ArrayList<>();
        String sql = "SELECT * FROM parking_lots";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ParkingLot lot = new ParkingLot();
                lot.setLotId(rs.getInt("lot_id"));
                lot.setLotName(rs.getString("lot_name"));
                lot.setCapacity(rs.getInt("capacity"));
                parkingLots.add(lot);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return parkingLots;
    }

    @Override
    public void updateParkingLot(ParkingLot parkingLot) throws ParkingException {
        String sql = "UPDATE parking_lots SET lot_name = ?, capacity = ? WHERE lot_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, parkingLot.getLotName());
            stmt.setInt(2, parkingLot.getCapacity());
            stmt.setInt(3, parkingLot.getLotId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteParkingLot(int lotId)throws ParkingException  {
        String sql = "DELETE FROM parking_lots WHERE lot_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, lotId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}