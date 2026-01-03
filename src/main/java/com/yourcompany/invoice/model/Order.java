package com.yourcompany.invoice.model;

import lombok.Getter;
import lombok.Setter;
import org.openxava.annotations.*;
import org.openxava.util.XavaResources;

import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import java.time.DayOfWeek;


@Entity @Getter
@Setter
@View(extendsView = "super.DEFAULT",
members = "estimatedDeliveryDays, delivered;"+
        "invoice { invoice }"
)
@View(name = "NoCustomerNoInvoice",
members = "year, number, date;" +
"details;" +
"remarks")


public class Order extends CommercialDocument {
@ManyToOne
@ReferenceView("NoCustomerNoOrders")
Invoice invoice;

@Depends("date")
public int getEstimatedDeliveryDays() {
if(getDate().getDayOfYear() < 15) return 20 - getDate().getDayOfYear();
if(getDate().getDayOfWeek() == DayOfWeek.SATURDAY) return 3;
if(getDate().getDayOfWeek() == DayOfWeek.SUNDAY) return 2;
return 1;
}
@Column( columnDefinition = "INTEGER DEFAULT 1")
    int deliveryDays;

//@PrePersist @PreUpdate
    private void recalculateDeliveryDays() {
    setDeliveryDays(getEstimatedDeliveryDays());
}

@Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    boolean delivered ;

@PrePersist @PreUpdate
    private void validate() throws Exception{
    if(invoice != null && !isDelivered()) {
        throw new javax.validation.ValidationException(XavaResources.getString(
                "order_must_be_delivered",
                getYear(), getNumber()
        )
        );
    }
}
   // @AssertTrue(
           // message="order_must_be_delivered"
    //)
   // private boolean isDeliveredToBeInInvoice(){
       // return// invoice == null && !isDelivered();
   // }


    //private void setInvoice(Invoice invoice) {
       // if(invoice == null && !isDelivered()) {
            //throw new javax.validation.ValidationException(
                    //XavaResources.getString(
                     //       "order_must_be_delivered",
                     //       getYear(),
                     //       getNumber()
                   // )
           // );
       // }
       // this.invoice = invoice;
   // }

    @PreRemove
    private void validateOnRemove(){
    if (invoice != null ){
        throw new javax.validation.ValidationException(
                XavaResources.getString("cannot_delete_order_with_invoice")
        );
    }
    }

    public void setDeleted(boolean deleted) {
    if  (deleted) validateOnRemove();
    super.setDeleted(deleted);
    }
}
