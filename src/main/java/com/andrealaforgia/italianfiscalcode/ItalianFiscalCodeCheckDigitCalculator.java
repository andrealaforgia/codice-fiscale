package com.andrealaforgia.italianfiscalcode;


import org.apache.commons.lang3.StringUtils;

public class ItalianFiscalCodeCheckDigitCalculator {
    static final int ENCODED_PERSON_LENGTH = 15;
    static final int FISCAL_CODE_LENGTH = ENCODED_PERSON_LENGTH + 1;
    private static final int ALPHABET_SIZE = 26;
    private static final int DIGIT_TO_LETTER_OFFSET = 10;
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

    static String appendCheckDigit(String encodedPerson) {
        return encodedPerson + calculate(encodedPerson);
    }

    public static char calculate(String encodedPerson) {
        if (StringUtils.isEmpty(encodedPerson) || encodedPerson.length() != ENCODED_PERSON_LENGTH) {
            throw new IllegalArgumentException("Invalid encoded person data");
        }
        int sum = 0;
        for (int charIndex = 0; charIndex < encodedPerson.length(); charIndex++) {
            int[] values = isOdd(charIndex + 1) ? ODD_CHAR_VALUES : EVEN_CHAR_VALUES;
            if (Character.isDigit(encodedPerson.charAt(charIndex))) {
                sum += values[encodedPerson.charAt(charIndex) - '0'];
            } else {
                sum += values[encodedPerson.charAt(charIndex) - 'A' + DIGIT_TO_LETTER_OFFSET];
            }
        }
        return CHECK_DIGITS.charAt(sum % ALPHABET_SIZE);
    }

    private static boolean isOdd(int position) {
        return !isEven(position);
    }

    private static boolean isEven(int position) {
        return (position % 2) == 0;
    }
}
