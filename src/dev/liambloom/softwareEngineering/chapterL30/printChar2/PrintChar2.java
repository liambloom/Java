package dev.liambloom.softwareEngineering.chapterL30.printChar2;

import java.util.Optional;

//The thread class for printing specific characters in specified times
class PrintChar2 implements Runnable
{
    private String charsToPrint;   // the character(s) to print
    private int times;                    // the times to repeat

    // thread class constructor
    public PrintChar2(String s,int t) {
        charsToPrint = s;
        times = t;
    }

    @Override
    public void run()     {
        for (int i=1; i <= times; i++) {
            System.out.print(charsToPrint);
            if ((i >= times / 2) && (times % 2 == 0)) {
                int r1 = (int) (Math.random() * 10 + 1);
                switch (r1) {
                    case 1, 2, 3, 4, 5 -> {
                        String priorityLevelString;
                        int priorityLevel;
                        switch ((int) (Math.random() * 3 + 1)) {
                            case 1 -> {
                                priorityLevelString = "MIN";
                                priorityLevel = Thread.MIN_PRIORITY;
                            }
                            case 2 -> {
                                priorityLevelString = "NORM";
                                priorityLevel = Thread.NORM_PRIORITY;
                            }
                            case 3 -> {
                                priorityLevelString = "MAX";
                                priorityLevel = Thread.MAX_PRIORITY;
                            }
                            default -> throw new IllegalStateException();
                        }
                        System.out.println(this + "setPriority(" + priorityLevelString + "_PRIORITY)");
                        Thread.currentThread().setPriority(priorityLevel);
                    }
                    case 6 -> spawnNew();
                    case 7 -> spawnNew()
                            .ifPresent((ConsumerCatchesInteruptedException<Thread>) Thread::join);
                    case 8 -> {
                        System.out.println(this + ".yield()");
                        Thread.yield();
                    }
                    case 9 -> {
                        System.out.println(this + ".sleep(500)");
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    case 10 -> {
                        System.out.println(this + ".stop()");
                        Thread.currentThread().stop();
                    }


                    // Random number r1 (1-10) results as follows:
    /* (1)-(5) [50% of the time] generate an r2 such that 1 <= r2 <= 3 resulting as follows:
     .(1) Print the current Thread path and name and set the Priority to MIN.
     .(2) Print the current Thread path and name and set the Priority to NORMAL.
     .(3) Print the current Thread path and name and set the Priority to MAX.
       (6) Create a new thread off of the current one and &OpenCurlyDoubleQuote;start&CloseCurlyDoubleQuote; it.
                Ex.  current thread = a1  new thread = a1a2  and a thread off of that would be a1a2a3
                        NOTE1: there most likely will be more than one time that a1 spons a new a1a2
                                      thread and that is ok.  Same with the others.
                        NOTE2:  Make sure to STOP the Thread when it reaches its 3rd generation,
                                       Otherwise it may go on forever!
                        Ex  a1a2a3  STOP it at this point!  So NO a1a2a3a4!
       (7) Create a new thread off of the current one and &OpenCurlyDoubleQuote;join&CloseCurlyDoubleQuote; it.
                Ex.  current thread = a1  new thread = a1a2  and a thread off of that would be a1a2a3
                        NOTE1: there most likely will be more than one time that a1 spons a new a1a2
                                      thread and that is ok.  Same with the others.
                        NOTE2:  Make sure to STOP the Thread when it reaches its 3rd generation,
                                       Otherwise it may go on forever!
                        Ex  a1a2a3  STOP it at this point!  So NO a1a2a3a4!
       (8) Yield the current thread.
       (9) Sleep the current thread for 500 milliseconds.
       (10) Stop the current thread.
       (Bonus): Add in a few of your own.
       NOTE: you will have to look through the book, online, etc in order to accomplish this.  There
                   are various places that will need to be in a try-catch Exception Handler.
    */

                } // switch r1
            }// if
        } // for
    }  // run()

    private Optional<Thread> spawnNew() {
        if (charsToPrint.length() == 6)
            return Optional.empty();
        String newName = charsToPrint + charsToPrint.charAt(0) + (Integer.parseInt(charsToPrint.substring(charsToPrint.length() - 1)) + 1);
        System.out.print("\\NewThread Starting:" + newName);
        return Optional.of(new Thread(new PrintChar2(newName, times)));
    }

    @Override
    public String toString() {
        return "\\" + charsToPrint + Thread.currentThread().getName();
    }
} // PrintChar2

