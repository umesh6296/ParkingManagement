package com.parking.service;

import com.parking.dao.ParkingTicketDAO;
import com.parking.dao.ParkingTicketDAOImpl;
import com.parking.entity.ParkingTicket;
import com.parking.exception.ParkingException;

import java.util.List;

public class ParkingTicketServiceImpl implements ParkingTicketService{
    private ParkingTicketDAO parkingTicketDAO=new ParkingTicketDAOImpl();
    @Override
    public void addParkingTicket(ParkingTicket parkingTicket) throws ParkingException {
        parkingTicketDAO.addParkingTicket(parkingTicket);
    }

    @Override
    public ParkingTicket getParkingTicketById(int parkingTicketById) throws ParkingException {
        return parkingTicketDAO.getParkingTicketById(parkingTicketById);
    }

    @Override
    public List<ParkingTicket> getAllParkingTickets() throws ParkingException {
        return parkingTicketDAO.getAllParkingTickets();
    }

    @Override
    public void updateParkingTicket(ParkingTicket parkingTicket) throws ParkingException {
        parkingTicketDAO.updateParkingTicket(parkingTicket);
    }

    @Override
    public void deleteParkingTicket(int ticketId) throws ParkingException {
        parkingTicketDAO.deleteParkingTicket(ticketId);
    }
}
