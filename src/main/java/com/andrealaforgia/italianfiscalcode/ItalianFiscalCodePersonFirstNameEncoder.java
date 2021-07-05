package com.andrealaforgia.italianfiscalcode;

import java.util.Locale;

import static org.apache.commons.lang3.ObjectUtils.firstNonNull;

public class ItalianFiscalCodePersonFirstNameEncoder {
    public static String encode(String firstName) {
        return encodeFirstName(firstNonNull(firstName, "")
                .replaceAll(" ", "")
                .toUpperCase(Locale.ROOT));
    }

    private static String encodeFirstName(String firstName) {
        char[] consonants = new char[firstName.length()];
        int consonantCount = 0;
        StringBuilder vowels = new StringBuilder();
        for (int i = 0; i < firstName.length(); i++) {
            if (isConsonant(firstName.charAt(i))) {
                consonants[consonantCount++] = firstName.charAt(i);
                if (consonantCount == 4) {
                    return new String(new char[]{consonants[0], consonants[2], consonants[3]});
                }
            } else {
                vowels.append(firstName.charAt(i));
            }
        }
        StringBuilder encodedFirstName = new StringBuilder();
        for (int i=0; i<consonantCount; i++) {
            encodedFirstName.append(consonants[i]);
        }
        String vowelsAsString = vowels.toString();
        int vowelsToAppendCount = 3 - consonantCount;
        int vowelsAppendedCount = Math.min(vowelsAsString.length(), vowelsToAppendCount);
        encodedFirstName.append(vowelsAsString, 0, vowelsAppendedCount);
        encodedFirstName.append("XXX", 0, 3 - consonantCount - vowelsAppendedCount);
        return encodedFirstName.toString();
    }

    private static boolean isConsonant(char c) {
        return !isVowel(c);
    }

    private static boolean isVowel(int c) {
        return "AEIOU".indexOf(c) != -1;
    }
}
