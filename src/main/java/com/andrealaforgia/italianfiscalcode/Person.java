package com.andrealaforgia.italianfiscalcode;

import java.time.LocalDate;

enum Gender {
    Male,
    Female
}

public class Person {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String placeOfBirthCode;
    private Gender gender;

    private Person(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.dateOfBirth = builder.dateOfBirth;
        this.placeOfBirthCode = builder.placeOfBirthCode;
        this.gender = builder.gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPlaceOfBirthCode() {
        return placeOfBirthCode;
    }

    public Gender getGender() {
        return gender;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String firstName;
        private String lastName;
        private LocalDate dateOfBirth;
        private String placeOfBirthCode;
        private Gender gender;

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder dateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public Builder placeOfBirthCode(String placeOfBirthCode) {
            this.placeOfBirthCode = placeOfBirthCode;
            return this;
        }

        public Builder gender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }
}
