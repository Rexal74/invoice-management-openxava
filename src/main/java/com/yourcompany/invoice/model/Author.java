package com.yourcompany.invoice.model;

import lombok.Getter;
import lombok.Setter;
import org.openxava.annotations.ListProperties;

import javax.persistence.*;
import java.util.Collection;

@Entity @Getter
@Setter
public class Author extends Identifiable {

    @Column(length = 50)
    String name;

    @OneToMany(mappedBy="author")
    @ListProperties("number, description, price")
    Collection<Product> products;
}
