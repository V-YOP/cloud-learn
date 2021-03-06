package me.yuuki.comsumer.order.controller;

import me.yuuki.entity.CommonResult;
import me.yuuki.entity.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE", path = "/payment")
public interface PaymentFeignService {
    @PostMapping("/create")
    CommonResult<Void> create(@RequestBody Payment payment);

    @GetMapping(value = {"/{id}"})
    CommonResult<Payment> getPayment(@PathVariable String id);
}
