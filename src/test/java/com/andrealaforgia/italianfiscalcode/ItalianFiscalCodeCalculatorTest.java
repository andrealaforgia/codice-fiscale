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

    private static Stream<Arguments> provideParameters() {
        return Stream.of(
                Arguments.of(
                        Person.builder().firstName("Mario").lastName("Rossi").dateOfBirth(LocalDate.of(1964, 1, 1)).gender(Gender.Male).placeOfBirthCode("H501").build(),
                        "RSSMRA64A01H501U"
                ),
                Arguments.of(
                        Person.builder().firstName("Caterina").lastName("Bianchi").dateOfBirth(LocalDate.of(1954, 12, 31)).gender(Gender.Female).placeOfBirthCode("A001").build(),
                        "BNCCRN54T71A001C"
                )
        );
    }
}