package com.almada.clinicaapi.util;

import java.util.Random;

public class GenerateCode {

    private GenerateCode() {

    }

    public static String randomCode() {

        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

        Random random = new Random();

        int length = 10;

        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();

    }

}
