package com.codechallenge.keola.api.domain.customer;

import lombok.Getter;
import lombok.Setter;

public class Customer {

    private String customerId;
    @Setter
    @Getter
    private String name;
    @Setter
    @Getter
    private String lastname;
    @Setter
    @Getter
    private String email;
    @Getter
    private String password_hash;
    @Setter
    @Getter
    private String phone;
}
