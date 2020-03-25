public class Exercises {
    public static double exercise1 (int n) {
        double total = 0;
        for (double i = 1; i <= n; i++) {
            total += 1 / i;
        }
        return total;
    }
    public static String exercise2 (String str, int repititions) {
        String returnValue = "";
        for (int i = 0; i < repititions; i++) {
            returnValue += str;
        }
        return returnValue;
    }
}
