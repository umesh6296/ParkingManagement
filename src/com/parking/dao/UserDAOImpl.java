package com.parking.dao;

import com.parking.entity.User;
import com.parking.utility.DBUtil;
import com.parking.exception.ParkingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public void addUser(User user) throws ParkingException {
        String sql = "INSERT INTO users (name, contact_number, email, vehicle_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getContactNumber());
            stmt.setString(3, user.getEmail());
            stmt.setInt(4, user.getVehicleId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new ParkingException("Error adding user: " + e.getMessage());
        }
    }

    @Override
    public User getUserById(int userId) throws ParkingException {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        User user = null;
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setName(rs.getString("name"));
                user.setContactNumber(rs.getString("contact_number"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setVehicleId(rs.getInt("vehicle_id"));
            }
        } catch (SQLException e) {
            throw new ParkingException("Error fetching user: " + e.getMessage());
        }
        return user;
    }
    @Override
    public User getUserByEmail(String email,String password)throws ParkingException{
        String sql = "SELECT * FROM users WHERE email=? and password = ?";
        User user = null;
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("User found..........");
            }
            else {
                System.out.println("Sorry user can't found......");
            }
        } catch (SQLException e) {
            throw new ParkingException("Error fetching user: " + e.getMessage());
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() throws ParkingException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setName(rs.getString("name"));
                user.setContactNumber(rs.getString("contact_number"));
                user.setEmail(rs.getString("email"));
                user.setVehicleId(rs.getInt("vehicle_id"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new ParkingException("Error fetching all users: " + e.getMessage());
        }
        return users;
    }

    @Override
    public void updateUser(User user) throws ParkingException {
        String sql = "UPDATE users SET name = ?, contact_number = ?, email = ?, vehicle_id = ? WHERE user_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getContactNumber());
            stmt.setString(3, user.getEmail());
            stmt.setInt(4, user.getVehicleId());
            stmt.setInt(5, user.getUserId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new ParkingException("Error updating user: " + e.getMessage());
        }
    }

    @Override
    public void deleteUser(int userId) throws ParkingException {
        String sql = "DELETE FROM users WHERE user_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new ParkingException("Error deleting user: " + e.getMessage());
        }
    }
}