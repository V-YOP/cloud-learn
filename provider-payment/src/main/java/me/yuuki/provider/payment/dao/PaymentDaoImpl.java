package me.yuuki.provider.payment.dao;

import me.yuuki.provider.payment.entity.Payment;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class PaymentDaoImpl implements PaymentDao {
    private final Map<String, Payment> database = new ConcurrentHashMap<>();

    @Override
    public void create(Payment payment) {
        Assert.notNull(payment.getId(), "Payment id can't be Null!");
        database.put(payment.getId(), payment);
    }

    @Override
    public Optional<Payment> getPayment(String id) {
        return Optional.ofNullable(database.get(id));
    }
}
