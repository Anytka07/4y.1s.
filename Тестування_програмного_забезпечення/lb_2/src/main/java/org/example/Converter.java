package org.example;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Converter {

    enum RomanNumeral {
        I(1), IV(4), V(5), IX(9), X(10),
        XL(40), L(50), XC(90), C(100),
        CD(400), D(500), CM(900), M(1000);

        private int value;

        RomanNumeral(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static List<RomanNumeral> getReverseSortedValues() {
            return Arrays.stream(values())
                    .sorted(Comparator.comparing((RomanNumeral e) -> e.value).reversed())
                    .collect(Collectors.toList());
        }
    }

    public static int convertRomanToArabic(String input) {
        // Перевірка на порожній рядок або null
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Input cannot be null or empty");
        }

        // Перевірка на наявність малих літер
        if (!input.equals(input.toUpperCase())) {
            throw new IllegalArgumentException("Input contains invalid lowercase letters");
        }

        String romanNumeral = input.toUpperCase(); // Конвертуємо до верхнього регістру
        int result = 0;

        // Перевірка на некоректну кількість однакових символів поспіль
        if (romanNumeral.contains("IIII") || romanNumeral.contains("VV") || romanNumeral.contains("XXXX")
                || romanNumeral.contains("LL") || romanNumeral.contains("CCCC") || romanNumeral.contains("DD")
                || romanNumeral.contains("MMMM")) {
            throw new IllegalArgumentException("Roman numeral contains invalid repeated characters");
        }

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();
        int i = 0;

        while ((romanNumeral.length() > 0) && (i < romanNumerals.size())) {
            RomanNumeral symbol = romanNumerals.get(i);
            if (romanNumeral.startsWith(symbol.name())) {
                result += symbol.getValue();
                romanNumeral = romanNumeral.substring(symbol.name().length());
            } else {
                i++;
            }
        }

        // Перевірка на некоректне римське число, якщо після обробки залишилися символи
        if (romanNumeral.length() > 0) {
            throw new IllegalArgumentException(input + " cannot be converted to a Roman Numeral");
        }

        return result;
    }


}
