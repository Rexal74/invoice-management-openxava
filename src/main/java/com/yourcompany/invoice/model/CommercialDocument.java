package com.yourcompany.invoice.model;

import com.yourcompany.invoice.calculators.NextNumberForYearCalculator;
import com.yourcompany.invoice.calculators.VatPercentageCalculator;
import lombok.Getter;
import lombok.Setter;
import org.openxava.annotations.*;
import org.openxava.calculators.CurrentLocalDateCalculator;
import org.openxava.calculators.CurrentYearCalculator;
import org.openxava.jpa.XPersistence;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;

@Entity @Getter
@Setter
@View(members = "year, number, date;" +
        "data {" +
        "customer;" +
        "details;" +
        "remarks"+
        "}"
)
abstract public class CommercialDocument extends Deletable {

    @Column(length = 4)
    @DefaultValueCalculator(CurrentYearCalculator.class)
    int year;

    // @Column(length = 6)
    //@DefaultValueCalculator(value = NextNumberForYearCalculator.class,
      //      properties =  @PropertyValue(name = "year"))
    @ReadOnly
    int number;

    @Required
    @DefaultValueCalculator(CurrentLocalDateCalculator.class)
    LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @ReferenceView("Simple")
    Customer customer;

    @ElementCollection
    @ListProperties("product.number, product.description, quantity, pricePerUnit,"+
            "amount+[" +
            "commercialDocument.vatPercentage,"+
            "commercialDocument.vat,"+
            "commercialDocument.totalAmount"+
            "]"
    )
    Collection<Detail> details;

    @DefaultValueCalculator(VatPercentageCalculator.class)
    @Digits(integer = 2, fraction = 0)
    BigDecimal vatPercentage;

  @ReadOnly
  @Money
  @Calculation("sum(details.amount) * vatPercentage / 100")
  BigDecimal vat;

    @ReadOnly
    @Money
    @Calculation("sum(details.amount) + vat")
    BigDecimal totalAmount;

    @PrePersist
    private void calculate() throws Exception{
        Query query = XPersistence.getManager()
                .createQuery("select max(i.number) from " +
                        getClass().getSimpleName() +
                        " i where i.year = :year");
        query.setParameter("year", year);
        Integer lastNumber = (Integer) query.getSingleResult();
        this.number = lastNumber == null ? 1: lastNumber+1;

    }
    @TextArea
    String remarks;
}
