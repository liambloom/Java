package io.github.liambloom.softwareEngineering.chapter6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SelfChecks {
    public static void sc15 (String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File(new Exception().getStackTrace()[0].getFileName()));
        while (s.hasNextLine()) System.out.println(s.nextLine());
    }
}