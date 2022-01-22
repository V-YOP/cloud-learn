package me.yuuki.provider.payment.dao;

import me.yuuki.provider.payment.entity.Payment;

import java.util.Optional;

public interface PaymentDao {
    void create(Payment payment);
    Optional<Payment> getPayment(String id);
}
