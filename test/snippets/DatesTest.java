package snippets;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static java.util.Calendar.MILLISECOND;
import static org.junit.Assert.assertEquals;
import static snippets.Dates.*;

/**
 * User: zahir
 * Date: 11-2-2
 * Time: 上午4:31
 */
public class DatesTest {

    @Test
    public void testAtBeginningOfMonth() throws Exception {

        Date someDate = makeDateTime(2011, 1, 12, 12, 13, 1);
        Date result = new Date(Dates.atBeginningOfMonth(someDate.getTime()));

        assertDateTimeEqualsIgnoreMilliseconds(makeDateTime(2011, 1, 1, 0, 0, 0), result);
    }

    @Test
    public void testAtEndOfMonth() throws Exception {

        Date someDate = makeDateTime(2000, 5, 16, 1, 1, 1);
        Date result = new Date(atEndOfMonth(someDate.getTime()));
        assertDateTimeEqualsIgnoreMilliseconds(makeDateTime(2000, 5, 30, 23, 59, 59), result);
    }

    @Test
    public void testSince() throws Exception {

        //the same year
        Date someDate = makeDateTime(2000, 0, 1, 0, 0, 50);
        Date result = since(someDate, 45);
        assertDateTimeEqualsIgnoreMilliseconds(makeDateTime(2000, 0, 1, 0, 1, 35), result);

        result = since(someDate, Numbers.month(1));
        assertDateTimeEqualsIgnoreMilliseconds(makeDateTime(2000, 1, 1, 0, 0, 50), result);

        result = since(someDate, Numbers.year(1));
        assertDateTimeEqualsIgnoreMilliseconds(makeDateTime(2001, 0, 1, 0, 0, 50), result);

        //cross a year
        someDate = makeDateTime(2000, 11, 31, 23, 59, 59);
        result = since(someDate, 2);

        assertDateTimeEqualsIgnoreMilliseconds(makeDateTime(2001, 0, 1, 0, 0, 1), result);


    }

    @Test
    public void testAgo() throws Exception {

        //the same year
        Date someDate = makeDateTime(2000, 0, 1, 0, 0, 50);
        Date result = ago(someDate, 45);
        assertDateTimeEqualsIgnoreMilliseconds(makeDateTime(2000, 0, 1, 0, 0, 5), result);

        //cross a year
        result = ago(someDate, 51);

        assertDateTimeEqualsIgnoreMilliseconds(makeDateTime(1999, 11, 31, 23, 59, 59), result);
    }

    @Test
    public void testMonthAgo() throws Exception {
        Date someDate = makeDate(2001, 0, 1);
        Date result = monthAgo(someDate, 1);
        assertDateTimeEqualsIgnoreMilliseconds(makeDate(2000, 11, 1), result);
    }

    @Test
    public void testYearAgo() throws Exception {
        Date someDate = makeDate(2001, 0, 1);
        Date result = yearAgo(someDate, 1);
        assertDateTimeEqualsIgnoreMilliseconds(makeDate(2000, 0, 1), result);
    }

    @Test
    public void testMonthSince() throws Exception {

        //the same year
        Date someDate = makeDateTime(2000, 0, 1, 0, 0, 50);
        Date result = monthSince(someDate, 1);
        assertDateTimeEqualsIgnoreMilliseconds(makeDateTime(2000, 1, 1, 0, 0, 50), result);


        result = monthSince(someDate, 12);
        assertDateTimeEqualsIgnoreMilliseconds(makeDateTime(2001, 0, 1, 0, 0, 50), result);
    }

    @Test
    public void testYearSince() throws Exception {
         //the same year
        Date someDate = makeDateTime(1999, 0, 1, 0, 0, 50);
        Date result = yearSince(someDate, 1);
        assertDateTimeEqualsIgnoreMilliseconds(makeDateTime(2000, 0, 1, 0, 0, 50), result);
    }

    private void assertDateTimeEqualsIgnoreMilliseconds(Date expected, Date actual) {
        if ( expected.equals(actual) ) {
            return;
        }
        Calendar calA = toCalendar(expected);
        calA.set(MILLISECOND, 0);

        Calendar calB = toCalendar(actual);
        calB.set(MILLISECOND, 0);

        assertEquals(calA.getTime(), calB.getTime());
    }

}
