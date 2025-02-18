package com.parking.dao;

import com.parking.entity.ParkingLot;
import com.parking.exception.ParkingException;

import java.util.List;

public interface ParkingLotDAO {
    void addParkingLot(ParkingLot parkingLot)throws ParkingException;
    ParkingLot getParkingLotById(int lotId)throws ParkingException;
    List<ParkingLot> getAllParkingLots()throws ParkingException;
    void updateParkingLot(ParkingLot parkingLot)throws ParkingException;
    void deleteParkingLot(int lotId)throws ParkingException;
}