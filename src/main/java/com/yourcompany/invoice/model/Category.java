package com.yourcompany.invoice.model;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.Column;
import javax.persistence.Entity;


@Entity @Getter
@Setter
public class Category extends Identifiable {

    @Column(length = 50)
    String description;
}
