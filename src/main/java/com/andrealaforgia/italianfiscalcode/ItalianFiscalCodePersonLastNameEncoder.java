package com.andrealaforgia.italianfiscalcode;

public class ItalianFiscalCodePersonLastNameEncoder extends AbstractNameEncoder {
    private static final ItalianFiscalCodePersonLastNameEncoder INSTANCE = new ItalianFiscalCodePersonLastNameEncoder();

    public static String encode(String lastName) {
        return INSTANCE.encodeName(lastName);
    }

    @Override
    String selectConsonants(String consonants) {
        return consonants.substring(0, Math.min(consonants.length(), ENCODED_NAME_LENGTH));
    }
}
