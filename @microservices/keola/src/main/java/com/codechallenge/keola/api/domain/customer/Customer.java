package com.codechallenge.keola.api.domain.customer;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long customerId;

    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private String lastname;

    @Setter
    @Getter
    @Column(unique = true)
    private String email;

    @Getter
    private String password_hash;

    @Setter
    @Getter
    private String phone;

    private boolean active = true;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Customer() {
    }

    public Customer( String name, String lastname, String email, String phone) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
    }

}
