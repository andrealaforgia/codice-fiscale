package com.andrealaforgia.italianfiscalcode;

import java.time.LocalDate;

public class ItalianFiscalCodePersonDateOfBirthEncoder {

    private static final String MONTH_ENCODING_LETTERS = "ABCDEHLMPRST";
    private static final int FEMALE_DAY_OFFSET = 40;
    private static final int CENTURY_MODULO = 100;

    public static String encode(LocalDate dateOfBirth, Gender gender) {
        if (dateOfBirth == null) {
            throw new IllegalArgumentException("Date of birth is required");
        }
        if (gender == null) {
            throw new IllegalArgumentException("Gender is required");
        }
        char monthLetter = MONTH_ENCODING_LETTERS.charAt(dateOfBirth.getMonthValue() - 1);
        int day = dateOfBirth.getDayOfMonth() + (gender == Gender.Female ? FEMALE_DAY_OFFSET : 0);
        return String.format("%02d%c%02d", dateOfBirth.getYear() % CENTURY_MODULO, monthLetter, day);
    }
}
