package com.andrealaforgia.italianfiscalcode;

import org.apache.commons.lang3.StringUtils;

public class ItalianFiscalCodeValidator {

    public static String validate(String fiscalCode) {
        if (isNotFiscalCode(fiscalCode)) {
            throw new IllegalArgumentException("Invalid fiscal code");
        }
        return ItalianFiscalCodeCheckDigitCalculator.appendCheckDigit(
                fiscalCode.substring(0, ItalianFiscalCodeCheckDigitCalculator.ENCODED_PERSON_LENGTH));
    }

    private static boolean isNotFiscalCode(String fiscalCode) {
        return !isFiscalCode(fiscalCode);
    }

    private static boolean isFiscalCode(String fiscalCode) {
        if (StringUtils.isEmpty(fiscalCode) || fiscalCode.length() != ItalianFiscalCodeCheckDigitCalculator.FISCAL_CODE_LENGTH) {
            return false;
        }
        return fiscalCode.chars().allMatch(character -> Character.isAlphabetic(character) || Character.isDigit(character));
    }
}
