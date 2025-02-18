package com.parking.dao;

import com.parking.entity.Payment;
import com.parking.utility.DBUtil;
import com.parking.exception.ParkingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public void addPayment(Payment payment) throws ParkingException {
        String sql = "INSERT INTO payments (ticket_id, payment_date, payment_amount, payment_method) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, payment.getTicketId());
            stmt.setTimestamp(2, payment.getPaymentDate());
            stmt.setDouble(3, payment.getPaymentAmount());
            stmt.setString(4, payment.getPaymentMethod());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new ParkingException("Error adding payment: " + e.getMessage());
        }
    }

    @Override
    public Payment getPaymentById(int paymentId) throws ParkingException {
        String sql = "SELECT * FROM payments WHERE payment_id = ?";
        Payment payment = null;
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, paymentId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                payment = new Payment();
                payment.setPaymentId(rs.getInt("payment_id"));
                payment.setTicketId(rs.getInt("ticket_id"));
                payment.setPaymentDate(rs.getTimestamp("payment_date"));
                payment.setPaymentAmount(rs.getDouble("payment_amount"));
                payment.setPaymentMethod(rs.getString("payment_method"));
            }
        } catch (SQLException e) {
            throw new ParkingException("Error fetching payment: " + e.getMessage());
        }
        return payment;
    }

    @Override
    public List<Payment> getAllPayments() throws ParkingException {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT * FROM payments";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Payment payment = new Payment();
                payment.setPaymentId(rs.getInt("payment_id"));
                payment.setTicketId(rs.getInt("ticket_id"));
                payment.setPaymentDate(rs.getTimestamp("payment_date"));
                payment.setPaymentAmount(rs.getDouble("payment_amount"));
                payment.setPaymentMethod(rs.getString("payment_method"));
                payments.add(payment);
            }
        } catch (SQLException e) {
            throw new ParkingException("Error fetching all payments: " + e.getMessage());
        }
        return payments;
    }

    @Override
    public void updatePayment(Payment payment) throws ParkingException {
        String sql = "UPDATE payments SET ticket_id = ?, payment_date = ?, payment_amount = ?, payment_method = ? WHERE payment_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, payment.getTicketId());
            stmt.setTimestamp(2, payment.getPaymentDate());
            stmt.setDouble(3, payment.getPaymentAmount());
            stmt.setString(4, payment.getPaymentMethod());
            stmt.setInt(5, payment.getPaymentId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new ParkingException("Error updating payment: " + e.getMessage());
        }
    }

    @Override
    public void deletePayment(int paymentId) throws ParkingException {
        String sql = "DELETE FROM payments WHERE payment_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, paymentId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new ParkingException("Error deleting payment: " + e.getMessage());
        }
    }
}