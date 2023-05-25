package com.tests.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.tests.tests.Converter.hexadecimalToOctalConverter;
import static com.tests.tests.Converter.octalToHexadecimalConverter;

class converterTest {

    @ParameterizedTest
    @CsvSource({
            "0, 0",
            "15, D",
            "52746757 , AbCdEf",
            "-56347500610, -1739E8188",
            "0100203100, 1010640"
    })
    void shouldConvertOctalToHexadecimal(String givenOctalNumber, String expectedHexadecimalNumber) {
        expectedHexadecimalNumber = expectedHexadecimalNumber.toLowerCase();

        String actualResult = octalToHexadecimalConverter(givenOctalNumber);

        Assertions.assertEquals(expectedHexadecimalNumber, actualResult);
    }


    @ParameterizedTest
    @CsvSource({
            "0, 0",
            "a, 12",
            "AbCdEf, 52746757",
            "-fc314, -3741424",
            "0xab89b5, 52704665"
    })
    void shouldConvertHexadecimalWithLowercaseLettersToOctal(String givenHexadecimalNumber, String expectedOctalNumber) {
        String actualResult = hexadecimalToOctalConverter(givenHexadecimalNumber);

        Assertions.assertEquals(expectedOctalNumber, actualResult);
    }

    @Nested
    class shouldConvertBigNumbers {
        @ParameterizedTest
        @CsvSource({
                "11777777777777777777767, 4FFFFFFFFFFFFFFF7",
                "-66205624561524371035513462635771, -D90B94B8D51F21DA5CCB3BF9",
                "53224421507304526254256334702337207663717457330101, 2B4A4468EC4AB2B15CDCE137D0FB3E7CBDB041"
        })
        void shouldConvertBigOctalToHexadecimal(String givenOctalNumber, String expectedHexadecimalNumber) {
            expectedHexadecimalNumber = expectedHexadecimalNumber.toLowerCase();

            String actualResult = octalToHexadecimalConverter(givenOctalNumber);

            Assertions.assertEquals(expectedHexadecimalNumber, actualResult);
        }

        @ParameterizedTest
        @CsvSource({
                "4fffffffffffffff7, 11777777777777777777767",
                "-2f9deb3b8d2b2205718cde7eb2463559, -574736547343225442012706146747726221432531",
                "23896078ae43f3d238ba128b310f25cd9fbaca8737753ccdaf, 434226017053441763644342720450546103622715477353124163356517146657"
        })
        void shouldConvertBigHexadecimalToOctal(String givenHexadecimalNumber, String expectedOctalNumber) {
            String actualResult = hexadecimalToOctalConverter(givenHexadecimalNumber);

            Assertions.assertEquals(expectedOctalNumber, actualResult);
        }
    }

    @Nested
    class shouldThrowNumberFormatException {
        @ParameterizedTest
        @ValueSource(strings = {"invalid input", "3.14", "3 000", "3 000", "3E-3", "aa"})
        void octalToHexadecimalConverterShouldThrowNumberFormatException(String givenOctalNumber) {
            Assertions.assertThrows(NumberFormatException.class, () -> octalToHexadecimalConverter(givenOctalNumber));
        }

        @ParameterizedTest
        @ValueSource(strings = {"invalid input", "3.14", "3 000", "3 000", "3E-3", "0x0xA"})
        void hexadecimalToOctalConverterShouldThrowNumberFormatException(String givenHexadecimalNumber) {
            Assertions.assertThrows(NumberFormatException.class, () -> hexadecimalToOctalConverter(givenHexadecimalNumber));
        }
    }
}
