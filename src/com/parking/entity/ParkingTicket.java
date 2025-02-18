package com.parking.entity;

import java.sql.Timestamp;

public class ParkingTicket {
    private int ticketId;
    private int vehicleId;
    private int lotId;
    private Timestamp entryTime;
    private Timestamp exitTime;
    private double parkingFee;

    public ParkingTicket(int ticketId, int vehicleId, int lotId, Timestamp entryTime, Timestamp exitTime, double parkingFee) {
        this.ticketId = ticketId;
        this.vehicleId = vehicleId;
        this.lotId = lotId;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.parkingFee = parkingFee;
    }
    public ParkingTicket(){}

    public ParkingTicket(int vehicleId, int lotId) {
        this.vehicleId = vehicleId;
        this.lotId = lotId;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getLotId() {
        return lotId;
    }

    public void setLotId(int lotId) {
        this.lotId = lotId;
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

    public double getParkingFee() {
        return parkingFee;
    }

    public void setParkingFee(double parkingFee) {
        this.parkingFee = parkingFee;
    }
}