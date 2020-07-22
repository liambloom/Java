package liam.chapter6;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.PrintStream;

// Set working directory to THIS DIR, not, the project dir (which is the intellij default)
// C:\Users\liamr\Documents\Github\Java\src\liam\chapter6

public class Exercises {
    public static final int lineLength = 60; // exercise 11/12

    public static void main (String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File("exercises.dat"));
        String txt = "";
        while (s.hasNextLine()) {
            String line = s.nextLine();
            if (line.startsWith("#")) continue;
            if (line.startsWith("\\#")) line = line.substring(1);
            txt += line + "\n";
        }
        //System.out.println(txt);
        exercise19(new Scanner(txt), System.out);

        //exercise9(new Scanner());
        //exercise11(new File("WordWrap.txt"));
        //exercise13(new Scanner(new File("Exercises.java")));
    }
    public static void exercise1 (Scanner s) { // Reads sum of alternating boy's and girl's names followed by integers
        int boy = 0;
        boolean even = true;
        int boySum = 0;
        int girlSum = 0;
        while (s.hasNext()) {
            s.next();
            if (even = !even) girlSum += s.nextInt();
            else {
                boySum += s.nextInt();
                boy++;
            }
        }
        System.out.printf("%d boys, %d girls%nDifference between boys' and girls' sums: %d%n", boy, boy - (even ? 0 : 1), Math.max(boySum, girlSum) - Math.min(boySum, girlSum));
    }
    public static void exercise2 (Scanner s) { // Reports various stats from list of #s
        int length = 0;
        int sum = 0;
        int even = 0;
        while (s.hasNextInt()) {
            int next = s.nextInt();
            length++;
            sum += next;
            if (next % 2 == 0) even++;
        }
        System.out.printf("%d numbers, sum = %d%n%d evens (%.2f%%)%n", length, sum, even, (double) even / length * 100);
    }
    public static boolean exercise3 (Scanner s) { // Prints if the sum ever reaches a negative
        int sum = 0;
        for (int i = 0; s.hasNextInt(); i++) {
            sum += s.nextInt();
            if (sum < 0) {
                System.out.printf("sum of %d after %d steps%n", sum, i);
                return true;
            }
        }
        System.out.println("no negative sum");
        return false;
    }
    public static void exercise4 (Scanner s) { // Value of coins
        int pennies = 0;
        int nickels = 0;
        int dimes = 0;
        int quarters = 0;
        while (s.hasNextInt()) {
            int num = s.nextInt();
            switch (s.next().toLowerCase()) {
                case "pennies":
                    pennies += num;
                    break;
                case "nickels":
                    nickels += num;
                    break;
                case "dimes":
                    dimes += num;
                    break;
                case "quarters":
                    quarters += num;
                    break;
            }
        }
        System.out.printf("Total money: $%.2f%n", (pennies + nickels * 5 + dimes * 10 + quarters * 25) / 100.0);
    }
    public static void exercise5 (Scanner s) { // removes spaces and prints
        while (s.hasNext()) System.out.print(s.next() + (s.hasNext() ? " " : ""));
        System.out.println();
    }
    public static String exercise6 (Scanner s) { // Returns file as string
        String str = "";
        while (s.hasNextLine()) str += s.nextLine() + "\n";
        return str;
    }
    public static void exercise7 (Scanner s) { // Prints file alternating lines
        ArrayList<String> lines = new ArrayList<>();
        while (s.hasNextLine()) lines.add(s.nextLine());
        liam.chapter10.Exercises.exercise2(lines);
        for (String line : lines) {
            System.out.println(s);
        }
    }
    public static void exercise8 (Scanner src, PrintStream out) { // double space
        while (src.hasNextLine()) out.println(src.nextLine() + "\n");
    }
    public static void exercise9 (Scanner s) { // Prints to console with word wrap
        exercise9_10(s, System.out, 60);
    }
    public static void exercise10 (File f) throws FileNotFoundException { // Adds word wrap to file
        exercise9_10(new Scanner(exercise6(new Scanner(f))), new PrintStream(f), lineLength);
    }
    private static void exercise9_10 (Scanner src, PrintStream out, int charLimit) {
        while (src.hasNextLine()) {
            String l = src.nextLine();
            for (int i = 0; i < l.length() / charLimit + 1; i++) {
                out.println(l.substring(i * charLimit, Math.min((i + 1) * charLimit, l.length())));
            }
        }
    }
    public static void exercise11 (File f) throws FileNotFoundException { // exercise10 but won't break in middle of word
        final Scanner src = new Scanner(exercise6(new Scanner(f)));
        final PrintStream out = new PrintStream(f);
        while (src.hasNextLine()) {
            final String l = src.nextLine();
            String line = "";
            String word = "";
            for (int i = 0; i < l.length() - 1; i++) {
                final char c = l.charAt(i);
                System.out.printf("c = %c, line = %s, word = %s%n", c, line, word);
                if (i != 0 && i % lineLength == 0) {
                    out.println(line);
                    line = "";
                    if (word.isBlank()) word = "";
                }
                if (!word.isEmpty() && Character.isWhitespace(c) != word.isBlank()) {
                    line += word;
                    word = "";
                }
                word += c;
                if (line.isBlank()) line = "";
            }
        }
    }
    public static void exercise12 (Scanner s) { // removes tags from html
        ArrayList<int[]> tags = new ArrayList<>();
        boolean inString = false;
        boolean escaped = false;
        int tagStart = -1;
        String html = exercise6(s);
        for (int i = 0; i < html.length(); i++) {
            if (escaped) {
                escaped = false;
                continue;
            }
            switch (html.charAt(i)) {
                case '"':
                    if (tagStart == -1) inString = !inString;
                    break;
                case '<':
                    if (!inString && tagStart == -1) tagStart = i;
                    break;
                case '>':
                    if (!inString && tagStart != -1) {
                        tags.add(new int[]{tagStart, i});
                        tagStart = -1;
                    }
                case '\\':
                    if (inString) escaped = true;
            }
        }
        if (tagStart != -1 || escaped || inString) throw new IllegalArgumentException("Invalid HTML");

        System.out.println(removeSections(html, tags));
    }
    public static void exercise13 (Scanner s) { // Print java file without comments
        // If java allowed variable length look-behinds, this could be done in a single regular expression
        // Also, I thought you said this was the EASIEST chapter!?
        // At the time I'm doing this, I've already done up to (and including) chapter 10, and the only harder chapter was chapter 7
        // And chapter 7 was only hard because I tried to figure out how to take arrays as user input (liam.chapter7.Ask)
        ArrayList<int[]> comments = new ArrayList<>();
        boolean inString = false;
        boolean inChar = false;
        boolean escaped = false;
        Comment comment = Comment.none;
        String java = exercise6(s);
        /*int line = 1;
        int column = 1;*/
        for (int i = 0; i < java.length(); i++) {
            //System.out.printf("i = %d, c = %s, comment.start = %d, comment = %s, escaped = %b, inString = %b, inChar = %b @%d:%d%n", i, (java.charAt(i) + "").replace("\n", "\\n") , comment.start, comment.toString(), escaped, inString, inChar, line, column);
            if (escaped) {
                escaped = false;
                //System.out.println("escaped");
                continue;
            }
            switch (java.charAt(i)) {
                case '"':
                    if (comment == Comment.none && !inChar) inString = !inString;
                    break;
                case '\'':
                    if (comment == Comment.none && !inString) inChar = !inChar;
                    break;
                case '\\':
                    if (inString || inChar) escaped = true;
                    break;
                case '/':
                    if (!inString && !inChar && comment == Comment.none) {
                        final char next = java.charAt(i + 1);
                        if (next == '/' || next == '*') {
                            comment = next == '/' ? Comment.singleLine : Comment.multiLine;
                            comment.start = i;
                            escaped = true;
                        }
                    }
                    break;
                case '\n':
                    if (comment == Comment.singleLine) {
                        comments.add(new int[]{comment.start, i - 1});
                        comment = Comment.none;
                    }
                    break;
                case '*':
                    if (comment == Comment.multiLine && java.charAt(i + 1) == '/') {
                        comments.add(new int[]{comment.start, i + 1});
                        comment = Comment.none;
                        escaped = true;
                    }
                    break;
            }
            /*if (java.charAt(i) == '\n') {
                line++;
                column = 1;
            }
            else column++;*/
        }
        /*ArrayList<String> strs = new ArrayList<>();
        comments.forEach(e -> strs.add(Arrays.toString(e)));
        System.out.println(strs.toString());*/
        System.out.println(removeSections(java, comments));
        //System.out.println(exercise6(s).replaceAll("(?<=^.*)(?:\\/\\/.*$|\\/\\*[^]*?\\*\\/)|\".*?(?<=(?<!\\\\)(?:\\\\{2})*)\"", ""));
        //"//.*(?=\\n)|/\\*[.\\n]*?\\*/"
        //MatchResult[] comments = s.findAll("//.*\\n|/\\*[.\\n]*?\\*/").toArray(MatchResult[]::new);
        /*for (MatchResult comment : comments) {

        }*/
    }
    private static String removeSections (String s, ArrayList<int[]> indexes) {
        for (int i = indexes.size() - 1; i >= 0; i--) s = s.substring(0, indexes.get(i)[0]) + s.substring(indexes.get(i)[1] + 1);
        return s;
    }
    public static void exercise14 (Scanner s) { // Prints # of times words appear consecutively on a line if it is >1
        while (s.hasNextLine()) {
            Scanner l = new Scanner(s.nextLine());
            int repititions = 1;
            String prevWord = "";
            while (l.hasNext()) {
                String word = l.next();
                if (word.equals(prevWord)) repititions++;
                else {
                    if (repititions > 1) System.out.printf("%s*%d ", prevWord, repititions);
                    prevWord = word;
                    repititions = 1;
                }
            }
            if (repititions > 1) System.out.printf("%s*%d ", prevWord, repititions);
            System.out.println();
        }
    }
    public static void exercise15 (Scanner s) { // Determines winner in series of coin flips ("H" or "T")
        while (s.hasNextLine()) {
            Scanner l = new Scanner(s.nextLine());
            int heads = 0;
            int total = 0;
            while (l.hasNextLine()) {
                total++;
                if (l.next().toLowerCase().equals("h")) heads++;
            }
            final double percent = 100.0 * heads / total;
            System.out.printf("%d heads (%.1f%%)%n", heads, percent);
            if (percent > 50) System.out.println("You win!");
        }
    }
    public static void exercise16 (Scanner s) { // Most common string on each line
        while (s.hasNextLine()) {
            Scanner l = new Scanner(s.nextLine());
            if (!l.hasNext()) {
                System.out.println();
                continue;
            }
            ArrayList<NameCounter> a = new ArrayList<>(); // The best way to do this would probably be to use a Map, but I didn't know those existed when I was doing it
            word: while (l.hasNext()) {
                final String word = l.next();
                for (NameCounter counter : a) {
                    if (counter.name.equals(word)) {
                        counter.count++;
                        continue word;
                    }
                }
                a.add(new NameCounter(word));
            }
            NameCounter max = a.get(0);
            for (NameCounter counter : a) {
                if (counter.count > max.count) max = counter;
            }
            System.out.println("Most common: " + max.name);
        }
    }
    public static void exercise17 (Scanner s) { // Longest line, # of tokens per line, longest token each line
        String longestLine = "";
        for (int i = 1; s.hasNextLine(); i++) {
            String line = s.nextLine();
            if (line.length() > longestLine.length()) longestLine = line;
            Scanner l = new Scanner(line);
            int longestToken = 0;
            int j;
            for (j = 0; l.hasNext(); j++) {
                int token = l.next().length();
                if (token > longestToken) longestToken = token;
            }
            System.out.printf("Line %d has %d tokens (longest = %d)%n", i, j, longestToken);
        }
        System.out.println("Longest line: " + longestLine);
    }
    public static void exercise18 (Scanner s) {
        while (s.hasNextLine()) {
            System.out.print(s.nextLine() + ": ");
            String line = s.nextLine();
            int plus = 0;
            int i;
            for (i = 0; i < line.length(); i++) {
                if (line.charAt(i) == '+') plus++;
            }
            System.out.printf("%.1f%% plus%n", 100.0 * plus / i);
        }
    }
    public static void exercise19 (Scanner src, PrintStream out) {
        while (src.hasNextLine()) {
            Scanner l = new Scanner(src.nextLine());
            while (l.hasNext()) {
                String token = l.next()
                    .replace('o', '0')
                    .replace('l', '1')
                    .replace('e', '3')
                    .replace('a', '4')
                    .replace('t', '7');
                if (token.endsWith("s")) token = token.substring(0, token.length() - 1) + "Z";
                out.print("(" + token + ") ");
            }
            out.println();
        }
    }
}

class NameCounter {
    int count = 1;
    final String name;
    NameCounter (String name) {
        this.name = name;
    }
}
enum Comment {
    none,
    singleLine,
    multiLine;

    public int start;
}