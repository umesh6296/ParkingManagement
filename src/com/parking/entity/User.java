package com.parking.entity;

public class User {
    private int userId;
    private String name;
    private String contactNumber;
    private String email;
    private String password;
    private String role;

    private int vehicleId;

    public User(int userId, String name, String contactNumber, String email, int vehicleId,String password,String role) {
        this.userId = userId;
        this.name = name;
        this.contactNumber = contactNumber;
        this.email = email;
        this.vehicleId = vehicleId;
        this.password=password;
        this.role=role;

    }
    public User(){

    }

    public User(String name, String contactNumber, String email, int vehicleId) {
        this.name = name;
        this.contactNumber = contactNumber;
        this.email = email;
        this.vehicleId = vehicleId;
    }

    public User(String name, String contactNumber, String email, String password, String role, int vehicleId) {
        this.name = name;
        this.contactNumber = contactNumber;
        this.password= password;
        this.role=role;
        this.email = email;
        this.vehicleId = vehicleId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
