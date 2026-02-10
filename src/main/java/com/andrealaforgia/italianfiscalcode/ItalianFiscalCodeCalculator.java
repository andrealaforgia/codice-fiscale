package com.andrealaforgia.italianfiscalcode;

public class ItalianFiscalCodeCalculator {

    public static String calculate(Person person) {
        return ItalianFiscalCodeCheckDigitCalculator.appendCheckDigit(
                ItalianFiscalCodePersonEncoder.encode(person));
    }
}
