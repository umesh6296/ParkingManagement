package com.parking.service;

import com.parking.dao.ParkingTicketDAO;
import com.parking.dao.ParkingTicketDAOImpl;
import com.parking.dao.UserDAO;
import com.parking.dao.UserDAOImpl;
import com.parking.entity.User;
import com.parking.exception.ParkingException;

import java.util.List;

public class UserServiceImpl implements UserService{
    private UserDAO userDAO=new UserDAOImpl();
    @Override
    public void addUser(User user) throws ParkingException {
        userDAO.addUser(user);
    }

    @Override
    public User getUserById(int userId) throws ParkingException {
        return userDAO.getUserById(userId);
    }

    @Override
    public List<User> getAllUsers() throws ParkingException {
        return userDAO.getAllUsers();
    }
    @Override
    public User getUserByEmail(String email,String password)throws ParkingException{
        return userDAO.getUserByEmail(email,password);
    }

    @Override
    public void updateUser(User user) throws ParkingException {
        userDAO.updateUser(user);
    }

    @Override
    public void deleteUser(int userId) throws ParkingException {
        userDAO.deleteUser(userId);
    }
}
