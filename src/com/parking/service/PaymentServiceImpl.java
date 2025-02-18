package com.parking.service;

import com.parking.dao.ParkingTicketDAO;
import com.parking.dao.ParkingTicketDAOImpl;
import com.parking.dao.PaymentDAO;
import com.parking.dao.PaymentDAOImpl;
import com.parking.entity.Payment;
import com.parking.exception.ParkingException;

import java.util.List;

public class PaymentServiceImpl implements PaymentService{
    private PaymentDAO paymentDAO=new PaymentDAOImpl();
    @Override
    public void addPayment(Payment payment) throws ParkingException {
        paymentDAO.addPayment(payment);
    }

    @Override
    public Payment getPaymentById(int paymentId) throws ParkingException {
        return paymentDAO.getPaymentById(paymentId);
    }

    @Override
    public List<Payment> getAllPayments() throws ParkingException {
        return paymentDAO.getAllPayments();
    }

    @Override
    public void updatePayment(Payment payment) throws ParkingException {
        paymentDAO.updatePayment(payment);
    }

    @Override
    public void deletePayment(int paymentId) throws ParkingException {
        paymentDAO.deletePayment(paymentId);
    }
}
