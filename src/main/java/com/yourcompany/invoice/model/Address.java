package com.yourcompany.invoice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable @Getter
@Setter
public class Address {
    @Column(length = 30)
    String street;

    @Column(length = 6)
    String zipCode;

    @Column(length = 30)
    String city;

    @Column(length = 30)
    String country;
}
