package io.github.liambloom.softwareEngineering.chapter2;

public class SelfChecks
{
    public static void sc9 () {
        double x = 3.0;//2
        System.out.println("x is " + x);//1
        
        /* int */ x = 15.2; //1
        System.out.println("x is now " + x);//1
        
        int y;
        y = (int) x + 1; //1
        System.out.println("x and y are " + x + " and " + y); //1
    }
    public static void sc22 () {
        int total = 25;
        for (int number = 1; number <= (total / 2); number++) {
            total -= number;
            System.out.println(total + " " + number);
        }
    }
    public static void sc31 () {
        sc31Class.main();
    }
}

class sc31Class {
  public static int MAX_ODD = 21;

  public static void writeOdds() {
    // print each odd number
    for (int count = 1; count <= MAX_ODD; count += 2) {
      System.out.print(count + " ");
      //count = count + 2;
    }

    // print the last odd number
    System.out.println();
  }
  public static void main () {
    // write all odds up to 21
    writeOdds();

    // now, write all odds up to 11
    MAX_ODD = 11;
    writeOdds();
  }
}