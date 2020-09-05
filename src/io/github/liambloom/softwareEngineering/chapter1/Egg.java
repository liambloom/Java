package io.github.liambloom.softwareEngineering.chapter1;

public class Egg {
    public static void main () {
        Egg.top();
        Egg.middle();
        Egg.bottom();
    }
    public static void top () {
        System.out.println("  _______");
        System.out.println(" /       \\");
        System.out.println("/         \\");
    }
    public static void bottom () {
        System.out.println("\\         /");
        System.out.println(" \\_______/");
    }
    public static void middle () {
        System.out.println("-\"-'-\"-'-\"-");
    }
}