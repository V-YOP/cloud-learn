package me.yuuki.provider.payment.dao;
import me.yuuki.entity.Payment;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 完全没有任何必要在现阶段引入数据库——我又没在学分布式事务啥的，扯这个干什么？
 */
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
