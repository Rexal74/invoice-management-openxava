package com.yourcompany.invoice.util;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openxava.util.PropertiesReader;
import org.openxava.util.XavaResources;

public class InvoicingPreferences {
    private final static String FILE_PROPERTIES = "invoice.properties";
    private static Log log = (Log) LogFactory.getLog(InvoicingPreferences.class);
    private static Properties properties ;

    private static Properties getProperties() {
        if (properties == null) {
            PropertiesReader reader =
                    new PropertiesReader(InvoicingPreferences.class, FILE_PROPERTIES);
            try{
                properties = reader.get();
            }
            catch (IOException ex){
                log.error(XavaResources.getString(
                        "properties_file_error", FILE_PROPERTIES),ex);
                properties = new Properties();
            }
            }
        return properties;
        }
        public static BigDecimal getDefaultVatPercentage() {
        return new BigDecimal(getProperties().getProperty("defaultVatPercentage"));
    }
}
