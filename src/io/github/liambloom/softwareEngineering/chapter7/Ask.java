package io.github.liambloom.softwareEngineering.chapter7;

import java.util.Arrays;

public class Ask extends io.github.liambloom.softwareEngineering.chapter6.Ask {
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
        @SuppressWarnings("rawtypes")
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

    private static Object[] forArray (final String prompt, final @SuppressWarnings("rawtypes") Class type) {
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
    public static Object[] forArray (final String prompt, final @SuppressWarnings("rawtypes") Class type, final int depth) {
        if (depth < 1) throw new IllegalArgumentException("An array cannot have a depth of less than 1");
        if (depth == 1 && type.isPrimitive()) throw new IllegalArgumentException("For a 1-dimensional primitive array, use one of the primitive array methods");
        @SuppressWarnings("rawtypes")
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
    public static Object[] forArray (final @SuppressWarnings("rawtypes") Class type, final int depth) {
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

    private static String getArrayTypeString (@SuppressWarnings("rawtypes") Class type, int depth) {
        return type.getSimpleName() + io.github.liambloom.softwareEngineering.chapter4.Exercises.exercise2("[]", depth);
    }
}

