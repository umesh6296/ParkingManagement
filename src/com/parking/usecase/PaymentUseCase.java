package com.parking.usecase;

import com.parking.entity.Payment;
import com.parking.service.PaymentService;
import com.parking.service.PaymentServiceImpl;
import com.parking.exception.ParkingException;
import java.util.List;
import java.util.Scanner;

public class PaymentUseCase {
    private PaymentService paymentService = new PaymentServiceImpl();
    private Scanner scanner = new Scanner(System.in);

    public void makePayment() {
        System.out.print("Enter ticket ID: ");
        int ticketId = scanner.nextInt();
        System.out.print("Enter payment amount: ");
        double paymentAmount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter payment method (Cash/Card/UPI): ");
        String paymentMethod = scanner.nextLine();

        Payment payment = new Payment(ticketId, paymentAmount, paymentMethod);
        try {
            paymentService.addPayment(payment);
            System.out.println("Payment successful!");
        } catch (ParkingException e) {
            System.out.println(e.getMessage());
        }
    }

    public void displayAllPayments() {
        try {
            List<Payment> payments = paymentService.getAllPayments();
            for (Payment payment : payments) {
                System.out.println("Payment ID: " + payment.getPaymentId() + " - Amount: " + payment.getPaymentAmount());
            }
        } catch (ParkingException e) {
            System.out.println(e.getMessage());
        }
    }
}