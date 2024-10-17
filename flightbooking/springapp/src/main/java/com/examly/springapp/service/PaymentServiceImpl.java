package com.examly.springapp.service;

import com.examly.springapp.model.Payment;
import com.examly.springapp.repository.PaymentRepository; // Assume you have this repository
import com.examly.springapp.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found with id " + id));
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment updatePayment(Long id, Payment updatedPayment) {
        Payment existingPayment = getPaymentById(id);
        // Update fields as necessary
        existingPayment.setAmount(updatedPayment.getAmount());
        existingPayment.setPaymentMethod(updatedPayment.getPaymentMethod());
        existingPayment.setStatus(updatedPayment.getStatus());
        existingPayment.setPaymentDate(updatedPayment.getPaymentDate());
        existingPayment.setBooking(updatedPayment.getBooking()); // Link back to the Booking
        return paymentRepository.save(existingPayment);
    }

    @Override
    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }
}
