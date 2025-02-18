package com.parking.entity;
import java.sql.Timestamp;

public class Payment {
    private int paymentId;
    private int ticketId;
    private Timestamp paymentDate;
    private double paymentAmount;
    private String paymentMethod;

    public Payment(int paymentId, int ticketId, Timestamp paymentDate, double paymentAmount, String paymentMethod) {
        this.paymentId = paymentId;
        this.ticketId = ticketId;
        this.paymentDate = paymentDate;
        this.paymentAmount = paymentAmount;
        this.paymentMethod = paymentMethod;
    }
    public Payment(){}

    public Payment(int ticketId, double paymentAmount, String paymentMethod) {
        this.ticketId = ticketId;
        this.paymentAmount = paymentAmount;
        this.paymentMethod = paymentMethod;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public Timestamp getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Timestamp paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
