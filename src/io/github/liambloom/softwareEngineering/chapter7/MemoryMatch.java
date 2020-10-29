package io.github.liambloom.softwareEngineering.chapter7;

import java.util.*;
import java.util.function.Supplier;

import io.github.liambloom.softwareEngineering.chapter7.ANSI.*;
import io.github.liambloom.softwareEngineering.chapter7.ANSI.Display.Color;


// ANSI Codes: https://gist.github.com/fnky/458719343aabd01cfb17a3a4f7296797
public class MemoryMatch {
    public static final int NUM_PAIRS = 4;
    public static final int[] board = new int[NUM_PAIRS * 2];
    public static final boolean[] found = new boolean[NUM_PAIRS * 2];
    public static final Random rand = new Random();
    public static final String PLAYER_1_DEFAULT_NAME = "Tom";
    public static final String PLAYER_2_DEFAULT_NAME = "Mary";
    public static final Scanner s = new Scanner(System.in);
    public static final int NAME_LEN = 12;
    private static boolean debug = false;
    private static boolean useANSI = true;
    private static boolean asciiOnly = false;
    private static Player player1;
    private static Player player2;

    protected static class Player {
        public final String name;
        public final int playerNumber;
        private int score = 0;

        public Player(int playerNumber) {
            this(playerNumber, ((Supplier<String>) (() -> {
                System.out.printf("Player %d name: ");
                return s.nextLine();
            })).get());
        }

        public Player(int playerNumber, String name) {
            this.name = name;
            this.playerNumber = playerNumber;
            //print();
        }

        public void increment() {
            score++;
            update();
        }

        protected void print() {
            goToRow();
            System.out.printf("          Player %d:  %-" + NAME_LEN + "sScore: %d%n", playerNumber, name, score);
        }

        protected void update() {
            if (useANSI) {
                goToRow();
                Cursor.moveToCol(42);
                System.out.print(score);
            }
            else
                print();
        }

        private void goToRow() {
            if (useANSI) {
                Cursor.revert();
                Cursor.moveDown(playerNumber - 1);
            }
        }
    }

    public static void main(String[] args) {
        // Process arguments
        Iterator<String> iter = Arrays.stream(args).iterator();
        while (iter.hasNext()) {
            final String arg = iter.next();
            switch (arg) {
                case "-d":
                case "--debug":
                    if (debug) {
                        error("Duplicate argument: -d/--debug");
                        System.exit(1);
                    }
                    debug = true;
                    break;
                case "--no-ansi":
                    if (!useANSI) {
                        error("Duplicate argument: --no-ansi");
                        System.exit(1);
                    }
                    useANSI = false;
                    break;
                case "--ascii-only":
                    if (asciiOnly) {
                        error("Duplicate argument: --ascii-only");
                        System.exit(1);
                    }
                    asciiOnly = true;
                    break;
                default:
                    error("Argument '" + arg + "' not recognized");
                    System.exit(1);
            }
        }

        // Initialize array
        for (int i = 0; i < NUM_PAIRS; i++) {
            board[2 * i] = i;
            board[2 * i + 1] = i;
        }

        // Shuffle the array
        shuffle();

        // Save initial cursor position
        if (useANSI)
            Cursor.save();

        // Initialize players
        if (debug) {
            player1 = new Player(1, PLAYER_1_DEFAULT_NAME);
            player2 = new Player(2, PLAYER_2_DEFAULT_NAME);
        }
        else {
            player1 = new Player(1);
            player2 = new Player(2);
        }
        if (useANSI) {
            Cursor.revert();
            Eraser.clearDown();
        }
        else {
            section();
        }

        // Print the initial board
        printRound();
    }

    private static void printRound() {
        player1.print();
        player2.print();
        System.out.println();
        for (int i = 0; i < NUM_PAIRS * 2; i++)
            System.out.print(i + "   ");
        for (int i = 0; i < NUM_PAIRS * 2; i++) {
            if (found[i])
                System.out.print(board[i]);
            else
                System.out.print('*');
            System.out.print("   ");
        }
        System.out.println();
        System.out.println();
    }

    private static void error(String s) {
        if (useANSI)
            Display.setFgColor(Color.Red);
        System.out.println("\u001b[1;31merror:\u001b[0m " + s);
        if (useANSI)
            Display.reset();
    }

    private static void shuffle() {
        if (debug) 
            return;
        for (int i = board.length - 1; i > 0; i--) {
            int j = rand.nextInt(i);
            int temp = board[i];
            board[i] = board[j];
            board[j] = temp;
        }
    }

    private static void section() {
        if (!useANSI) {
            System.out.println("_______________________________________");
            System.out.println();
        }
    }

    private static String numToString(int num) {
        String s = Integer.toString(num);
        switch (num % 10) {
            case 1:
                s += superscript("st");
                break;
            case 2:
                s += superscript("nd");
                break;
            default:
                // This should never happen
                assert false;
        }
        return s; 
    }

    // Note: this is not a general superscript function.
    // This works specifically for my purpose, and that's it
    private static String superscript(String original) {
        if (asciiOnly)
            return original;
        String sup = "";
        for (int i = 0; i < original.length(); i++) {
            final char c = original.charAt(i);
            switch (c) {
                case 's':
                    sup += '\u02E2';
                    break;
                case 't':
                    sup += '\u1D57';
                    break;
                case 'n':
                    sup += '\u207F';
                    break;
                case 'd':
                    sup += '\u1D48';
                    break;
                default:
                    // This should never happen
                    assert false;
            }
        }
        return sup;
    }
}