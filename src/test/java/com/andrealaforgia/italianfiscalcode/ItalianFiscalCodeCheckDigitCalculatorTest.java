package com.andrealaforgia.italianfiscalcode;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class ItalianFiscalCodeCheckDigitCalculatorTest {

    @ParameterizedTest
    @MethodSource("provideValidParameters")
    void shouldCalculateCheckDigit(String encodedPerson, Character expectedCheckDigit) {
        assertThat(ItalianFiscalCodeCheckDigitCalculator.calculate(encodedPerson))
                .isEqualTo(expectedCheckDigit);
    }

    @Test
    void shouldRejectInvalidParametersWhenCalculatingCheckiDigit() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> ItalianFiscalCodeCheckDigitCalculator.calculate(null));
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> ItalianFiscalCodeCheckDigitCalculator.calculate(""));
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> ItalianFiscalCodeCheckDigitCalculator.calculate(" "));
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> ItalianFiscalCodeCheckDigitCalculator.calculate("RSSMRA64A01H50"));
    }

    private static Stream<Arguments> provideValidParameters() {
        return Stream.of(
                Arguments.of("RSSMRA64A01H501", 'U'),
                Arguments.of("BNCCRN54T71A001", 'C')
        );
    }
}