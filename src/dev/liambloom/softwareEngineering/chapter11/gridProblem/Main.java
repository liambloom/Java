package dev.liambloom.softwareEngineering.chapter11.gridProblem;

public class Main {
    public static final String ERROR = "\u001b[31merror\u001b[0m: ";
    // public static final String WARNING = "\u001b[33mwarning\u001b[0m: ";

    public static void main(String[] args) {
        /*if (args.length < 1)
            error("No output method given");
        else if (args.length > 1)
            error("Expected 1 argument, received " + args.length);
        else if (args[0].toLowerCase().equals("cli"))*/
            new CLI().main();
        /*else if (args[0].toLowerCase().equals("gui"))
            new GUI().main();*/
        /*else
            error(args[0] + " is not a valid argument");*/
    }

    public static void error(String err) {
        System.err.println("\u001b[31merror\u001b[0m: " + err);
        System.err.printf("\u001b[36mnote\u001b[0m: Try using either `grid cli' or `grid gui'");
        System.exit(1);
    }
}
