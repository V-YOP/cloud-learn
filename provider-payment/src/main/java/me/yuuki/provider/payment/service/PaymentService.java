package me.yuuki.provider.payment.service;


import me.yuuki.entity.Payment;

import java.util.Optional;

public interface PaymentService {
    void create(Payment payment);
    Optional<Payment> getPayment(String id);
}
