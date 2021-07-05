package com.andrealaforgia.italianfiscalcode;

public class ItalianFiscalCodeCalculator {

    public static String calculate(Person person) {
        String encodedPerson = ItalianFiscalCodePersonEncoder.encode(person);
        return encodedPerson + ItalianFiscalCodeCheckDigitCalculator.calculate(encodedPerson);
    }
}
