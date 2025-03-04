package com.parking.dao;

import com.parking.entity.ParkingTicket;
import com.parking.utility.DBUtil;
import com.parking.exception.ParkingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParkingTicketDAOImpl implements ParkingTicketDAO {
    @Override
    public void addParkingTicket(ParkingTicket parkingTicket) throws ParkingException {
        String sql = "INSERT INTO parking_tickets (vehicle_id, lot_id, entry_time, exit_time, parking_fee) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, parkingTicket.getVehicleId());
            stmt.setInt(2, parkingTicket.getLotId());
            stmt.setTimestamp(3, parkingTicket.getEntryTime());
            stmt.setTimestamp(4, parkingTicket.getExitTime());
            stmt.setDouble(5, parkingTicket.getParkingFee());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new ParkingException("Error adding parking ticket: " + e.getMessage());
        }
    }

    @Override
    public ParkingTicket getParkingTicketById(int vehicleId) throws ParkingException {
        String sql = "SELECT * FROM parking_tickets WHERE vehicle_id = ?";
        ParkingTicket ticket = null;

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, vehicleId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                ticket = new ParkingTicket();
                ticket.setTicketId(rs.getInt("ticket_id"));
                ticket.setVehicleId(rs.getInt("vehicle_id"));
                ticket.setLotId(rs.getInt("lot_id"));

            }
        } catch (SQLException e) {
            throw new ParkingException("Error fetching parking ticket: " + e.getMessage());
        }

        return ticket;
    }


    @Override
    public List<ParkingTicket> getAllParkingTickets() throws ParkingException {
        List<ParkingTicket> parkingTickets = new ArrayList<>();
        String sql = "SELECT * FROM parking_tickets";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ParkingTicket ticket = new ParkingTicket();
                ticket.setTicketId(rs.getInt("ticket_id"));
                ticket.setVehicleId(rs.getInt("vehicle_id"));
                ticket.setLotId(rs.getInt("lot_id"));
                ticket.setEntryTime(rs.getTimestamp("entry_time"));
                ticket.setExitTime(rs.getTimestamp("exit_time"));
                ticket.setParkingFee(rs.getDouble("parking_fee"));
                parkingTickets.add(ticket);
            }
        } catch (SQLException e) {
            throw new ParkingException("Error fetching all parking tickets: " + e.getMessage());
        }
        return parkingTickets;
    }

    @Override
    public void updateParkingTicket(ParkingTicket parkingTicket) throws ParkingException {
        String sql = "UPDATE parking_tickets SET vehicle_id = ?, lot_id = ?, entry_time = ?, exit_time = ?, parking_fee = ? WHERE ticket_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, parkingTicket.getVehicleId());
            stmt.setInt(2, parkingTicket.getLotId());
            stmt.setTimestamp(3, parkingTicket.getEntryTime());
            stmt.setTimestamp(4, parkingTicket.getExitTime());
            stmt.setDouble(5, parkingTicket.getParkingFee());
            stmt.setInt(6, parkingTicket.getTicketId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new ParkingException("Error updating parking ticket: " + e.getMessage());
        }
    }

    @Override
    public void deleteParkingTicket(int ticketId) throws ParkingException {
        String sql = "DELETE FROM parking_tickets WHERE ticket_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, ticketId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new ParkingException("Error deleting parking ticket: " + e.getMessage());
        }
    }
}