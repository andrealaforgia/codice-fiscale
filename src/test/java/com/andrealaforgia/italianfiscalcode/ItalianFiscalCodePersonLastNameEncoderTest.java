package com.andrealaforgia.italianfiscalcode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ItalianFiscalCodePersonLastNameEncoderTest {

    @ParameterizedTest
    @MethodSource("provideParameters")
    void shouldEncodeLastName(String lastName, String expectedEncodedLastName) {
        assertThat(ItalianFiscalCodePersonLastNameEncoder.encode(lastName)).isEqualTo(expectedEncodedLastName);
    }

    private static Stream<Arguments> provideParameters() {
        return Stream.of(
                Arguments.of("Rossi", "RSS"),
                Arguments.of("Rosi", "RSO"),
                Arguments.of("Roi", "ROI"),
                Arguments.of("Aeiou", "AEI"),
                Arguments.of("Z", "ZXX"),
                Arguments.of("A", "AXX"),
                Arguments.of("ZZ", "ZZX"),
                Arguments.of("BCD", "BCD"),
                Arguments.of("ZA", "ZAX"),
                Arguments.of("ZAX", "ZXA"),
                Arguments.of("ZXA", "ZXA"),
                Arguments.of("ZX", "ZXX"),
                Arguments.of(null, "XXX")
        );
    }
}