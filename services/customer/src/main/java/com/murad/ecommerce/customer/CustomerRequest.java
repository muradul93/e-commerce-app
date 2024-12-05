package com.murad.ecommerce.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(String id,
                              @NotNull(message = "firstname is required")
                              String firstname,
                              String lastname,
                              @NotNull(message = "email is required")
                              @Email(message = "email is not valid")
                              String email,
                              @NotNull(message = "phone is required")
                              String phone,
                              Address address) {
}
