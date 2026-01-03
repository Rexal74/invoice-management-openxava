package com.yourcompany.invoice.calculators;

import com.yourcompany.invoice.util.InvoicingPreferences;
import org.openxava.calculators.ICalculator;

public class VatPercentageCalculator implements ICalculator {
     public Object calculate() throws Exception {
         return InvoicingPreferences.getDefaultVatPercentage();
     }
}
