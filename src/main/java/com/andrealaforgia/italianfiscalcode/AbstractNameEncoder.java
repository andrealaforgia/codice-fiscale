package com.andrealaforgia.italianfiscalcode;

import java.util.Locale;

import static org.apache.commons.lang3.ObjectUtils.firstNonNull;

abstract class AbstractNameEncoder {
    static final int ENCODED_NAME_LENGTH = 3;
    static final String PADDING = "XXX";

    String encodeName(String name) {
        String sanitizedName = sanitize(name);
        return buildEncodedName(sanitizedName);
    }

    private String sanitize(String name) {
        return firstNonNull(name, "")
                .replaceAll(" ", "")
                .toUpperCase(Locale.ROOT);
    }

    private String buildEncodedName(String sanitizedName) {
        StringBuilder consonants = new StringBuilder();
        StringBuilder vowels = new StringBuilder();
        for (int charIndex = 0; charIndex < sanitizedName.length(); charIndex++) {
            char character = sanitizedName.charAt(charIndex);
            if (CharacterClassifier.isConsonant(character)) {
                consonants.append(character);
            } else {
                vowels.append(character);
            }
        }
        String selectedConsonants = selectConsonants(consonants.toString());
        int consonantCount = selectedConsonants.length();

        StringBuilder encodedName = new StringBuilder(selectedConsonants);

        String vowelsAsString = vowels.toString();
        int vowelsToAppendCount = ENCODED_NAME_LENGTH - consonantCount;
        int vowelsAppendedCount = Math.min(vowelsAsString.length(), vowelsToAppendCount);
        encodedName.append(vowelsAsString, 0, vowelsAppendedCount);
        encodedName.append(PADDING, 0, ENCODED_NAME_LENGTH - consonantCount - vowelsAppendedCount);
        return encodedName.toString();
    }

    /**
     * Selects which consonants to use for the encoded name.
     * Subclasses define the selection strategy.
     *
     * @param consonants all consonants from the name, in order
     * @return the selected consonants (at most ENCODED_NAME_LENGTH characters)
     */
    abstract String selectConsonants(String consonants);
}
