package com.andrealaforgia.italianfiscalcode;

import org.apache.commons.lang3.StringUtils;

public class ItalianFiscalCodeDisambiguator {

    private static final String DISAMBIGUATION_CHARS = "LMNPQRSTUV";

    public static String disambiguate(String fiscalCode, int charIndex) {
        if (StringUtils.isEmpty(fiscalCode)
                || fiscalCode.length() != 16
                || charIndex < 0
                || charIndex > 15
                || !Character.isDigit(fiscalCode.charAt(charIndex))
        ) {
            throw new IllegalArgumentException("Invalid fiscal code");
        }
        String encodedPerson = (fiscalCode.substring(0, charIndex) +
                DISAMBIGUATION_CHARS.charAt(fiscalCode.charAt(charIndex)-'0') +
                fiscalCode.substring(charIndex+1)).substring(0, 15);
        return encodedPerson + ItalianFiscalCodeCheckDigitCalculator.calculate(encodedPerson);
    }
}
