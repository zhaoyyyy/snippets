package snippets;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static java.util.Calendar.*;


/**
 * User: zahir
 * Date: 11-2-2
 * Time: 上午4:24
 */
public class Dates {

    public static Date since(Date dateTime, int seconds) {
        Calendar cal = toCalendar(dateTime);

        if ( seconds >= Numbers.year(1) ) {
            return yearSince(dateTime, seconds / ( 60 * 60 * 24 * 30 * 12 ));
        }

        if ( seconds >= Numbers.month(1) ) {
            return monthSince(dateTime, seconds / (60 * 60 * 24 * 30));
        }


        cal.add(SECOND, seconds);
        return cal.getTime();
    }

    public static Date monthSince(Date dateTime, int months) {
        Calendar cal = toCalendar(dateTime);
        cal.add(MONTH, months);
        return cal.getTime();
    }

    public static Date yearSince(Date dateTime, int years) {
        Calendar cal = toCalendar(dateTime);
        cal.add(YEAR, years);
        return cal.getTime();
    }


    public static Date ago(Date dateTime, int seconds) {
        Calendar cal = toCalendar(dateTime);
        cal.add(SECOND, -seconds);
        return cal.getTime();
    }

    public static Date monthAgo(Date dateTime, int months) {
        Calendar cal = toCalendar(dateTime);
        cal.add(MONTH, -months);
        return cal.getTime();
    }

    public static Date yearAgo(Date dateTime, int years) {
        Calendar cal = toCalendar(dateTime);
        cal.add(YEAR, -years);
        return cal.getTime();
    }

    public static long atBeginningOfMonth(long time) {
        Calendar cal = makeCalendar(time);
        cal.set(DAY_OF_MONTH, 1);
        return atBeginningOfDay(cal.getTimeInMillis());
    }

    public static long atEndOfMonth(long time) {
        Calendar cal = makeCalendar(time);
        int maxDayOfMonth = cal.getActualMaximum(DAY_OF_MONTH);
        cal.set(DAY_OF_MONTH, maxDayOfMonth);
        return atEndOfDay(cal.getTimeInMillis());
    }

    public static long atBeginningOfDay(long time) {
        Calendar cal = makeCalendar(time);
        cal.set(HOUR_OF_DAY, 0);
        cal.set(MINUTE, 0);
        cal.set(SECOND, 0);
        return cal.getTimeInMillis();
    }

    public static long atEndOfDay(long time) {
        Calendar cal = makeCalendar(time);
        cal.set(HOUR_OF_DAY, 23);
        cal.set(MINUTE, 59);
        cal.set(SECOND, 59);
        return cal.getTimeInMillis();
    }


    public static Calendar makeCalendar(long time) {
        Calendar cal = getInstance();
        cal.setTime(new Date(time));
        return cal;
    }

    public static Calendar makeCalendar(int year, int month, int dayOfMonth, int hour, int minute, int second) {
        Calendar cal = getInstance();
        cal.set(year, month, dayOfMonth, hour, minute, second);
//        cal.set(MILLISECOND, 0);
        return cal;
    }

    public static Calendar toCalendar(Date dateTime) {
        return makeCalendar(dateTime.getTime());
    }

    public static String format(Date dateTime, String pattern) {
        return new SimpleDateFormat(pattern).format(dateTime);
    }

    public static String formatAsISO8601(Date dateTime) {
        return format(dateTime, "yyyy-MM-dd HH:mm:ss");
    }

    public static Date makeDateTime(int year, int month, int dayOfMonth, int hour, int minute, int second) {
        return makeCalendar(year, month, dayOfMonth, hour, minute, second).getTime();
    }

    public static Date makeDate(int year, int month, int dayOfMonth) {
        return makeCalendar(year, month, dayOfMonth, 0, 0, 0).getTime();
    }

    public static boolean equalsIgnoreMilliseconds(Date dateTimeA, Date dateTimeB) {
        if ( dateTimeA.equals(dateTimeB) ) {
            return true;
        }
        Calendar calA = toCalendar(dateTimeA);
        calA.set(MILLISECOND, 0);

        Calendar calB = toCalendar(dateTimeA);
        calB.set(MILLISECOND, 0);

        return calA.equals(calB);
    }
}
