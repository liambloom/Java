package io.github.liambloom.softwareEngineering.chapter7;

import java.util.*;
import java.util.function.Supplier;

/**
 * Command line arguments:
 * -d, --debug      Puts it in debug mode. This means that it uses the
 *                      names Tom and Mary (from the example), instead
 *                      of asking the user for the names; and it prevents
 *                      the array from being shuffled
 */
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
    private static Player player1;
    private static Player player2;

    protected static class Player {
        public final String name;
        public final int playerNumber;
        private int score = 0;

        public Player(int playerNumber) {
            this(playerNumber, ((Supplier<String>) (() -> {
                System.out.printf("Player %d name: ", playerNumber);
                final String line = s.nextLine();
                return line;
            })).get());
        }

        public Player(int playerNumber, String name) {
            this.name = name;
            this.playerNumber = playerNumber;
            //print();
        }

        public void increment() {
            score++;
        }

        protected void print() {
            System.out.printf("          Player %d:  %-" + NAME_LEN + "sScore: %d%n", playerNumber, name, score);
        }
    }

    public static void main(String[] args)
        throws InterruptedException
    {
        // Process arguments
        for (String arg : args) {
            switch (arg) {
                case "-d":
                case "--debug":
                    if (debug) {
                        System.err.println("error: Duplicate argument: -d/--debug");
                        System.exit(1);
                    }
                    debug = true;
                    break;
                default:
                    System.err.println("error: Argument '" + arg + "' not recognized");
                    System.exit(1);
            }
        }

        // Initialize array
        for (int i = 0; i < NUM_PAIRS; i++) {
            board[2 * i] = i + 1;
            board[2 * i + 1] = i + 1;
        }

        // Shuffle the array
        shuffle();

        // Initialize players
        if (debug) {
            player1 = new Player(1, PLAYER_1_DEFAULT_NAME);
            player2 = new Player(2, PLAYER_2_DEFAULT_NAME);
        }
        else {
            player1 = new Player(1);
            player2 = new Player(2);
            section();
        }

        // Print the initial board
        printRound();

        // While loop to control the round
        boolean allFound = false;
        Player p = player1;
        while (!allFound) {
            int[] tempShow = new int[2];

            for (int i = 0; i <= 1; i++) {
                System.out.printf("%s, what is your %s guess: ", p.name, numToString(i + 1));

                String err = null;
                do {
                    if (err != null) {
                        System.out.println('\t' + err);
                        System.out.print('\t');
                    }

                    err = null;

                    if (s.hasNextInt()) {
                        tempShow[i] = s.nextInt() - 1;
                        if (tempShow[i] < 0 || tempShow[i] >= NUM_PAIRS * 2)
                            err = "Please enter a number 1-" + NUM_PAIRS * 2;
                        else if (found[tempShow[i]])
                            err = "You cannot turn over a card that is already visible";
                    }
                    else
                        err = "Please enter a valid integer";

                    s.nextLine();

                }
                while (err != null);

                found[tempShow[i]] = true;

                section();
                printRound();
            }
            
            if (board[tempShow[0]] == board[tempShow[1]]) {
                System.out.println("MATCH!!  You get to go again!");
                p.increment();
                allFound = true;
                for (int i = 0; i < found.length; i++) {
                    if (!found[i]) {
                        allFound = false;
                        break;
                    }
                }
            }
            else {
                p = p == player1 ? player2 : player1;
                System.out.printf(">>>>> NO Match!  %s's turn. <<<<<<%n", p.name);
                found[tempShow[0]] = false;
                found[tempShow[1]] = false;
            }
            Thread.sleep(1000);

            // Print new board at END of round
            section();
            printRound();
        }

        if (player1.score > player2.score)
            System.out.println(player1.name + " wins!");
        else if (player1.score < player2.score)
            System.out.println(player2.name + " wins!");
        else
            System.out.println("It's a tie!");
    }

    private static void printRound() throws InterruptedException {
        player1.print();
        //Thread.sleep(2500);
        player2.print();
        //Thread.sleep(2500);
        System.out.println();
        for (int i = 1; i <= NUM_PAIRS * 2; i++)
            System.out.print(i + "   ");
        System.out.println();
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
        System.out.println("_______________________________________");
        System.out.println();
    }

    private static String numToString(int num) {
        String s = Integer.toString(num);
        switch (num % 10) {
            case 1:
                s += "st";
                break;
            case 2:
                s += "nd";
                break;
            default:
                // This should never happen
                assert false;
        }
        return s; 
    }
}