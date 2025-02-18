package com.parking.service;

import com.parking.entity.User;
import com.parking.exception.ParkingException;

import java.util.List;

public interface UserService {
    void addUser(User user)throws ParkingException;
    User getUserById(int userId)throws ParkingException;
    List<User> getAllUsers()throws ParkingException;
    void updateUser(User user)throws ParkingException;
    void deleteUser(int userId)throws ParkingException;
    User getUserByEmail(String email,String password)throws ParkingException;
}
