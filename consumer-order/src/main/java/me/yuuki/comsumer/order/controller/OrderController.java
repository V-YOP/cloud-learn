package me.yuuki.comsumer.order.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.yuuki.entity.CommonResult;
import me.yuuki.entity.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

// 微服务的消费者通常在url中使用consumer标明
@RestController
@RequestMapping("/consumer/payment")
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private final RestTemplate restTemplate;

    public OrderController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // 权宜之策，真正使用微服务的时候肯定使用服务注册和服务发现框架来获取具体信息
    // 这时目标的地址就类似"lb://payment_service"了
    private final String PAYMENT_URL = "http://localhost:8001";

    @PostMapping("/create")
    public CommonResult<Void> create(@RequestBody Payment payment) {
        // 丑陋至极！这请求失败了直接抛一个运行时异常，实在是有够麻烦……如果能返回一个Result<T>之类的东西就好了
        ResponseEntity<CommonResult<Void>> res = null;
        try {
            // 好像只有exchange允许使用TypeReference，颇为麻烦，希望工程实践中用到泛型Dto的机会不多
            res = restTemplate.exchange(PAYMENT_URL + "/payment/create", HttpMethod.POST, new HttpEntity<>(payment, null), new ParameterizedTypeReference<CommonResult<Void>>() {});
            if (res.getStatusCode().equals(HttpStatus.OK)) {
                return res.getBody();
            }
        } catch (RestClientException e) {
            logger.error("请求" + PAYMENT_URL + "/payment/create" + "失败", e);
        }
        return CommonResult.empty(res == null ? 500 : res.getStatusCodeValue(), "请求失败");
    }

    @GetMapping("/{id}")
    public CommonResult<Payment> getPayment(@PathVariable String id) {
        ResponseEntity<CommonResult<Payment>> res = null;
        try {
            res = restTemplate.exchange(PAYMENT_URL + "/payment/" + id, HttpMethod.GET, null, new ParameterizedTypeReference<CommonResult<Payment>>() {});
            if (res.getBody() != null)
                logger.info(res.getBody().toString());
            if (res.getStatusCode().equals(HttpStatus.OK)) {
                return res.getBody();
            }
        } catch (RestClientException e) {
            logger.error("请求" + PAYMENT_URL + "/payment/" + id + "失败", e);
        }
        return CommonResult.empty(res == null ? 500 : res.getStatusCodeValue(), "请求失败");
    }


}
