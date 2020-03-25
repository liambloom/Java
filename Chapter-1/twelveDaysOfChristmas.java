public class twelveDaysOfChristmas
{
    // 1st way of doing it
    public static void main () {
        startOfVerse("first");
        day1();
        startOfVerse("second");
        day2();
        startOfVerse("third");
        day3();
        startOfVerse("fourth");
        day4();
        startOfVerse("fifth");
        day5();
        startOfVerse("sixth");
        day6();
        startOfVerse("seventh");
        day7();
        startOfVerse("eighth");
        day8();
        startOfVerse("ninth");
        day9();
        startOfVerse("tenth");
        day10();
        startOfVerse("eleventh");
        day11();
        startOfVerse("twelfth");
        day12();
    }
    public static void day1 () {
        System.out.println("a partridge in a pear tree.\n");
    }
    public static void day2 () {
        System.out.println("two turle doves, and");
        day1();
    }
    public static void day3 () {
        System.out.println("three Frence hens,");
        day2();
    }
    public static void day4 () {
        System.out.println("four falling birds,");
        day3();
    }
    public static void day5 () {
        System.out.println("five goldn rings,");
        day4();
    }
    public static void day6 () {
        System.out.println("six geese a-layin,");
        day5();
    }
    public static void day7 () {
        System.out.println("seven swans a-swimming,");
        day6();
    }
    public static void day8 () {
        System.out.println("eight mains a-milking,");
        day7();
    }
    public static void day9 () {
        System.out.println("nine ladies dancing,");
        day8();
    }
    public static void day10 () {
        System.out.println("ten lords a-leaping,");
        day9();
    }
    public static void day11 () {
        System.out.println("eleven pipers piping,");
        day10();
    }
    public static void day12 () {
        System.out.println("Twelve drummers drumming");
        day11();
    }
    private static void startOfVerse (String day) {
        System.out.println("On the " + day + " of Christmas");
        System.out.println("my true love sent to me");
    }
    /* Maybe I'll finish this when I actally learn arrays
    public static void loop () {
        String[] numbers = {
            "a", 
            "two", 
            "three",
            "four",
            "five",
            "six",
            "seven",
            "eight",
            "nine",
            "ten",
            "eleven",
            "twelve"
        };
        String[] gifts = {
            "partridge in a pear tree.",
            "turtle doves,",
            "three Frence hens,",
            "
            "drummers drumming"
        };
        for (int verse = 1; verse <= 12; verse++) {
            startOfVerse(numbers[verse]);
            for (int line = verse; line > 0; line--) {
                
            }
        }
    }*/
}
