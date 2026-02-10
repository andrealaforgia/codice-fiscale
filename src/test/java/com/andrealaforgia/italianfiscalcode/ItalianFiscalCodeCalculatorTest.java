package com.andrealaforgia.italianfiscalcode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ItalianFiscalCodeCalculatorTest {

    @ParameterizedTest()
    @MethodSource("provideParameters")
    void shouldCalculateItalianFiscalCode(Person person, String expectedFiscalCode) {
        String fiscalCode = ItalianFiscalCodeCalculator.calculate(person);
        assertThat(fiscalCode).isEqualTo(expectedFiscalCode);
    }

    static class TaxCodeCalculationTestScope {
        Person person;
        String calculatedTaxCode;

        public void givenPerson(Person person) {
            this.person = person;
        }

        public void whenCalculatingTaxCode() {
            calculatedTaxCode = ItalianFiscalCodeCalculator.calculate(person);
        }

        public void thenTaxCodeIs(String expectedTaxCode) {
            assertThat(calculatedTaxCode).isEqualTo(expectedTaxCode);
        }
    }

    void shouldCalculateTaxCode(Person person, String expectedTaxCode) {
        TaxCodeCalculationTestScope scope = new TaxCodeCalculationTestScope();
        scope.givenPerson(person);
        scope.whenCalculatingTaxCode();
        scope.thenTaxCodeIs(expectedTaxCode);
    }

    private static Stream<Arguments> provideParameters() {
        return Stream.of(
                Arguments.of(
                        Person.builder().firstName("Mario").lastName("Rossi").dateOfBirth(LocalDate.of(1964, 1, 1)).gender(Gender.Male).placeOfBirthCode("H501").build(),
                        "RSSMRA64A01H501U"
                ),
                Arguments.of(
                        Person.builder().firstName("Caterina").lastName("Bianchi").dateOfBirth(LocalDate.of(1954, 12, 31)).gender(Gender.Female).placeOfBirthCode("A001").build(),
                        "BNCCRN54T71A001C"
                ),
                Arguments.of(
                        Person.builder().firstName("Matteo").lastName("Moretti").dateOfBirth(LocalDate.of(1991, 4, 8)).gender(Gender.Male).placeOfBirthCode("F205").build(),
                        "MRTMTT91D08F205J"
                ),
                Arguments.of(
                        Person.builder().firstName("Samantha").lastName("Miller").dateOfBirth(LocalDate.of(1982, 9, 25)).gender(Gender.Female).placeOfBirthCode("Z404").build(),
                        "MLLSNT82P65Z404U"
                )
        );
    }
}