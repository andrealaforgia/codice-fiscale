package com.andrealaforgia.italianfiscalcode;

import java.time.LocalDate;

public class ItalianFiscalCodePersonDateOfBirthEncoder {

    private static final String MONTHS = "ABCDEHLMPRST";

    public static String encode(LocalDate dateOfBirth, Gender gender) {
        if (dateOfBirth == null) {
            throw new IllegalArgumentException("Date of birth is required");
        }
        if (gender == null) {
            throw new IllegalArgumentException("Gender is required");
        }
        char monthLetter = MONTHS.charAt(dateOfBirth.getMonthValue() - 1);
        int day = dateOfBirth.getDayOfMonth() + (gender == Gender.Female ? 40 : 0);
        return String.format("%02d%c%02d", dateOfBirth.getYear() % 100, monthLetter, day);
    }
}
