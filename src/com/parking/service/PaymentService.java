package com.parking.service;

import com.parking.entity.Payment;
import com.parking.exception.ParkingException;

import java.util.List;

public interface PaymentService {
    void addPayment(Payment payment)throws ParkingException;
    Payment getPaymentById(int paymentId)throws ParkingException;
    List<Payment> getAllPayments()throws ParkingException;
    void updatePayment(Payment payment)throws ParkingException;
    void deletePayment(int paymentId)throws ParkingException;
}
