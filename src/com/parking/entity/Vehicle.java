package com.parking.entity;
import java.sql.Timestamp;
public class Vehicle {
    private int vehicleId;
    private String plateNumber;
    private String ownerName;
    private String vehicleType;
    private Timestamp entryTime;
    private Timestamp exitTime;
    private int lotId;

    public Vehicle(int vehicleId, String plateNumber, String ownerName, String vehicleType, Timestamp entryTime, Timestamp exitTime, int lotId) {
        this.vehicleId = vehicleId;
        this.plateNumber = plateNumber;
        this.ownerName = ownerName;
        this.vehicleType = vehicleType;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.lotId = lotId;
    }
    public Vehicle(){}

    public Vehicle(String plateNumber, String ownerName, String vehicleType, int lotId) {
        this.plateNumber = plateNumber;
        this.ownerName = ownerName;
        this.vehicleType = vehicleType;
        this.lotId = lotId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Timestamp getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Timestamp entryTime) {
        this.entryTime = entryTime;
    }

    public Timestamp getExitTime() {
        return exitTime;
    }

    public void setExitTime(Timestamp exitTime) {
        this.exitTime = exitTime;
    }

    public int getLotId() {
        return lotId;
    }

    public void setLotId(int lotId) {
        this.lotId = lotId;
    }
}
