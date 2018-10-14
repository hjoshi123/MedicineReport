package me.blume.medicinereport.utils;

import android.util.Log;

import java.util.LinkedList;

public class Encode{

    public static String trimGender(String input){
        //TODO Chage identifiers for male and female
        if (input.equals("M")){
            return "0";
        }
        if (input.equals("F")){
            return "1";
        }
        return "0";
    }

    public static String trimDate(String input) {
        if (null == input) {
           //Do nothing
        }
        else {
            input = input.replace("/", "");
            input = input.replace("-", "");
        }
        return input;
    }

    public static String getFinalString(LinkedList<String> input) {
        String finalString = "|";
        for (String item : input) {
            finalString = finalString + item + "|";
        }
        Log.d("Final String", finalString);
        return finalString;
    }

    public static String getHyphenCat(LinkedList<String> input) {
        String hyphenString = "";
        for (String item : input) {
            hyphenString = hyphenString + item + "-";
        }
        return (hyphenString == null || hyphenString.length() == 0) ? null : (hyphenString.substring(0, hyphenString.length() - 1));

    }

    public static String getUnderCat(LinkedList<String> input) {
        String underString = "";
        for (String item : input) {
            underString = underString + item+ "_";
        }
        return (underString == null || underString.length() == 0) ? null : (underString.substring(0, underString.length() - 1));

    }

    public static String getStatus(String input) {
        if (null == input) {}
        else {
            if (input.equals("YES"))
                return "0";
            else if (input.equals("NO"))
                return "1";
            else
                return "2";
        }
        return "2";
    }

}