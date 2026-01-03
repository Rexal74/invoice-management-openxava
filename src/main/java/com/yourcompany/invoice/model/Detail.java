package com.yourcompany.invoice.model;

import com.yourcompany.invoice.calculators.PricePerUnitCalculator;
import lombok.Getter;
import lombok.Setter;
import org.openxava.annotations.DefaultValueCalculator;
import org.openxava.annotations.Depends;
import org.openxava.annotations.Money;
import org.openxava.annotations.PropertyValue;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Embeddable @Getter
@Setter
public class Detail {
    int quantity;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    Product product;

    @DefaultValueCalculator(value = PricePerUnitCalculator.class,
            properties = @PropertyValue(
                    name = "productNumber",
                    from = "product.number")
    )
    @Money
    BigDecimal pricePerUnit;

    @Money
    @Depends("pricePerUnit,  quantity")
    public BigDecimal getAmount(){
        if(pricePerUnit == null) return BigDecimal.ZERO;
        return new BigDecimal(quantity).multiply(pricePerUnit);



    }
}
