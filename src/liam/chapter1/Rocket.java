package liam.chapter1;

public class Rocket {
    public static void main (int num) { //
        cone(num);
        boxTop(num);
        boxTopText(num, "United", "States");
        drawBox(num);
        cone(num);
    }
    public static void drawBox (int num) {
        boxTop(num);
        horizontal(num);
    }
    public static void cone (int num) {
        outRepeat(num, "   /\\   ");
        outRepeat(num, "  /  \\  ");
        outRepeat(num, " /    \\ ");
    }
    private static void boxTop (int num) {
        boxTopText(num, "      ", "      ");
    }
    private static void boxTopText (int num, String txtLn1, String txtLn2) {
        horizontal(num);
        verticalLines(num, txtLn1);
        verticalLines(num, txtLn2);
    }
    private static void horizontal (int num) {
        outRepeat(num, "+------+");
    }
    private static void verticalLines (int num, String txt) {
        if (txt.length() != 6) {
            System.out.println(txt + " must be 6 characters");
            txt = "      ";
        }
        outRepeat(num, "|" + txt + "|");
    }
    private static void outRepeat (int num, String output) {
        for (int i = 0; i < num; i++) {
            System.out.print(output + " ");
        }
        System.out.println();
    }
}