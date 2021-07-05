package com.andrealaforgia.italianfiscalcode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class ItalianFiscalCodePersonDateOfBirthEncoderTest {

    @ParameterizedTest
    @MethodSource("provideValidParameters")
    void shouldEncodeDateOfBirth(LocalDate dateOfBirth, Gender gender, String expectedEncodedDateOfBirth) {
        assertThat(ItalianFiscalCodePersonDateOfBirthEncoder.encode(dateOfBirth, gender)).isEqualTo(expectedEncodedDateOfBirth);
    }

    @ParameterizedTest
    @MethodSource("provideInvalidParameters")
    void shouldRejectInvalidParametersWhenEncodingDateOfBirth(LocalDate dateOfBirth, Gender gender) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> ItalianFiscalCodePersonDateOfBirthEncoder.encode(dateOfBirth, gender));
    }

    private static Stream<Arguments> provideValidParameters() {
        return Stream.of(
                Arguments.of(LocalDate.of(1901, 1, 1), Gender.Male, "01A01"),
                Arguments.of(LocalDate.of(1901, 1, 1), Gender.Female, "01A41"),
                Arguments.of(LocalDate.of(1920, 3, 1), Gender.Male, "20C01"),
                Arguments.of(LocalDate.of(1920, 3, 1), Gender.Female, "20C41"),
                Arguments.of(LocalDate.of(1950, 5, 1), Gender.Male, "50E01"),
                Arguments.of(LocalDate.of(1950, 5, 1), Gender.Female, "50E41"),
                Arguments.of(LocalDate.of(1970, 7, 1), Gender.Male, "70L01"),
                Arguments.of(LocalDate.of(1970, 7, 1), Gender.Female, "70L41"),
                Arguments.of(LocalDate.of(1999, 9, 1), Gender.Male, "99P01"),
                Arguments.of(LocalDate.of(1999, 9, 30), Gender.Female, "99P70")
        );
    }

    private static Stream<Arguments> provideInvalidParameters() {
        return Stream.of(
                Arguments.of(null, Gender.Male),
                Arguments.of(LocalDate.of(1999, 9, 30), null)
        );
    }
}