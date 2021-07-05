package com.andrealaforgia.italianfiscalcode;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

enum Gender {
    Male,
    Female
}

@Builder
@Value
public class Person {
    String firstName;
    String lastName;
    LocalDate dateOfBirth;
    String placeOfBirthCode;
    Gender gender;
}
