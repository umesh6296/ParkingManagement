package com.parking.service;

import com.parking.entity.ParkingLot;
import com.parking.entity.ParkingTicket;
import com.parking.exception.ParkingException;

import java.util.List;

public interface ParkingTicketService {
    void addParkingTicket(ParkingTicket parkingTicket)throws ParkingException;
    ParkingTicket getParkingTicketById(int parkingTicketById)throws ParkingException;
    List<ParkingTicket> getAllParkingTickets()throws ParkingException;
    void updateParkingTicket(ParkingTicket parkingTicket)throws ParkingException;
    void deleteParkingTicket(int ticketId)throws ParkingException;
}
