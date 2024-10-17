package com.examly.springapp.service;

import com.examly.springapp.model.Payment;

import java.util.List;

public interface PaymentService {
    Payment createPayment(Payment payment);
    Payment getPaymentById(Long id);
    List<Payment> getAllPayments();
    Payment updatePayment(Long id, Payment payment);
    void deletePayment(Long id);
}
