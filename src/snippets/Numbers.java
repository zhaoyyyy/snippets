package snippets;

/**
 * User: zahir
 * Date: 11-2-2
 * Time: 上午5:09
 */
public class Numbers {

    public static int second(int n) {
        return n;
    }

    public static int minute(int n) {
        return 60 * second(n);
    }

    public static int hour(int n) {
        return 60 * minute(n);
    }

    public static int day(int n) {
        return 24 * hour(n);
    }

    public static int month(int n) {
        return 30 * day(n);
    }

    public static int year(int n) {
        return 12 * month(n);
    }

}
