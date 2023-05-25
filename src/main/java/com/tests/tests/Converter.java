package com.tests.tests;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Converter {

    private static List<String> converterHistory = new ArrayList<>();

    public static String octalToHexadecimalConverter(String octalNumber) throws NumberFormatException {
        BigInteger decimalNumber;
        String result;

        try {
            decimalNumber = new BigInteger(octalNumber, 8);
            result = decimalNumber.toString(16);
            converterHistory.add("OctToHex:" + octalNumber + ":" + result);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid octal number: " + octalNumber);
        }
        return result;
    }

    public static String hexadecimalToOctalConverter(String hexadecimalNumber) throws NumberFormatException {
        BigInteger decimalNumber;
        String result;

        try {
            decimalNumber = new BigInteger(removeHexadecimalPrefix(hexadecimalNumber), 16);
            result = decimalNumber.toString(8);
            converterHistory.add("HexToOct:" + hexadecimalNumber + ":" + result);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid hexadecimal number: " + hexadecimalNumber);
        }
        return result;
    }

    private static String removeHexadecimalPrefix(String hexadecimalNumber) {
        return hexadecimalNumber.replaceFirst("^0x", "");
    }

    public static List<String> getConverterHistoryString() {
        List<String> history = new ArrayList<>();
        for (String operation : converterHistory) {
            String[] operationFragments = operation.split(":");
            String operationDescription = "";
            if (operationFragments[0].equals("HexToOct")) {
                operationDescription = " Hexadecimal to octal: ";
            } else if (operationFragments[0].equals("OctToHex")) {
                operationDescription = " Octal to hexadecimal: ";
            }
            history.add(converterHistory.indexOf(operation)
                    + operationDescription + operationFragments[1]
                    + " to " + operationFragments[2]);
        }
        return history;
    }
}
