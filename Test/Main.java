import java.util.Random;

public class Main {
  public static Random r = new Random();
  public static void main (String[] args) {
    int i;
    while ((i = r.nextInt(1000)) <= 900) System.out.println(i);
  }
}