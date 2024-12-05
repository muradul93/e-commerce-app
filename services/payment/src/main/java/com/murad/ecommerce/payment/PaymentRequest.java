package com.murad.ecommerce.payment;

import java.math.BigDecimal;

public record PaymentRequest(
    Integer id,
    BigDecimal amount,
    String orderId,
    PaymentMethod paymentMethod,
    String referenceNo,
    Customer customer
    ) {
}
