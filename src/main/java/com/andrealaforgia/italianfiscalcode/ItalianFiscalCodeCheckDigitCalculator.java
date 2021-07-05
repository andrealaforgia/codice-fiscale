package com.andrealaforgia.italianfiscalcode;


import org.apache.commons.lang3.StringUtils;

public class ItalianFiscalCodeCheckDigitCalculator {
    private static final String CHECK_DIGITS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final int[] ODD_CHAR_VALUES = new int[]{
            1, 0, 5, 7, 9, 13, 15, 17, 19, 21,
            1, 0, 5, 7, 9, 13, 15, 17, 19, 21,
            2, 4, 18, 20, 11, 3, 6, 8, 12, 14,
            16, 10, 22, 25, 24, 23
    };
    private static final int[] EVEN_CHAR_VALUES = new int[]{
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
            10, 11, 12, 13, 14, 15, 16, 17,
            18, 19, 20, 21, 22, 23, 24, 25
    };

    public static char calculate(String encodedPerson) {
        if (StringUtils.isEmpty(encodedPerson) || encodedPerson.length() != 15) {
            throw new IllegalArgumentException("Invalid encoded person data");
        }
        int sum = 0;
        for (int i=0; i<encodedPerson.length(); i++) {
            int[] values = isOdd(i+1) ? ODD_CHAR_VALUES : EVEN_CHAR_VALUES;
            if (Character.isDigit(encodedPerson.charAt(i))) {
                sum += values[encodedPerson.charAt(i)-'0'];
            } else {
                sum += values[encodedPerson.charAt(i)-'A' + 10];
            }
        }
        return CHECK_DIGITS.charAt(sum % 26);
    }

    private static boolean isOdd(int i) {
        return !isEven(i);
    }

    private static boolean isEven(int i) {
        return (i % 2) == 0;
    }
}
