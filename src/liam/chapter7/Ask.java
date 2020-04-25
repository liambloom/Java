package liam.chapter7;

import liam.chapter4.Exercises;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.function.Function;

public class Ask extends liam.chapter4.Ask {
    public static void main (String[] args) {
        while (true) System.out.println(Arrays.toString(forPrimitiveIntArray()));
    }

    protected static final IllegalArgumentException ARRAY_UNCLOSED_EXCEPTION = new IllegalArgumentException("The array was never closed");

    protected static final Pattern EMPTY_ARRAY_REGEX = Pattern.compile("^\\[\\s*\\]$");

    protected static String getArrayString(String prompt, int depth) {
        prompt(prompt);
        String arrayString = "";
        boolean isValid = false;
        while (!isValid) {
            try {
                parseArray(arrayString += console.nextLine() + '\n', depth);
                isValid = true;
            }
            catch (Exception e) {
                if (e != ARRAY_UNCLOSED_EXCEPTION) {
                    System.out.println(e.toString());
                    return getArrayString(prompt, depth);
                }
            }
        }
        return arrayString;
    }

    protected static String removeLeadingTrailingWhitespace (String str) {
        return str.replaceAll("^\\s*|\\s*$", "");
    }
    protected static char escapeCode (char c) {
        int index = "tbnrf'\"\\".indexOf(c);
        if (index == -1) throw new IllegalArgumentException("\"\\" + c + "\" is not a valid escape sequence");
        return "\t\b\n\r\f'\"\\".charAt(index); // I used strings instead of arrays because they have an indexOf method
    }

    protected static Object[] parseArray (String str, int depth) {
        if (depth < 1) throw new IllegalArgumentException("An array cannot have a depth of " + depth);
        str = removeLeadingTrailingWhitespace(str);
        if (EMPTY_ARRAY_REGEX.matcher(str).matches()) return new String[0];
        int[] commaIndexes = new int[str.split(",").length + 1];
        commaIndexes[0] = -1;
        int currentDepth = 0;
        int comma = 1;
        boolean inString = false; // True when in a string or char
        boolean escaped = false;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            //System.out.printf("c=%c inString=%b escaped=%b %n", c, inString, escaped);
            if (escaped) {
                if ("tbnrf'\"\\".indexOf(c) == -1) throw new IllegalArgumentException(c + " is not a legal escape character");
                escaped = false;
                continue;
            }
            switch (c) {
                case '[':
                    if (!inString) currentDepth++;
                    break;
                case ']':
                    if (!inString) currentDepth--;
                    break;
                case ',':
                    if (currentDepth == 1 && !inString) commaIndexes[comma++] = i;
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
            if (currentDepth > depth) throw new IllegalArgumentException("The argument str must be a " + depth + "-dimensional array.");
            if (currentDepth == 0 && i != str.length() - 1) throw new IllegalArgumentException("The argument str must be a valid array");
        }
        if (currentDepth > 0) throw ARRAY_UNCLOSED_EXCEPTION;
        commaIndexes[comma] = str.length();
        commaIndexes = Arrays.copyOfRange(commaIndexes, 0, comma + 1);
        String[] elements = new String[comma];
        for (int i = 0; i < commaIndexes.length - 1; i++) elements[i] = removeLeadingTrailingWhitespace(str.substring(commaIndexes[i] + 1, commaIndexes[i + 1]));
        elements[0] = elements[0].replaceFirst("^\\[", "");
        elements[elements.length - 1] = elements[elements.length - 1].replaceFirst("\\]$", "");
        if (depth > 1) {
            Object[] deep = new Object[elements.length];
            for (int i = 0; i < elements.length; i++) {
                try {
                    deep[i] = parseArray(elements[i], depth - 1);
                }
                catch (Exception e) {
                    throw new IllegalArgumentException("The argument str must be a " + depth + "-dimensional array.");
                }
            }
            return deep;
        }
        return elements;
    }
    // There is no plausible reason for me to need more than a 3d array; Even 3d isn't likely

    protected static Object[] parseArrayToType (Object[] strs, Class type) {
        Object[] arr = new Object[strs.length];

        Function<String, Object> parser = null;
        if (type == Integer.class) parser = Integer::parseInt;
        else if (type == Double.class) parser = Double::parseDouble;
        else if (type == Boolean.class) {
            parser = str -> {
                if (str.equals("true")) return true;
                else if (str.equals("false")) return false;
                else throw new IllegalArgumentException("Error: " + str + " is not a valid boolean value.");
            };
        }
        else if (type == Character.class) {
            parser = str -> {
                if (str.length() < 2) throw new IllegalArgumentException("Error: " + str + " is not a valid character.");
                final boolean esc = str.charAt(1) == '\\';
                if (str.startsWith("'") && str.endsWith("'") && str.length() == (esc ? 4 : 3)) {
                    final char c = str.charAt(str.length() - 2);
                    return esc ? escapeCode(c) : c;
                }
                else throw new IllegalArgumentException("Error: " + str + " is not a valid character.");
            };
        }
        else if (type == String.class) {
            parser = str -> {
                final IllegalArgumentException invalidString = new IllegalArgumentException("Error: " + str + " is not a valid string.");
                if (!str.startsWith("\"") || !str.endsWith("\"")) throw invalidString;
                str = str.substring(1, str.length() - 1) + ' ';
                boolean escaped = false;
                for (int i = 0; i < str.length(); i++) {
                    if (escaped) {
                        str = str.substring(0, i - 1) + escapeCode(str.charAt(i)) + str.substring(i + 1);
                        escaped = false;
                        i--;
                        continue;
                    }
                    switch (str.charAt(i)) {
                        case '"':
                        case '\n':
                            throw invalidString;
                        case '\\':
                            escaped = true;
                    }
                }
                return str.substring(0, str.length() - 1);
            };
        }
        assert parser != null;

        for (int i = 0; i < strs.length; i++) {
            if (strs[i].getClass().isArray()) arr[i] = parseArrayToType((Object[]) strs[i], type);
            else if (strs[i] instanceof String) arr[i] = parser.apply((String) strs[i]);
            else throw new IllegalArgumentException("This error should never be thrown");
        }

        return arr;
    }

    public static Object[] forArray (String prompt, Class type, int depth) {
        if (depth < 1) throw new IllegalArgumentException("An array cannot have a depth of less than 1");

        final IllegalArgumentException typeError = new IllegalArgumentException("Recieved type " + type.getName() + ", expected one of int, double, char, boolean, Integer, Double, Character, Boolean, String");
        boolean isPrimitive = type.isPrimitive();
        if (isPrimitive) {
            if (depth == 1) throw new IllegalArgumentException("For a 1-dimentional primitive array, use one of the primitive array methods");
            else {
                if (type == int.class) type = Integer.class;
                else if (type == double.class) type = Double.class;
                else if (type == char.class) type = Character.class;
                else if (type == boolean.class) type = Boolean.class;
                else throw typeError;
            }
        }

        if (type != Integer.class && type != Double.class && type != Character.class && type != Boolean.class && type != String.class) throw typeError;

        try {
            Object[] arr = parseArrayToType(parseArray(getArrayString(prompt, depth), depth), type);
            if (hasDepth(arr, depth)) {
                System.out.println(Arrays.deepToString(arr));
                if (isPrimitive) arr = toPrimitive(arr);
                return arr;
            }
            else {
                System.out.println("Please enter a valid " + getArrayTypeString(type, depth));
                return forArray(prompt, type, depth);
            }
        }
        catch (Exception e) {
            System.out.println(e.toString() + " " + Arrays.toString(e.getStackTrace()));
            return forArray(prompt, type, depth);
        }
    }
    public static Object[] forArray (Class type, int depth) {
        return forArray(defaultPrompt(getArrayTypeString(type, depth)), type, depth);
    }
    public static Object[] forArray (String prompt, Class type) {
        return forArray(prompt, type, 1);
    }
    public static Object[] forArray (Class type) {
        return forArray(defaultPrompt(getArrayTypeString(type, 1)), type, 1);
    }

    public static int[] forPrimitiveIntArray (String prompt) {
        Object[] arr = forArray(prompt, Integer.class, 1);
        System.out.println(Arrays.toString(arr));
        System.out.println(arr[1].getClass().getName());
        return toPrimitive((Integer[]) arr);
    }
    public static int[] forPrimitiveIntArray () {
        return forPrimitiveIntArray(defaultPrompt("int[]"));
    }
    public static double[] forPrimitiveDoubleArray (String prompt) {
        return toPrimitive((Double[]) forArray(prompt, Double.class, 1));
    }
    public static double[] forPrimitiveDoubleArray () {
        return forPrimitiveDoubleArray(defaultPrompt("double[]"));
    }
    public static char[] forPrimitiveCharArray (String prompt) {
        return toPrimitive((Character[]) forArray(prompt, Character.class, 1));
    }
    public static char[] forPrimitiveCharArray () {
        return forPrimitiveCharArray(defaultPrompt("char[]"));
    }
    public static boolean[] forPrimitiveBoolArray (String prompt) {
        return toPrimitive((Boolean[]) forArray(prompt, Boolean.class, 1));
    }
    public static boolean[] forPrimitiveBoolArray () {
        return forPrimitiveBoolArray(defaultPrompt("boolean[]"));
    }

    protected static int[] toPrimitive (Integer[] arr) {
        int[] primitiveArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) primitiveArr[i] = arr[i];
        return primitiveArr;
    }
    protected static double[] toPrimitive (Double[] arr) {
        double[] primitiveArr = new double[arr.length];
        for (int i = 0; i < arr.length; i++) primitiveArr[i] = arr[i];
        return primitiveArr;
    }
    protected static char[] toPrimitive (Character[] arr) {
        char[] primitiveArr = new char[arr.length];
        for (int i = 0; i < arr.length; i++) primitiveArr[i] = arr[i];
        return primitiveArr;
    }
    protected static boolean[] toPrimitive (Boolean[] arr) {
        boolean[] primitiveArr = new boolean[arr.length];
        for (int i = 0; i < arr.length; i++) primitiveArr[i] = arr[i];
        return primitiveArr;
    }
    protected static Object[] toPrimitive (Object[] arr) {
        Object[] primitiveMatrix = new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            Class c = arr[i].getClass();
            if (c == Integer.class.arrayType()) primitiveMatrix[i] = toPrimitive((Integer[]) arr[i]);
            else if (c == Double.class.arrayType()) primitiveMatrix[i] = toPrimitive((Double[]) arr[i]);
            else if (c == Character.class.arrayType()) primitiveMatrix[i] = toPrimitive((Character[]) arr[i]);
            else if (c == Boolean.class.arrayType()) primitiveMatrix[i] = toPrimitive((Boolean[]) arr[i]);
            else {
                System.out.println(arr[i].getClass().getName());
                primitiveMatrix[i] = toPrimitive((Object[]) arr[i]);
            }
        }
        return primitiveMatrix;
    }

    protected static boolean hasDepth (Object[] arr, int target) {
        int depth = getDepth(arr);
        return depth == -Exercises.Infinity || depth == target; // Why is Exercises liam.chapter4.Exercises if i'm in liam.chapter7?
    }
    private static int getDepth (Object[] arr) {
        if (arr.length == 0) return -Exercises.Infinity;
        int[] depths = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].getClass().isArray()) depths[i] = getDepth((Object[]) arr[i]);
        }
        return $.max(depths) + 1;
    }

    private static String getArrayTypeString (Class type, int depth) {
        return type.getName() + liam.chapter4.Exercises.exercise2("[]", depth);
    }
}