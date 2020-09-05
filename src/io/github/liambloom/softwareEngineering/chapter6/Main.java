package io.github.liambloom.softwareEngineering.chapter6;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
    public static void main (String[] args) throws FileNotFoundException {
        System.out.println("Copy a file");
        copyFile(Ask.forFileScanner(), Ask.forFilePrintStream(true));
    }
    public static void copyFile (String from, String to) throws FileNotFoundException {
        Scanner src = new Scanner(new File(from));
        PrintStream out = new PrintStream(new File(to));
        copyFile(src, out);
    }
    public static void copyFile (Scanner src, PrintStream out) {
        while (src.hasNextLine()) out.println(src.nextLine());
    }
}
