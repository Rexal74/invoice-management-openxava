package com.yourcompany.invoice.model;

import lombok.Getter;
import lombok.Setter;
import org.openxava.annotations.Required;
import org.openxava.annotations.View;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity @Getter
@Setter
@View(name = "Simple",
        members = "number, name"
)
public class Customer {
    @Id
    @Column(length=6)
    int number;

    @Required
    @Column(length = 50)
    String name;

    @Embedded
    Address address;
}
