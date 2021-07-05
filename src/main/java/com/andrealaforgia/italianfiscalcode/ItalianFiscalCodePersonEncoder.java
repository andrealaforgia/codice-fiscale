package com.andrealaforgia.italianfiscalcode;

public class ItalianFiscalCodePersonEncoder {
    public static String encode(Person person) {
        return ItalianFiscalCodePersonLastNameEncoder.encode(person.getLastName()) +
                ItalianFiscalCodePersonFirstNameEncoder.encode(person.getFirstName()) +
                ItalianFiscalCodePersonDateOfBirthEncoder.encode(person.getDateOfBirth(), person.getGender()) +
                person.getPlaceOfBirthCode();
    }
}
