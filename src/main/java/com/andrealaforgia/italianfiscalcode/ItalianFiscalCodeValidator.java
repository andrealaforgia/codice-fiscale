package com.andrealaforgia.italianfiscalcode;

import org.apache.commons.lang3.StringUtils;

public class ItalianFiscalCodeValidator {

    public static String validate(String fiscalCode) {
        if (isNotFiscalCode(fiscalCode)) {
            throw new IllegalArgumentException("Invalid fiscal code");
        }
        String encodedPerson = fiscalCode.substring(0, 15);
        return encodedPerson + ItalianFiscalCodeCheckDigitCalculator.calculate(encodedPerson);
    }

    private static boolean isNotFiscalCode(String fiscalCode) {
        return !isFiscalCode(fiscalCode);
    }

    private static boolean isFiscalCode(String fiscalCode) {
        if (StringUtils.isEmpty(fiscalCode) || fiscalCode.length() != 16) {
            return false;
        }
        return fiscalCode.chars().allMatch(c -> Character.isAlphabetic(c) || Character.isDigit(c));
    }
}
