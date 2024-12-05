package com.murad.ecommerce.payment;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "payment-service",
        url = "${application.config.payment-url}")
public interface PaymentClient {

    @PostMapping
     Integer createPaymentOrder(@RequestBody PaymentRequest request);
}
