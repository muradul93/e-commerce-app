package com.murad.ecommerce.customer;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class Address {

    private String street;
    private String houseNumber;
    private String zipCode;



}
