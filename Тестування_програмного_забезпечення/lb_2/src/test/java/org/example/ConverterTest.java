package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/*
@author   Anna Melnychuk
@project   lb_2
@class  group 444A
@version  1.0.0
@since 21.09.2024 - 23:08
*/

public class ConverterTest {

    /* Спочатку йдуть кілька тестів на звичайне переведення римських чисел у арабські,
     далі нижче йдуть 10 тестів для виняткових ситуацій */

    @Test
    public void whenRomanIs_III_ThenReturn_3() {
        // Перевіряємо, що римське число III перетворюється в арабське 3
        assertEquals(3, Converter.convertRomanToArabic("III"));
    }

    @Test
    public void whenRomanIs_V_ThenReturn_5() {
        assertEquals(5, Converter.convertRomanToArabic("V"));
    }

    @Test
    public void whenRomanIs_IX_ThenReturn_9() {
        assertEquals(9, Converter.convertRomanToArabic("IX"));
    }

    // Тести для більш складних чисел
    @Test
    public void whenRomanIs_C_ThenReturn_100() {
        assertEquals(100, Converter.convertRomanToArabic("C"));
    }

    @Test
    public void whenRomanIs_MCMXCIX_ThenReturn_1999() {
        assertEquals(1999, Converter.convertRomanToArabic("MCMXCIX"));
    }

    @Test
    public void whenRomanIs_MMXXIII_ThenReturn_2023() {
        assertEquals(2023, Converter.convertRomanToArabic("MMXXIII"));
    }

    @Test
    public void whenRomanIs_MMCDXLIV_ThenReturn_2444() {
        assertEquals(2444, Converter.convertRomanToArabic("MMCDXLIV"));
    }

    @Test
    public void whenRomanIs_MMMCCCXXXIII_ThenReturn_3333() {
        assertEquals(3333, Converter.convertRomanToArabic("MMMCCCXXXIII"));
    }

    @Test
    public void whenRomanIs_MMMDCCCLXXXVIII_ThenReturn_3888() {
        assertEquals(3888, Converter.convertRomanToArabic("MMMDCCCLXXXVIII"));
    }

    @Test
    public void whenRomanIs_MMMCMXCIX_ThenReturn_3999() {
        assertEquals(3999, Converter.convertRomanToArabic("MMMCMXCIX"));
    }

    // Тести на крайні випадки
    // 1. Перевірка, що рядок з недопустимими символами викликає виключення
    @Test
    public void whenRomanContainsInvalidCharacters_ThenThrowException() {
        assertThrows(IllegalArgumentException.class, () -> Converter.convertRomanToArabic("ABCD"));
    }

    //  2. Тест на порожній рядок
    @Test
    public void whenRomanIsEmptyString_ThenThrowException() {
        assertThrows(IllegalArgumentException.class, () -> Converter.convertRomanToArabic(""));
    }

    //  3. Тест на некоректну кількість однакових символів
    @Test
    public void whenRomanHasMoreThanThreeSameSymbols_ThenThrowException() {
        assertThrows(IllegalArgumentException.class, () -> Converter.convertRomanToArabic("IIII"));
    }

    //  4. Тест на неправильну послідовність символів
    @Test
    public void whenRomanHasInvalidOrder_ThenThrowException() {
        assertThrows(IllegalArgumentException.class, () -> Converter.convertRomanToArabic("VX"));
    }

    //  5. Тест на число, яке перевищує межі
    @Test
    public void whenRomanExceedsMaxLimit_ThenThrowException() {
        assertThrows(IllegalArgumentException.class, () -> Converter.convertRomanToArabic("MMMM"));
    }

    //  6. Тест на введення нуля
    @Test
    public void whenRomanHasZero_ThenThrowException() {
        assertThrows(IllegalArgumentException.class, () -> Converter.convertRomanToArabic("0"));
    }

    //  7. Тест на малі літери
    @Test
    public void whenRomanIsLowerCase_ThenThrowException() {
        assertThrows(IllegalArgumentException.class, () -> Converter.convertRomanToArabic("x"));
    }

    //  8. Тест на некоректну комбінацію римських символів
    @Test
    public void whenRomanHasInvalidCombination_ThenThrowException() {
        assertThrows(IllegalArgumentException.class, () -> Converter.convertRomanToArabic("IC"));
    }

    //  9. Тест на повторення неприпустимих комбінацій
    @Test
    public void whenRomanHasInvalidRepetitions_ThenThrowException() {
        assertThrows(IllegalArgumentException.class, () -> Converter.convertRomanToArabic("IIIIIV"));
    }

    //  10. Тест на наявність пробілів у римському числі
    @Test
    public void whenRomanHasSpaces_ThenThrowException() {
        assertThrows(IllegalArgumentException.class, () -> Converter.convertRomanToArabic("X I"));
    }
}
