package me.yuuki.provider.payment.controller;

import me.yuuki.provider.payment.entity.Payment;
import me.yuuki.provider.payment.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    private final PaymentService paymentService;
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/create")
    public CommonResult<Void> create(@RequestBody Payment payment) {
        paymentService.create(payment);
        logger.info("create payment: {}", payment);
        return CommonResult.empty(200, "成功");
    }

    @GetMapping(value = {"/{id}"})
    public CommonResult<Payment> getPayment(@PathVariable String id) {
        return paymentService.getPayment(id) // Optional<Payment>
                .map(CommonResult::success) //Optional<CommonResult<Payment>>
                .orElse(CommonResult.empty(404,"查无此payment")); // CommonResult<Payment>
    }
}

