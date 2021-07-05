package com.andrealaforgia.italianfiscalcode;

import java.util.Locale;

import static org.apache.commons.lang3.ObjectUtils.firstNonNull;

public class ItalianFiscalCodePersonLastNameEncoder {
    public static String encode(String lastName) {
        return encodeLastName(firstNonNull(lastName, "")
                .replaceAll(" ", "")
                .toUpperCase(Locale.ROOT));
    }

    private static String encodeLastName(String lastName) {
        StringBuilder encodedLastName = new StringBuilder();
        StringBuilder vowels = new StringBuilder();
        int consonantCount = 0;
        for (int i = 0; i < lastName.length(); i++) {
            if (isConsonant(lastName.charAt(i))) {
                encodedLastName.append(lastName.charAt(i));
                if (++consonantCount == 3) {
                    return encodedLastName.toString();
                }
            } else {
                vowels.append(lastName.charAt(i));
            }
        }
        String vowelsAsString = vowels.toString();
        int vowelsToAppendCount = 3 - consonantCount;
        int vowelsAppendedCount = Math.min(vowelsAsString.length(), vowelsToAppendCount);
        encodedLastName.append(vowelsAsString, 0, vowelsAppendedCount);
        encodedLastName.append("XXX", 0, 3 - consonantCount - vowelsAppendedCount);
        return encodedLastName.toString();
    }

    private static boolean isConsonant(char c) {
        return !isVowel(c);
    }

    private static boolean isVowel(int c) {
        return "AEIOU".indexOf(c) != -1;
    }
}
