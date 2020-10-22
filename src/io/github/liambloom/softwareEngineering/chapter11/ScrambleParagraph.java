/**
 * ScrambleParagraph.java
 * 
 * Write a program that will scramble a given paragraph. You are to write the
 * program "ScrambleWords.java" that will take in a given paragraph, break it
 * down to sentences, then break that down into words. Then for each word,
 * scramble it and return it. Then when the sentence is completely scrambled it
 * should be returned. After all the sentences are scrambled, you should return
 * the scrambled paragraph and print it out.
 * 
 * 
 * Here is the example to use:
 * 
 * "According to a research at an English University, it does not matter in what
 * order the letters in a word are, the only important thing is that the first
 * and last letter is at the right place. The rest can be a total mess and you
 * can still read it without a problem. This is because we do not read every
 * letter by itself, but the word as-a-whole!"
 * 
 * 
 * 
 * A sample scrambledParagraph would be:
 * 
 * "Aoccdrnig to a rscheearch at an Elingsh Uinervtisy, it deos not mttaer in
 * waht oredr the ltteers in a wrod are, the olny iprmoetnt tihing is the taht
 * frist and lsat ltteer is at the rghit pclae. The rset can be a toatl mses and
 * you can sitll raed it wouthit a porbelm. Tihs is bcuseae we do not raed ervey
 * lteter by istlef, but the wrod as-a-wlohe!"
 * 
 * 
 * NOTE: Some words can not scramble, such as "a", "at", "if", "the", etc. as
 * the first and last letters MUST remain the same.
 ** 
 * 
 * Your task is to write the program "ScrambleParagraph.java" which is a Project
 * with a CLIENT and a class called "ScrambleWords". You will write the
 * following methods in the class.
 * 
 * (a) String scrambleWord(String w) This method takes in a single word and
 * scrambles it, if is scramble-able. It returns the scrambled word. Note: if
 * there is any punctuation at the end of a sentence such as a period, comma,
 * question mark, or exclamation point, then that stays at the end. If the word
 * is hyphenated, then scramble it in parts but do NOT cross over the hypen.
 * 
 * (b) String scrambleSentence(String s) This method calls srambleWord(w)
 * continuously, where w is a word in the sentence. This method should return
 * the sentence as a string when it is completely scrambled.
 * 
 * (c) String srambleParagraph(String p) This method calls scrambleSentence(s)
 * continuously, where s is a sentence in the paragraph. This method should
 * return the paragraph as a string when it is completely scrambled.
 * 
 * Have FUN!! :)
 * 
 * Note: A sentence ends with '.', '?', or '!'.
 */

package io.github.liambloom.softwareEngineering.chapter11;

import java.util.regex.Pattern;
import java.util.Random;

public class ScrambleParagraph {
    public static final Pattern scrambleableWord = Pattern.compile("[a-z]{4,}", Pattern.CASE_INSENSITIVE);
    public static final Pattern wordBorder = Pattern.compile("(?<=[a-z])(?=[^a-z])|(?<=[^a-z])(?=[a-z])", Pattern.CASE_INSENSITIVE);
    protected  static final Random r = new Random();

    public static void main(String[] args) {
        System.out.println(scrambleParagraph("According to a research at an English University, it does not matter in what  order the letters in a word are, the only important thing is that the first  and last letter is at the right place. The rest can be a total mess and you  can still read it without a problem. This is because we do not read every  letter by itself, but the word as-a-whole!"));
    } 

    public static String scrambleWord(String w) {
        if (w.length() <= 3)
            return w;
        if (w.contains("-")) {
            String s = "";
            final String[] words = w.split("-");
            s += scrambleWord(words[0]);
            for (int i = 1; i < words.length; i++)
                s += scrambleWord(words[i]) + '-';
            return s;
        }
        final boolean hasEndPunct = w.endsWith(".") || w.endsWith("!") || w.endsWith("?");
        final StringBuilder scrambled = new StringBuilder(w.length());
        scrambled.append(w.charAt(0));
        for (int i = 1; i < w.length() - (hasEndPunct ? 2 : 1); i++)
            scrambled.insert(r.nextInt(i) + 1, w.charAt(i));
        scrambled.append(w.charAt(w.length() - (hasEndPunct ? 2 : 1)));
        if (hasEndPunct)
            scrambled.append(w.charAt(w.length() - 1));
        return scrambled.toString();
    }

    public static String scrambleSentence(String s) {
        String scrambled = "";
        for (String token : wordBorder.split(s)) {
            if (scrambleableWord.matcher(token).matches())
                scrambled += scrambleWord(token);
            else
                scrambled += token;
        }
        return scrambled;
    }

    public static String scrambleParagraph(String p) {
        String scrambled = "";
        for (String sentence : p.split("(?<=[.!?])"))
            scrambled += scrambleSentence(sentence);
        return scrambled;
    }
}