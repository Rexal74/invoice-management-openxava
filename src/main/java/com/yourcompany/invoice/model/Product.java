package com.yourcompany.invoice.model;

import com.yourcompany.invoice.annotations.ISBN;
import lombok.Getter;
import lombok.Setter;
import org.openxava.annotations.DescriptionsList;
import org.openxava.annotations.Files;
import org.openxava.annotations.Money;
import org.openxava.annotations.TextArea;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity @Getter @Setter
public class Product {
    @Id
    @Column(length = 13)
    int number;

    @Column(length = 50)
    String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @DescriptionsList
    Category category;

    @Money
    BigDecimal price;

    @Files
    @Column(length = 32)
    String photos;

    @ManyToOne(fetch = FetchType.LAZY)
    @DescriptionsList
    Author author;

    @Column(length=13)
    @ISBN
    String isbn;

    @TextArea
    String remarks;
}
