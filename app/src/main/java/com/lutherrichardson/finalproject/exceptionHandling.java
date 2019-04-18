package com.lutherrichardson.finalproject;


public class exceptionHandling {


    public static boolean doubleGreaterZero(String input) {

        boolean inputOk = true;

        double num = Double.parseDouble(input);

        try {
            if (num < 0)
                throw new Exception("Exception: must be greater than 0!");
        } catch (Exception e) {
            return inputOk = false;
        }

        return inputOk;
    }

    public static boolean doubleFormat(String input) {
        boolean inputOk = true;

        try {
            Double.parseDouble(input);
        } catch (java.lang.NumberFormatException e) {
            inputOk = false;
        }
        return inputOk;
    }
}



