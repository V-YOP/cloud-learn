package me.yuuki.provider.payment.service;

import me.yuuki.provider.payment.dao.PaymentDao;
import me.yuuki.provider.payment.entity.Payment;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentDao paymentDao;

    public PaymentServiceImpl(PaymentDao paymentDao) {
        this.paymentDao = paymentDao;
    }

    @Override
    public void create(Payment payment) {
        paymentDao.create(payment);
    }

    @Override
    public Optional<Payment> getPayment(String id) {
        return paymentDao.getPayment(id);
    }
}
