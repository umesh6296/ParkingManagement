package com.parking.service;

import com.parking.dao.ParkingLotDAO;
import com.parking.dao.ParkingLotDAOImpl;
import com.parking.entity.ParkingLot;
import com.parking.exception.ParkingException;

import java.util.List;

public class ParkingLotServiceImpl implements ParkingLotService {
    private ParkingLotDAO parkingLotDAO = new ParkingLotDAOImpl();

    @Override
    public void addParkingLot(ParkingLot parkingLot) throws ParkingException {
        parkingLotDAO.addParkingLot(parkingLot);
    }

    @Override
    public ParkingLot getParkingLotById(int lotId) throws ParkingException{
        return parkingLotDAO.getParkingLotById(lotId);
    }

    @Override
    public List<ParkingLot> getAllParkingLots()throws ParkingException {
        return parkingLotDAO.getAllParkingLots();
    }

    @Override
    public void updateParkingLot(ParkingLot parkingLot)throws ParkingException {
        parkingLotDAO.updateParkingLot(parkingLot);
    }

    @Override
    public void deleteParkingLot(int lotId)throws ParkingException {
        parkingLotDAO.deleteParkingLot(lotId);
    }
}