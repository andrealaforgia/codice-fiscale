package com.andrealaforgia.italianfiscalcode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class ItalianFiscalCodeDisambiguatorTest {

    @ParameterizedTest
    @MethodSource("provideValidParameters")
    void shouldDisambiguate(String fiscalCode, int charIndex, String expectedFiscalCode) {
        assertThat(ItalianFiscalCodeDisambiguator.disambiguate(fiscalCode, charIndex))
                .isEqualTo(expectedFiscalCode);
    }

    @ParameterizedTest
    @MethodSource("provideInvalidParameters")
    void shouldRejectInvalidParameters(String fiscalCode, int charIndex) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> ItalianFiscalCodeDisambiguator.disambiguate(fiscalCode, charIndex));
    }

    private static Stream<Arguments> provideValidParameters() {
        return Stream.of(
                Arguments.of("RSSMRA64A01H501U", 14, "RSSMRA64A01H50MM"),
                Arguments.of("RSSMRA64A01H501U", 13, "RSSMRA64A01H5L1F"),
                Arguments.of("RSSMRA64A01H501U", 7, "RSSMRA6QA01H501G"),
                Arguments.of("RSSMRA64A01H501U", 6, "RSSMRAS4A01H501R")
        );
    }

    private static Stream<Arguments> provideInvalidParameters() {
        return Stream.of(
                Arguments.of(null, 0),
                Arguments.of("", 0),
                Arguments.of("  ", 0),
                Arguments.of("RSSMRA64", 0),
                Arguments.of("RSSMRA64A01H501U", -1),
                Arguments.of("RSSMRA64A01H501U", 16),
                Arguments.of("RSSMRA64A01H501U", 15)
        );
    }
}