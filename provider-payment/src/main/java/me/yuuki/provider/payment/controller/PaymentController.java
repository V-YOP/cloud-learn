package me.yuuki.provider.payment.controller;

import me.yuuki.entity.CommonResult;
import me.yuuki.entity.Payment;

import me.yuuki.provider.payment.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    private final PaymentService paymentService;

    // 方便在返回结果中看到究竟是哪个实例被调用了
    @Value("${server.port}")
    private String serverPort;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/create")
    public CommonResult<Void> create(@RequestBody Payment payment) {
        paymentService.create(payment);
        logger.info("create payment: {}, server port: {}", payment, serverPort);
        return CommonResult.empty(200, "成功, server port: " + serverPort);
    }

    @GetMapping(value = {"/{id}"})
    public CommonResult<Payment> getPayment(@PathVariable String id) {
        logger.info(paymentService.getPayment(id).toString());
        return paymentService.getPayment(id) // Optional<Payment>
                .map(CommonResult::success) //Optional<CommonResult<Payment>>
                .orElse(CommonResult.empty(404,"查无此payment, server port: " + serverPort)); // CommonResult<Payment>
    }
}

