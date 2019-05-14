package com.demo.URLShortener.utilities;

import java.util.Random;

public class RandomNumberGenerator {

    public static int getRandomNumber (int min, int max) {

        if (min > max)
            throw new IllegalArgumentException("Max needs to be grater than minimum");

        Random r = new Random();
        return r.nextInt((max - min) + 1 ) + min;
    }
}
