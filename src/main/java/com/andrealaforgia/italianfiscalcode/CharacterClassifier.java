package com.andrealaforgia.italianfiscalcode;

final class CharacterClassifier {

    private static final String VOWELS = "AEIOU";

    private CharacterClassifier() {
    }

    static boolean isConsonant(char character) {
        return !isVowel(character);
    }

    static boolean isVowel(int character) {
        return VOWELS.indexOf(character) != -1;
    }
}
