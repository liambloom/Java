package liam.chapter7;

import java.util.Arrays;
import java.util.regex.Pattern;

public class Ask extends liam.chapter6.Ask {
    public static void main (String[] args) {
        while (true) {
            Object[] x = forArray(Integer.class, 1);
            for (Object e : x) System.out.println(e.getClass().getSimpleName());
            Integer y = (Integer) x[0];
            System.out.println(y);
        }
    }

    public static Defaults defaults = new Defaults();

    protected static class Defaults extends Object {
        public String prompt = null;
        public Class type = Object.class;
        public String prompt (String fallback) {
            if (prompt != null && Ask.defaultPrompt != null) throw new IllegalStateException("You cannot use the new and deprecated default apis simultaneously");
            return prompt == null ? Ask.defaultPrompt == null ? fallback : Ask.defaultPrompt : prompt;
        }
    }

    @Deprecated public static String defaultPrompt = null;
    @Deprecated protected static String defaultPrompt (String fallback) {
        return defaults.prompt(fallback);
    }

    protected static Object[] getArrayString(String prompt) {
        prompt(prompt);
        String arrayString = "";
        Object[] parsedArray = null;
        while (parsedArray == null) {
            try {
                parsedArray = Parser.parseArray(arrayString += console.nextLine() + '\n');
            }
            catch (Exception e) {
                if (e != Parser.ARRAY_UNCLOSED_EXCEPTION) {
                    System.out.println(e.toString());
                    return getArrayString(prompt);
                }
            }
        }
        System.out.println(arrayString);
        System.out.println(Arrays.deepToString(parsedArray));
        return parsedArray;
    }

    private static Object[] forArray (final String prompt, final Class type) {
        if (type.isPrimitive()) throw new IllegalArgumentException("Ask cannot parse variable depth primitive arrays");
        if (type != Integer.class && type != Double.class && type != Character.class && type != Boolean.class && type != String.class && type != Object.class) throw new IllegalArgumentException("Recieved type " + type.getSimpleName() + ", expected one of Integer, Double, Character, Boolean, String");

        try {
            return Parser.parseArrayType(getArrayString(prompt), type);
        }
        catch (Exception e) {
            System.out.println(e.toString()/* + " " + Arrays.toString(e.getStackTrace())*/);
            return forArray(prompt, type);
        }
    }

    public static Object[] forArray (final String prompt) {
        return forArray(prompt, defaults.type);
    }
    public static Object[] forArray () {
        return forArray(defaults.prompt("Array"), defaults.type);
    }
    public static Object[] forArray (final String prompt, final Class type, final int depth) {
        if (depth < 1) throw new IllegalArgumentException("An array cannot have a depth of less than 1");
        if (depth == 1 && type.isPrimitive()) throw new IllegalArgumentException("For a 1-dimensional primitive array, use one of the primitive array methods");
        final Class wrapper;
        if (type.isPrimitive()) {
            if (depth == 1) throw new IllegalArgumentException("One-dimensional primitive arrays must be parsed by their type specific methods");
            if (type == int.class) wrapper = Integer.class;
            else if (type == double.class) wrapper = Double.class;
            else if (type == char.class) wrapper = Character.class;
            else if (type == boolean.class) wrapper = Boolean.class;
            else throw new IllegalArgumentException("Received type " + type.getSimpleName() + ", expected one of int, double, char, boolean, Integer, Double, Character, Boolean, String");
        }
        else wrapper = type;

        Object[] arr = forArray(prompt, wrapper);
        if (type.isPrimitive()) Parser.toPrimitive(arr);
        if (Parser.hasDepth(arr, depth)) return arr;
        else {
            System.out.println("Please enter a valid " + getArrayTypeString(type, depth));
            return forArray(prompt, type, depth);
        }

    }
    public static Object[] forArray (final Class type, final int depth) {
        return forArray(defaults.prompt(getArrayTypeString(type, depth)), type, depth);
    }
    public static Object[] forArray (final String prompt, final int depth) {
        return forArray(prompt, defaults.type, depth);
    }
    public static Object[] forArray (final int depth) {
        return forArray(defaults.type, depth);
    }

    public static int[] forPrimitiveIntArray (String prompt) {
        return Parser.toPrimitive((Integer[]) forArray(prompt, Integer.class, 1));
    }
    public static int[] forPrimitiveIntArray () {
        return forPrimitiveIntArray(defaults.prompt("int[]"));
    }
    public static double[] forPrimitiveDoubleArray (String prompt) {
        return Parser.toPrimitive((Double[]) forArray(prompt, Double.class, 1));
    }
    public static double[] forPrimitiveDoubleArray () {
        return forPrimitiveDoubleArray(defaults.prompt("double[]"));
    }
    public static char[] forPrimitiveCharArray (String prompt) {
        return Parser.toPrimitive((Character[]) forArray(prompt, Character.class, 1));
    }
    public static char[] forPrimitiveCharArray () {
        return forPrimitiveCharArray(defaults.prompt("char[]"));
    }
    public static boolean[] forPrimitiveBoolArray (String prompt) {
        return Parser.toPrimitive((Boolean[]) forArray(prompt, Boolean.class, 1));
    }
    public static boolean[] forPrimitiveBoolArray () {
        return forPrimitiveBoolArray(defaults.prompt("boolean[]"));
    }

    private static String getArrayTypeString (Class type, int depth) {
        return type.getSimpleName() + liam.chapter4.Exercises.exercise2("[]", depth);
    }
}

class Parser {
    public static final IllegalArgumentException ARRAY_UNCLOSED_EXCEPTION = new IllegalArgumentException("The array was never closed");

    protected static final Pattern EMPTY_ARRAY_REGEX = Pattern.compile("^\\[\\s*\\]$");
    protected static final Pattern INT_REGEX = Pattern.compile("-?\\d+");
    protected static final Pattern DOUBLE_REGEX = Pattern.compile("-?(?:\\d+\\.\\d*|\\.\\d+)");

    public static Object parseType (String str, Class type) {
        str = removeLeadingTrailingWhitespace(str);
        if (type == Integer.class) {
            try {
                return Integer.parseInt(str);
            }
            catch (Exception e) {
                throw new NumberFormatException(str + " is not a valid integer");
            }
        }
        else if (type == Double.class) {
            try {
                return Double.parseDouble(str);
            }
            catch (Exception e) {
                throw new NumberFormatException(str + " is not a valid number");
            }
        }
        else if (type == Boolean.class) {
            if (str.equals("true")) return true;
            else if (str.equals("false")) return false;
            else throw new IllegalArgumentException(str + " is not a valid boolean value.");
        }
        else if (type == Character.class) {
            if (str.length() < 2) throw new IllegalArgumentException(str + " is not a valid character.");
            final boolean esc = str.charAt(1) == '\\';
            if (str.startsWith("'") && str.endsWith("'") && str.length() == (esc ? 4 : 3)) {
                final char c = str.charAt(str.length() - 2);
                return esc ? Parser.escapeCode(c) : c;
            }
            else throw new IllegalArgumentException(str + " is not a valid character.");
        }
        else if (type == String.class) {
            String newStr = str;
            final IllegalArgumentException invalidString = new IllegalArgumentException(str + " is not a valid string.");
            if (!newStr.startsWith("\"") || !newStr.endsWith("\"")) throw invalidString;
            newStr = newStr.substring(1, newStr.length() - 1) + ' ';
            boolean escaped = false;
            for (int i = 0; i < newStr.length(); i++) {
                if (escaped) {
                    newStr = newStr.substring(0, i - 1) + Parser.escapeCode(newStr.charAt(i)) + newStr.substring(i + 1);
                    escaped = false;
                    i--;
                    continue;
                }
                switch (newStr.charAt(i)) {
                    case '"':
                    case '\n':
                        throw invalidString;
                    case '\\':
                        escaped = true;
                }
            }
            return newStr.substring(0, newStr.length() - 1);
        }
        else if (type == Object.class) {
            if (str.startsWith("\"") && str.endsWith("\"")) return parseType(str, String.class);
            else if (str.startsWith("'") && str.endsWith("'")) return parseType(str, Character.class);
            else if (DOUBLE_REGEX.matcher(str).matches()) return parseType(str, Double.class);
            else if (INT_REGEX.matcher(str).matches()) return parseType(str, Integer.class);
            else throw new IllegalArgumentException(str + " is not a valid anything");
        }
        else throw new IllegalArgumentException("Unsupported type " + type.getSimpleName());
    }
    public static Object[] parseArrayType(Object[] unparsed, Class type) {
        Object[] arr = new Object[unparsed.length];
        System.out.println(Arrays.deepToString(unparsed));

        for (int i = 0; i < unparsed.length; i++) {
            System.out.println(unparsed[i]);
            if (unparsed[i].getClass().isArray()) arr[i] = parseArrayType((Object[]) unparsed[i], type);
            else if (unparsed[i] instanceof String) arr[i] = parseType((String) unparsed[i], type);
            else throw new IllegalArgumentException("This error should never be thrown");
        }

        return arr;
    }
    public static Object[] parseArray (String str) {
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
            if (currentDepth == 0 && i != str.length() - 1) throw new IllegalArgumentException("The argument str must be a valid array");
        }
        if (currentDepth > 0) throw ARRAY_UNCLOSED_EXCEPTION;
        commaIndexes[comma] = str.length();
        commaIndexes = Arrays.copyOfRange(commaIndexes, 0, comma + 1);
        String[] elements = new String[comma];
        for (int i = 0; i < commaIndexes.length - 1; i++) elements[i] = removeLeadingTrailingWhitespace(str.substring(commaIndexes[i] + 1, commaIndexes[i + 1]));
        elements[0] = elements[0].replaceFirst("^\\[", "");
        elements[elements.length - 1] = elements[elements.length - 1].replaceFirst("\\]$", "");
        System.out.println(Arrays.toString(elements));
        Object[] deep = Arrays.copyOf(elements, elements.length);
        for (int i = 0; i < elements.length; i++) {
            final boolean startsWithBracket = elements[i].startsWith("[");
            final boolean endsWithBracket = elements[i].endsWith("]");
            if (startsWithBracket || endsWithBracket) {
                if (startsWithBracket && endsWithBracket) deep[i] = parseArray(elements[i]);
                else throw new Error (elements[i] + " is not a valid anything");
            }
        }
        System.out.println(Arrays.deepToString(deep));
        return deep;
    }

    public static int[] toPrimitive (Integer[] arr) {
        int[] primitiveArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) primitiveArr[i] = arr[i];
        return primitiveArr;
    }
    public static double[] toPrimitive (Double[] arr) {
        double[] primitiveArr = new double[arr.length];
        for (int i = 0; i < arr.length; i++) primitiveArr[i] = arr[i];
        return primitiveArr;
    }
    public static char[] toPrimitive (Character[] arr) {
        char[] primitiveArr = new char[arr.length];
        for (int i = 0; i < arr.length; i++) primitiveArr[i] = arr[i];
        return primitiveArr;
    }
    public static boolean[] toPrimitive (Boolean[] arr) {
        boolean[] primitiveArr = new boolean[arr.length];
        for (int i = 0; i < arr.length; i++) primitiveArr[i] = arr[i];
        return primitiveArr;
    }
    public static void toPrimitive (Object[] arr) {
        for (int i = 0; i < arr.length; i++) {
            Class c = arr[i].getClass();
            if (c == Integer.class.arrayType()) arr[i] = toPrimitive((Integer[]) arr[i]);
            else if (c == Double.class.arrayType()) arr[i] = toPrimitive((Double[]) arr[i]);
            else if (c == Character.class.arrayType()) arr[i] = toPrimitive((Character[]) arr[i]);
            else if (c == Boolean.class.arrayType()) arr[i] = toPrimitive((Boolean[]) arr[i]);
            else if (c.isArray()) toPrimitive((Object[]) arr[i]);
            else throw new IllegalArgumentException("Type " + arr[i].getClass().getSimpleName() + " cannot be converted to primitive[]");
        }
    }

    public static boolean hasDepth (Object[] arr, int depth) {
        for (Object o : arr) {
            if (o.getClass().isArray() || depth != 1) {
                if (o.getClass().isArray() && depth != 1) {
                    if (!hasDepth((Object[]) o, depth - 1)) return false;
                }
                else return false;
            }
        }
        return true;
    }

    protected static char escapeCode (char c) {
        int index = "tbnrf'\"\\".indexOf(c);
        if (index == -1) throw new IllegalArgumentException("\"\\" + c + "\" is not a valid escape sequence");
        return "\t\b\n\r\f'\"\\".charAt(index); // I used strings instead of arrays because they have an indexOf method
    }

    protected static String removeLeadingTrailingWhitespace (String str) {
        return str.replaceAll("^\\s*|\\s*$", "");
    }
}