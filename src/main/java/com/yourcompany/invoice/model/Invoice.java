package com.yourcompany.invoice.model;

import lombok.Getter;
import lombok.Setter;
import org.openxava.annotations.CollectionView;
import org.openxava.annotations.Hidden;
import org.openxava.annotations.View;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity @Getter
@Setter

@View(extendsView = "super.DEFAULT",
members = "orders { orders }")

@View(name = "NoCustomerNoOrders",
members =
"year, number, date;" +
"details;" +
"remarks"
)
public class Invoice extends CommercialDocument {
    @OneToMany(mappedBy = "invoice")
    @CollectionView("NoCustomerNoInvoice")
    Collection<Order> orders;

    //@Hidden
    //@Column(columnDefinition="BOOLEAN DEFAULT FALSE")
   //boolean deleted;
}
