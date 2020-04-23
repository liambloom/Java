package liam.chapter7;

import java.util.regex.Pattern;

public class Ask extends liam.chapter4.Ask {
    protected static String[] getArrayString (String prompt) {
        prompt(prompt);
        String arrayString = "";
        int depth = 0;
        do {
            final String line = console.nextLine();
            arrayString += line;

            for (int i = 0; i < line.length(); i++) {
                final char c = line.charAt(i);
                if (c == '[') depth++;
                else if (c == ']') depth--;
                if (depth < 0) {
                    System.out.println("Invalid array");
                    return getArrayString(prompt);
                }
                if (depth > 1) {
                    System.out.println("Multi-dimensional arrays are not supported");
                    return getArrayString(prompt);
                }
            }
        }
        while (depth != 0);
        return arrayString.replaceFirst("^\\s*\\[", "").replaceFirst("\\]\\s*$", "").split(",");
    }
    public static String[] forStringArray (String prompt) {
        String[] original = getArrayString(prompt);
        String[] strs = new String[original.length];
        for (int i = 0; i < strs.length; i++) {
            final String str = removeLeadingTrailingWhitespace(strs[i]);
            if (Pattern.compile("^\".*?(?<!\\\\)\"$").matcher(str).matches()) strs[i] = str.substring(1, str.length() - 1);
            else {
                System.out.printf("Error: %s is not a valid string. %n", str);
                return forStringArray(prompt);
            }
        }
        return strs;
    }
    public static String[] forStringArray () {
        return forStringArray(defaultPrompt("Array"));
    }
    public static int[] forIntArray (String prompt) {
        String[] strs = getArrayString(prompt);
        int[] ints = new int[strs.length];
        for (int i = 0; i < strs.length; i++) {
            final String str = removeLeadingTrailingWhitespace(strs[i]);
            try {
                ints[i] = Integer.parseInt(str);
            }
            catch (Exception e) {
                System.out.printf("Error: %s is not a valid integer. %n", str);
                return forIntArray(prompt);
            }
        }
        return ints;
    }
    public static int[] forIntArray () {
        return forIntArray(defaultPrompt("Array of Integers"));
    }
    public static double[] forDoubleArray (String prompt) {
        String[] strs = getArrayString(prompt);
        double[] dbls = new double[strs.length];
        for (int i = 0; i < strs.length; i++) {
            final String str = removeLeadingTrailingWhitespace(strs[i]);
            try {
                dbls[i] = Double.parseDouble(str);
            }
            catch (Exception e) {
                System.out.printf("Error: %s is not a valid number. %n", str);
                return forDoubleArray(prompt);
            }
        }
        return dbls;
    }
    public static double[] forDoubleArray () {
        return forDoubleArray(defaultPrompt("Array of Integers"));
    }
    public static boolean[] forBooleanArray (String prompt) {
        String[] strs = getArrayString(prompt);
        boolean[] bools = new boolean[strs.length];
        for (int i = 0; i < strs.length; i++) {
            final String str = removeLeadingTrailingWhitespace(strs[i]);
            if (str.equals("true")) bools[i] = true;
            else if (str.equals("false")) bools[i] = false;
            else {
                System.out.printf("Error: %s is not a valid boolean value. %n", str);
                return forBooleanArray(prompt);
            }
        }
        return bools;
    }
    public static boolean[] forBooleanArray () {
        return forBooleanArray(defaultPrompt("Array of Integers"));
    }
    public static char[] forCharArray (String prompt) {
        String[] strs = getArrayString(prompt);
        char[] chars = new char[strs.length];
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].length() > 1) {
                System.out.printf("Error: %s is not a valid character. %n", strs[i]);
                return forCharArray(prompt);
            }
            else chars[i] = strs[i].charAt(0);
        }
        return chars;
    }
    public static char[] forCharArray () {
        return forCharArray(defaultPrompt("Array of Integers"));
    }
    protected static String removeLeadingTrailingWhitespace (String str) {
        return str.replaceAll("^\\s*|\\s*$", "");
    }
}