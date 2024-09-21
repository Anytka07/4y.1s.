package org.example;

import org.junit.Test;

import static org.junit.Assert.*;

/*
@author   Anna Melnychuk
@project   lb_2
@class  group 444A
@version  1.0.0
@since 21.09.2024 - 21:30
*/

public class ConverterTest {

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
    public void whenRomanIs_X_ThenReturn_10() {
        assertEquals(10, Converter.convertRomanToArabic("X"));
    }

    // Десяткові комбінації чисел
    @Test
    public void whenRomanIs_IV_ThenReturn_4() {
        assertEquals(4, Converter.convertRomanToArabic("IV"));
    }

    @Test
    public void whenRomanIs_IX_ThenReturn_9() {
        assertEquals(9, Converter.convertRomanToArabic("IX"));
    }

    @Test
    public void whenRomanIs_XL_ThenReturn_40() {
        assertEquals(40, Converter.convertRomanToArabic("XL"));
    }

    @Test
    public void whenRomanIs_L_ThenReturn_50() {
        assertEquals(50, Converter.convertRomanToArabic("L"));
    }

    @Test
    public void whenRomanIs_XC_ThenReturn_90() {
        assertEquals(90, Converter.convertRomanToArabic("XC"));
    }

    // Тести для більш складних чисел
    @Test
    public void whenRomanIs_C_ThenReturn_100() {
        assertEquals(100, Converter.convertRomanToArabic("C"));
    }

    @Test
    public void whenRomanIs_CD_ThenReturn_400() {
        assertEquals(400, Converter.convertRomanToArabic("CD"));
    }

    @Test
    public void whenRomanIs_D_ThenReturn_500() {
        assertEquals(500, Converter.convertRomanToArabic("D"));
    }

    @Test
    public void whenRomanIs_CM_ThenReturn_900() {
        assertEquals(900, Converter.convertRomanToArabic("CM"));
    }

    @Test
    public void whenRomanIs_M_ThenReturn_1000() {
        assertEquals(1000, Converter.convertRomanToArabic("M"));
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
    @Test
    public void whenRomanIs_EmptyString_ThenReturn_0() {
        //Тест перевіряє поведінку методу при порожньому рядку
        assertEquals(0, Converter.convertRomanToArabic(""));
    }

    @Test
    public void whenRomanContainsInvalidCharacters_ThenThrowException() {
        // Перевірка, що рядок з недопустимими символами викликає виключення
        assertThrows(IllegalArgumentException.class, () -> Converter.convertRomanToArabic("ABCD"));
    }
}