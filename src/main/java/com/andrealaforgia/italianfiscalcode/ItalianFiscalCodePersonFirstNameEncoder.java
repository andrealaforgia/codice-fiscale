package com.andrealaforgia.italianfiscalcode;

public class ItalianFiscalCodePersonFirstNameEncoder extends AbstractNameEncoder {
    private static final ItalianFiscalCodePersonFirstNameEncoder INSTANCE = new ItalianFiscalCodePersonFirstNameEncoder();
    private static final int FIRST_NAME_CONSONANT_SKIP_THRESHOLD = 4;

    public static String encode(String firstName) {
        return INSTANCE.encodeName(firstName);
    }

    @Override
    String selectConsonants(String consonants) {
        if (consonants.length() >= FIRST_NAME_CONSONANT_SKIP_THRESHOLD) {
            return new String(new char[]{consonants.charAt(0), consonants.charAt(2), consonants.charAt(3)});
        }
        return consonants.substring(0, Math.min(consonants.length(), ENCODED_NAME_LENGTH));
    }
}
