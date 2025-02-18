package com.parking.entity;


public class ParkingLot {
    private int lotId;
    private String lotName;
    private int capacity;

    public ParkingLot(String lotName, int capacity) {
        this.lotName = lotName;
        this.capacity = capacity;
    }
    public ParkingLot(){

    }

    public int getLotId() {
        return lotId;
    }

    public void setLotId(int lotId) {
        this.lotId = lotId;
    }

    public String getLotName() {
        return lotName;
    }

    public void setLotName(String lotName) {
        this.lotName = lotName;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
