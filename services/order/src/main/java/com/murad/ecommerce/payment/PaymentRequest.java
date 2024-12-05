package com.murad.ecommerce.payment;

import com.murad.ecommerce.customer.CustomerResponse;
import com.murad.ecommerce.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
    BigDecimal amount,
    PaymentMethod paymentMethod,
    Integer orderId,
    String referenceNo,
    CustomerResponse customer
    ) {
}
