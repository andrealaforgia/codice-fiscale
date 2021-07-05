package com.andrealaforgia.italianfiscalcode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class ItalianFiscalCodeValidatorTest {

    @ParameterizedTest
    @MethodSource("provideValidParameters")
    void shouldValidate(String fiscalCode, String expectedFiscalCode) {
        assertThat(ItalianFiscalCodeValidator.validate(fiscalCode))
                .isEqualTo(expectedFiscalCode);
    }

    @ParameterizedTest
    @MethodSource("provideInvalidParameters")
    void shouldRejectInvalidParameters(String fiscalCode) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> ItalianFiscalCodeValidator.validate(fiscalCode));
    }

    private static Stream<Arguments> provideValidParameters() {
        return Stream.of(
                Arguments.of("RSSMRA64A01H50MU", "RSSMRA64A01H50MM"),
                Arguments.of("RSSMRA64A01H5L1U", "RSSMRA64A01H5L1F"),
                Arguments.of("RSSMRA6QA01H501U", "RSSMRA6QA01H501G"),
                Arguments.of("RSSMRAS4A01H501U", "RSSMRAS4A01H501R"),
                Arguments.of("BNCCRN54T71A001C", "BNCCRN54T71A001C"),
                Arguments.of("MRTMTT91D08F205J", "MRTMTT91D08F205J"),
                Arguments.of("MLLSNT82P65Z404U", "MLLSNT82P65Z404U"),
                Arguments.of("RSSMRA64A01H50MX", "RSSMRA64A01H50MM"),
                Arguments.of("RSSMRA64A01H5L1X", "RSSMRA64A01H5L1F"),
                Arguments.of("RSSMRA6QA01H501Y", "RSSMRA6QA01H501G"),
                Arguments.of("RSSMRAS4A01H501Z", "RSSMRAS4A01H501R"),
                Arguments.of("BNCCRN54T71A001A", "BNCCRN54T71A001C"),
                Arguments.of("MRTMTT91D08F205B", "MRTMTT91D08F205J"),
                Arguments.of("MLLSNT82P65Z404C", "MLLSNT82P65Z404U")
        );
    }

    private static Stream<Arguments> provideInvalidParameters() {
        return Stream.of(
                Arguments.of((String)null),
                Arguments.of(""),
                Arguments.of("  "),
                Arguments.of("RSSMRA64"),
                Arguments.of("RSSMRA64A01H50M"),
                Arguments.of("RSSMRA64A01H50MUX"),
                Arguments.of("RSSMRA64A$1H50MU")
        );
    }
}