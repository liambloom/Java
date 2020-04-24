package liam.chapter7;

import java.util.Arrays;
import java.util.regex.Pattern;

public class Ask extends liam.chapter4.Ask {
    protected static final IllegalArgumentException ARRAY_UNCLOSED_EXCEPTION = new IllegalArgumentException("The array was never closed");

    protected static final Pattern STRING_REGEX = Pattern.compile("^\".*?(?<!\\\\)\"$");
    protected static final Pattern INT_REGEX = Pattern.compile("^-?\\d+$");
    protected static final Pattern DOUBLE_REGEX = Pattern.compile("^-?(?:\\d*\\.\\d+|\\d+\\.)$");
    protected static final Pattern EMPTY_ARRAY_REGEX = Pattern.compile("^\\[\\s*\\]$");

    protected static String getArrayString(String prompt) {
        prompt(prompt);
        String arrayString = "";
        boolean isValid = false;
        while (!isValid) {
            try {
                parse1dArray(arrayString += console.nextLine());
                isValid = true;
            }
            catch (Exception e) {
                if (e != ARRAY_UNCLOSED_EXCEPTION) {
                    System.out.println(e.toString());
                    return getArrayString(prompt);
                }
            }
        }
        return arrayString;
    }

    protected static String removeLeadingTrailingWhitespace (String str) {
        return str.replaceAll("^\\s*|\\s*$", "");
    }

    protected static String[] parse1dArray (String str) {
        str = removeLeadingTrailingWhitespace(str);
        if (EMPTY_ARRAY_REGEX.matcher(str).matches()) return new String[0];
        int[] commaIndexes = new int[str.split(",").length + 1];
        commaIndexes[0] = -1;
        int depth = 0;
        int comma = 1;
        boolean inString = false; // True when in a string or char
        boolean escaped = false;
        for (int i = 0; i < str.length(); i++) {
            if (escaped) {
                escaped = false;
                continue;
            }
            char c = str.charAt(i);
            switch (c) {
                case '[':
                    if (!inString) depth++;
                    break;
                case ']':
                    if (!inString) depth--;
                    break;
                case ',':
                    if (depth == 1 && !inString) commaIndexes[comma++] = i;
                    break;
                case '"':
                case '\'':
                    inString = !inString;
                    break;
                case '\\':
                    escaped = inString;
                    break;
                case '\n':
                    if (inString) throw new IllegalArgumentException("Unclosed string or character");
                    break;
            }
            if (depth == 0 && i != str.length() - 1) throw new IllegalArgumentException("The argument str must be a valid array");
        }
        if (depth > 0) throw ARRAY_UNCLOSED_EXCEPTION;
        commaIndexes[comma] = str.length();
        commaIndexes = Arrays.copyOfRange(commaIndexes, 0, comma + 1);
        String[] elements = new String[comma];
        for (int i = 0; i < commaIndexes.length - 1; i++) elements[i] = removeLeadingTrailingWhitespace(str.substring(commaIndexes[i] + 1, commaIndexes[i + 1]));
        elements[0] = elements[0].replaceFirst("^\\[", "");
        elements[elements.length - 1] = elements[elements.length - 1].replaceFirst("\\]$", "");
        return elements;
    }
    protected static String[][] parse2dArray (String str) {
        String[] array1d = parse1dArray(str);
        String[][] array2d = new String[array1d.length][];
        for (int i = 0; i < array1d.length; i++) array2d[i] = parse1dArray(array1d[i]);
        return array2d;
    }
    protected static String[][][] parse3dArray (String str) {
        String[] array1d = parse1dArray(str);
        String[][][] array3d = new String[array1d.length][][];
        for (int i = 0; i < array1d.length; i++) array3d[i] = parse2dArray(array1d[i]);
        return array3d;
    }
    // There is no plausible reason for me to need more than a 3d array; Even 3d isn't likely

    protected static int[] parseIntArray (String[] strs) {
        int[] ints = new int[strs.length];
        for (int i = 0; i < strs.length; i++) {
            final String str = strs[i];
            if (INT_REGEX.matcher(str).matches()) ints[i] = Integer.parseInt(str);
            else throw new NumberFormatException("Error: " + str + " is not a valid integer");
        }
        return ints;
    }
    protected static double[] parseDoubleArray (String[] strs) {
        double[] dbls = new double[strs.length];
        for (int i = 0; i < strs.length; i++) {
            final String str = strs[i];
            if (DOUBLE_REGEX.matcher(str).matches()) dbls[i] = Double.parseDouble(str);
            else throw new NumberFormatException("Error: " + str + " is not a valid number");
        }
        return dbls;
    }
    protected static boolean[] parseBooleanArray (String[] strs) {
        boolean[] bools = new boolean[strs.length];
        for (int i = 0; i < strs.length; i++) {
            final String str = strs[i];
            if (str.equals("true")) bools[i] = true;
            else if (str.equals("false")) bools[i] = false;
            else throw new IllegalArgumentException("Error: " + str + " is not a valid boolean value.");
        }
        return bools;
    }
    protected static char[] parseCharArray (String[] strs) {
        char[] chars = new char[strs.length];
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            final IllegalArgumentException illegalCharacter = new IllegalArgumentException("Error: " + str + " is not a valid character.");
            final boolean esc = str.charAt(1) == '\\';
            if (str.startsWith("'") && str.endsWith("'") && str.length() == (esc ? 4 : 3)) {
                final char c = str.charAt(str.length() - 2);
                chars[i] = esc ? escapeCode(c) : c;
            }
            else throw illegalCharacter;
        }
        return chars;
    }
    protected static String[] parseStringArray (String[] strs) {
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            if (STRING_REGEX.matcher(str).matches()) strs[i] = str.substring(1, str.length() - 1);
            else throw new IllegalArgumentException("Error: " + str + " is not a valid string.");
        }
        return strs;
    }

    protected static int[][] parse2dIntArray(String[][] strings) {
        int[][] ints = new int[strings.length][];
        for (int i = 0; i < strings.length; i++) ints[i] = parseIntArray(strings[i]);
        return ints;
    }
    protected static double[][] parse2dDoubleArray(String[][] strings) {
        double[][] dbls = new double[strings.length][];
        for (int i = 0; i < strings.length; i++) dbls[i] = parseDoubleArray(strings[i]);
        return dbls;
    }
    protected static boolean[][] parse2dBooleanArray(String[][] strings) {
        boolean[][] bools = new boolean[strings.length][];
        for (int i = 0; i < strings.length; i++) bools[i] = parseBooleanArray(strings[i]);
        return bools;
    }
    protected static char[][] parse2dCharArray(String[][] strings) {
        char[][] chars = new char[strings.length][];
        for (int i = 0; i < strings.length; i++) chars[i] = parseCharArray(strings[i]);
        return chars;
    }
    protected static String[][] parse2dStringArray(String[][] strings) {
        for (int i = 0; i < strings.length; i++) strings[i] = parseStringArray(strings[i]);
        return strings;
    }

    public static String[] forStringArray (String prompt) {
        try {
            return parseStringArray(parse1dArray(getArrayString(prompt)));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return forStringArray(prompt);
        }
    }
    public static String[] forStringArray () {
        return forStringArray(defaultPrompt("Array"));
    }
    public static String[][] forStringMatrix (String prompt) {
        try {
            return parse2dStringArray(parse2dArray(getArrayString(prompt)));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return forStringMatrix(prompt);
        }
    }
    public static String[][] forStringMatrix () {
        return forStringMatrix("Matrix of strings");
    }

    public static int[] forIntArray (String prompt) {
        try {
            return parseIntArray(parse1dArray(getArrayString(prompt)));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return forIntArray(prompt);
        }
    }
    public static int[] forIntArray () {
        return forIntArray(defaultPrompt("Array of integers"));
    }
    public static int[][] forIntMatrix (String prompt) {
        try {
            return parse2dIntArray(parse2dArray(getArrayString(prompt)));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return forIntMatrix(prompt);
        }
    }
    public static int[][] forIntMatrix () {
        return forIntMatrix(defaultPrompt("Matrix of integers"));
    }

    public static double[] forDoubleArray (String prompt) {
        try {
            return parseDoubleArray(parse1dArray(getArrayString(prompt)));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return forDoubleArray(prompt);
        }
    }
    public static double[] forDoubleArray () {
        return forDoubleArray(defaultPrompt("Array of numbers"));
    }
    public static double[][] forDoubleMatrix (String prompt) {
        try {
            return parse2dDoubleArray(parse2dArray(getArrayString(prompt)));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return forDoubleMatrix(prompt);
        }
    }
    public static double[][] forDoubleMatrix () {
        return forDoubleMatrix(defaultPrompt("Matrix of numbers"));
    }

    public static boolean[] forBooleanArray (String prompt) {
        try {
            return parseBooleanArray(parse1dArray(getArrayString(prompt)));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return forBooleanArray(prompt);
        }
    }
    public static boolean[] forBooleanArray () {
        return forBooleanArray(defaultPrompt("Array of boolean values"));
    }
    public static boolean[][] forBooleanMatrix (String prompt) {
        try {
            return parse2dBooleanArray(parse2dArray(getArrayString(prompt)));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return forBooleanMatrix(prompt);
        }
    }
    public static boolean[][] forBooleanMatrix () {
        return forBooleanMatrix(defaultPrompt("Matrix of boolean values"));
    }

    public static char[] forCharArray (String prompt) {
        try {
            return parseCharArray(parse1dArray(getArrayString(prompt)));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return forCharArray(prompt);
        }
    }
    public static char[] forCharArray () {
        return forCharArray(defaultPrompt("Array of characters"));
    }
    public static char[][] forCharMatrix (String prompt) {
        try {
            return parse2dCharArray(parse2dArray(getArrayString(prompt)));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return forCharMatrix(prompt);
        }
    }
    public static char[][] forCharMatrix () {
        return forCharMatrix(defaultPrompt("Matrix of characters"));
    }
}