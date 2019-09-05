package com.christophermasse.simplecalculator.utils;

import android.annotation.SuppressLint;

import com.christophermasse.simplecalculator.exception.ExcessiveValueException;

/** Formatting utility to convert doubles to strings for the EditText
 */

public class FormattingUtil {

    public static final int MAX_LENGTH = 15;

    @SuppressLint("DefaultLocale")
    public static String toString(double d) throws ExcessiveValueException {

        String str;

        if(d == (long) d){
            str = String.format("%d", (long) d);
        } else {
            str = String.format("%s", d);
        }

        System.out.println(str);

        //Handle too many digits
        if (d > 999999999999999d || d < -99999999999999d){
            throw new ExcessiveValueException("Return value of " + str +
                    " exceeds character limit of " + MAX_LENGTH);
        }
        return str;

    }

    public static double parseDouble(String str) throws NumberFormatException{
        return Double.parseDouble(str);
    }
}
