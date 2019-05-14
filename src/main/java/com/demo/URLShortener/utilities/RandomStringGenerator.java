package com.demo.URLShortener.utilities;

import java.security.SecureRandom;
import java.util.Random;

public class RandomStringGenerator {

    private static final Random RANDOM = new SecureRandom();
    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public static String generateRandomString(int length) {
        StringBuilder password = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            password.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }

        return password.toString();
    }
}
