package com.murad.ecommerce.kafka;

import com.murad.ecommerce.customer.CustomerResponse;
import com.murad.ecommerce.order.PaymentMethod;
import com.murad.ecommerce.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation (
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products

) {
}
