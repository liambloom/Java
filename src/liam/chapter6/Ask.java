package liam.chapter6;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class Ask extends liam.chapter4.Ask {
    public static File forFile (String prompt, boolean preexisting) {
        File file = new File(forToken(prompt));
        if (!file.canRead() && preexisting) {
            System.out.println("File not found.");
            return forFile(prompt);
        }
        return file;
    }
    public static File forFile (boolean preexisting) {
        return forFile(defaultPrompt("File"), preexisting);
    }
    public static File forFile (String prompt) {
        return forFile(prompt, true);
    }
    public static File forFile () {
        return forFile(defaultPrompt("File"), true);
    }
    public static Scanner forFileScanner(String prompt) {
        try {
            return new Scanner(forFile(prompt));
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException("If you see this, something has gone terribly wrong");
        }
    }
    public static Scanner forFileScanner() {
        return forFileScanner(defaultPrompt("Source File"));
    }
    public static PrintStream forFilePrintStream(String prompt, boolean preexisting) throws FileNotFoundException {
        return new PrintStream(forFile(prompt + " \u001b[31m\u001b[4m(WARNING: FILE CONTENTS WILL BE OVERWRITTEN)\u001b[0m", preexisting));
        /* \u001b[31m - red
         * \u001b[4m - underline
         * \u001b[0m - return to defaults
         */
    }
    public static PrintStream forFilePrintStream(boolean preexisting) throws FileNotFoundException {
        return forFilePrintStream(defaultPrompt("Output File"), preexisting);
    }
    public static PrintStream forFilePrintStream(String prompt) throws FileNotFoundException {
        return forFilePrintStream(prompt, false);
    }
    public static PrintStream forFilePrintStream() throws FileNotFoundException {
        return forFilePrintStream(defaultPrompt("Output File"), false);
    }
}