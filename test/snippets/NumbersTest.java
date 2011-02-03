package snippets;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static snippets.Dates.makeDateTime;
import static snippets.Dates.since;
import static snippets.Numbers.*;

/**
 * User: zahir
 * Date: 11-2-2
 * Time: 上午5:15
 */
public class NumbersTest {
    @Test
    public void testSecond() throws Exception {

        //the same year
        Date someDate = makeDateTime(2000, 0, 1, 0, 0, 50);
        Date result = since(someDate, second(45));
        assertEquals(makeDateTime(2000, 0, 1, 0, 1, 35), result);

        //cross a year
        someDate = makeDateTime(2000, 11, 31, 23, 59, 59);
        result = since(someDate, second(2));

        assertEquals(makeDateTime(2001, 0, 1, 0, 0, 1), result);

    }

    @Test
    public void testMinute() throws Exception {
        //the same year
        Date someDate = makeDateTime(2000, 0, 1, 0, 0, 50);
        Date result = since(someDate, minute(1));
        assertEquals(makeDateTime(2000, 0, 1, 0, 1, 50), result);
    }

    @Test
    public void testHour() throws Exception {
        //the same year
        Date someDate = makeDateTime(2000, 0, 1, 0, 0, 50);
        Date result = since(someDate, hour(1));
        assertEquals(makeDateTime(2000, 0, 1, 1, 0, 50), result);
    }

    @Test
    public void testDay() throws Exception {
        Date someDate = makeDateTime(2000, 0, 1, 0, 0, 50);
        Date result = since(someDate, day(1));
        assertEquals(makeDateTime(2000, 0, 2, 0, 0, 50), result);
    }

    @Test
    public void testMonth() throws Exception {
        assertEquals(30 * day(1), month(1));
    }

    @Test
    public void testYear() throws Exception {
        assertEquals(12 * month(1), year(1));

        System.out.println(year(1) / (60 * 60 * 24 * 30 * 12));
    }
}
