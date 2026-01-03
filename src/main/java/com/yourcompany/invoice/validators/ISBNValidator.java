package com.yourcompany.invoice.validators;

import javax.validation.*;
import javax.ws.rs.client.*; // To use JAX-RS
import com.yourcompany.invoice.annotations.*;
import org.apache.commons.logging.*; // To use Log
import org.openxava.util.*;

public class ISBNValidator implements ConstraintValidator<ISBN, Object> {

    private boolean search;

    private static Log log = LogFactory.getLog(ISBNValidator.class);

    private static  org.apache.commons.validator.routines.ISBNValidator
            validator =
            new org.apache.commons.validator.routines.ISBNValidator();

    public void initialize(ISBN isbn){
        this.search =  isbn.search();
    }

    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        if(Is.empty(value)) return true;
        if(!validator.isValid(value.toString())) return false;
        return search ? isbnExists(value) : true;
    }
    private boolean isbnExists(Object isbn) {
        try {

            String response = ClientBuilder.newClient()
                    .target("http://openlibrary.org/")
                    .path("/api/books")
                    .queryParam("jscmd", "data")
                    .queryParam("format", "json")
                    .queryParam("bibkeys", "ISBN:" + isbn)
                    .request()
                    .get(String.class);
            return !response.equals("{}");
        }
        catch (Exception ex) {
            log.warn("Impossible to connect to openlibrary.org " +
                    "to validate the ISBN. Validation fails", ex);
            return false;
        }
    }
    }


