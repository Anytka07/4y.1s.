package org.example;

import org.junit.Test;

import static org.junit.Assert.*;

/*
@author   Anna Melnychuk
@project   lb_1
@class  group 444A
@version  1.0.0
@since 11.09.2024 - 20:38
*/

public class ConverterTest {

    // Тести для невеликих значень

    @Test
    public void whenNumberIs_5_ThenReturn_V() {
        assertEquals("V", Converter.convertArabicToRoman(5));
    }

    @Test
    public void whenNumberIs_9_ThenReturn_IX() {
        assertEquals("IX", Converter.convertArabicToRoman(9));
    }

    @Test
    public void whenNumberIs_10_ThenReturn_X() {
        assertEquals("X", Converter.convertArabicToRoman(10));
    }

    @Test
    public void whenNumberIs_14_ThenReturn_XIV() {
        assertEquals("XIV", Converter.convertArabicToRoman(14));
    }

    @Test
    public void whenNumberIs_19_ThenReturn_XIX() {
        assertEquals("XIX", Converter.convertArabicToRoman(19));
    }

    // Тести для середніх значень
    @Test
    public void whenNumberIs_40_ThenReturn_XL() {
        assertEquals("XL", Converter.convertArabicToRoman(40));
    }

    @Test
    public void whenNumberIs_50_ThenReturn_L() {
        assertEquals("L", Converter.convertArabicToRoman(50));
    }

    @Test
    public void whenNumberIs_90_ThenReturn_XC() {
        assertEquals("XC", Converter.convertArabicToRoman(90));
    }

    @Test
    public void whenNumberIs_100_ThenReturn_C() {
        assertEquals("C", Converter.convertArabicToRoman(100));
    }

    @Test
    public void whenNumberIs_400_ThenReturn_CD() {
        assertEquals("CD", Converter.convertArabicToRoman(400));
    }

    @Test
    public void whenNumberIs_500_ThenReturn_D() {
        assertEquals("D", Converter.convertArabicToRoman(500));
    }

    @Test
    public void whenNumberIs_900_ThenReturn_CM() {
        assertEquals("CM", Converter.convertArabicToRoman(900));
    }

    @Test
    public void whenNumberIs_1000_ThenReturn_M() {
        assertEquals("M", Converter.convertArabicToRoman(1000));
    }

    // Тести на складніші комбінації
    @Test
    public void whenNumberIs_399_ThenReturn_CCCXCIX() {
        assertEquals("CCCXCIX", Converter.convertArabicToRoman(399));
    }

    @Test
    public void whenNumberIs_444_ThenReturn_CDXLIV() {
        assertEquals("CDXLIV", Converter.convertArabicToRoman(444));
    }

    @Test
    public void whenNumberIs_666_ThenReturn_DCLXVI() {
        assertEquals("DCLXVI", Converter.convertArabicToRoman(666));
    }

    @Test
    public void whenNumberIs_789_ThenReturn_DCCLXXXIX() {
        assertEquals("DCCLXXXIX", Converter.convertArabicToRoman(789));
    }

    @Test
    public void whenNumberIs_1010_ThenReturn_MX() {
        assertEquals("MX", Converter.convertArabicToRoman(1010));
    }

    @Test
    public void whenNumberIs_2022_ThenReturn_MMXXII() {
        assertEquals("MMXXII", Converter.convertArabicToRoman(2022));
    }

    @Test
    public void whenNumberIs_2421_ThenReturn_MMCDXXI() {
        assertEquals("MMCDXXI", Converter.convertArabicToRoman(2421));
    }

    @Test
    public void whenNumberIs_3001_ThenReturn_MMMI() {
        assertEquals("MMMI", Converter.convertArabicToRoman(3001));
    }

    @Test
    public void whenNumberIs_3210_ThenReturn_MMMCCX() {
        assertEquals("MMMCCX", Converter.convertArabicToRoman(3210));
    }

    @Test
    public void whenNumberIs_3500_ThenReturn_MMMD() {
        assertEquals("MMMD", Converter.convertArabicToRoman(3500));
    }

    @Test
    public void whenNumberIs_3888_ThenReturn_MMMDCCCLXXXVIII() {
        assertEquals("MMMDCCCLXXXVIII", Converter.convertArabicToRoman(3888));
    }

    @Test
    public void whenNumberIs_1987_ThenReturn_MCMLXXXVII() {
        assertEquals("MCMLXXXVII", Converter.convertArabicToRoman(1987));
    }

    @Test
    public void whenNumberIs_3999_ThenReturn_MMMCMXCIX() {
        assertEquals("MMMCMXCIX", Converter.convertArabicToRoman(3999));
    }

    @Test
    public void whenNumberIs_158_ThenReturn_CLVIII() {
        assertEquals("CLVIII", Converter.convertArabicToRoman(158));
    }

    @Test
    public void whenNumberIs_264_ThenReturn_CCXLIV() {
        assertEquals("CCLXIV", Converter.convertArabicToRoman(264));
    }

    @Test
    public void whenNumberIs_318_ThenReturn_CCXVIII() {
        assertEquals("CCCXVIII", Converter.convertArabicToRoman(318));
    }

    @Test
    public void whenNumberIs_411_ThenReturn_CDXI() {
        assertEquals("CDXI", Converter.convertArabicToRoman(411));
    }

    @Test
    public void whenNumberIs_629_ThenReturn_DCXXIX() {
        assertEquals("DCXXIX", Converter.convertArabicToRoman(629));
    }

    @Test
    public void whenNumberIs_738_ThenReturn_DCCXXXVIII() {
        assertEquals("DCCXXXVIII", Converter.convertArabicToRoman(738));
    }

    @Test
    public void whenNumberIs_895_ThenReturn_DCCCXCV() {
        assertEquals("DCCCXCV", Converter.convertArabicToRoman(895));
    }

    @Test
    public void whenNumberIs_1023_ThenReturn_MXXIII() {
        assertEquals("MXXIII", Converter.convertArabicToRoman(1023));
    }

    @Test
    public void whenNumberIs_1457_ThenReturn_MCDLVII() {
        assertEquals("MCDLVII", Converter.convertArabicToRoman(1457));
    }

    @Test
    public void whenNumberIs_2999_ThenReturn_MMCMXCIX() {
        assertEquals("MMCMXCIX", Converter.convertArabicToRoman(2999));
    }

    @Test
    public void whenNumberIs_3648_ThenReturn_MMMDCXLVIII() {
        assertEquals("MMMDCXLVIII", Converter.convertArabicToRoman(3648));
    }

    @Test
    public void whenNumberIs_1776_ThenReturn_MDCCLXXVI() {
        assertEquals("MDCCLXXVI", Converter.convertArabicToRoman(1776));
    }

    @Test
    public void whenNumberIs_2444_ThenReturn_MMCDXLIV() {
        assertEquals("MMCDXLIV", Converter.convertArabicToRoman(2444));
    }

    @Test
    public void whenNumberIs_3147_ThenReturn_MMMCXLVII() {
        assertEquals("MMMCXLVII", Converter.convertArabicToRoman(3147));
    }

    @Test
    public void whenNumberIs_2214_ThenReturn_MMCCXIV() {
        assertEquals("MMCCXIV", Converter.convertArabicToRoman(2214));
    }

    @Test
    public void whenNumberIs_2781_ThenReturn_MMDCCLXXXI() {
        assertEquals("MMDCCLXXXI", Converter.convertArabicToRoman(2781));
    }

    @Test
    public void whenNumberIs_3412_ThenReturn_MMMCDXII() {
        assertEquals("MMMCDXII", Converter.convertArabicToRoman(3412));
    }

    @Test
    public void whenNumberIs_3933_ThenReturn_MMMCMXXXIII() {
        assertEquals("MMMCMXXXIII", Converter.convertArabicToRoman(3933));
    }

    // Тести на крайні випадки
    @Test
    public void whenNumberIs_0_ThenThrowException() {
        assertThrows(IllegalArgumentException.class, () -> Converter.convertArabicToRoman(0));
    }

    @Test
    public void whenNumberIs_Negative_ThenThrowException() {
        assertThrows(IllegalArgumentException.class, () -> Converter.convertArabicToRoman(-5));
    }
}