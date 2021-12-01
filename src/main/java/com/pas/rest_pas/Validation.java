package com.pas.rest_pas;

import org.apache.commons.validator.routines.EmailValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    public Validation() {
    }

    public static boolean validateData(String parameter, ValidationParameter validationParameter) {
        String regex;
        Pattern p;
        Matcher m;

        switch(validationParameter) {
            case FIRSTNAME:
            case LASTNAME:
            case CITY:
                regex = "[A-Za-z]";
                p = Pattern.compile(regex);
                m = p.matcher(parameter);

                return m.matches();

                // to najprawdopodobniej nie działa
            case POSTAL_CODE:
                regex = "[[1-9][0-9]-[1-9][0-9][0-9]]";
                p = Pattern.compile(regex);
                m = p.matcher(parameter);

                return m.matches();
        }
        return false;
    }

}
