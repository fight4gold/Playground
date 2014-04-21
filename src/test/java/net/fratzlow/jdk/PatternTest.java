package net.fratzlow.jdk;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by frank on 28/02/14.
 */
public class PatternTest {

    @Test
    public void testReplaceIgnoreCase() {
        String s_1 = "Frank ist cool";
        String s_2 = "Frank IST cool";
        String s_3 = "Frank Ist cool";

        String searchString = "ist";
        String IGNORE_CASE_FLAG = "(?i)";
        String pattern = IGNORE_CASE_FLAG + searchString;

        String expected = "Frank  cool";

        Assert.assertEquals(expected, s_1.replaceAll( pattern, ""));
        Assert.assertEquals(expected, s_2.replaceAll( pattern, ""));
        Assert.assertEquals(expected, s_3.replaceAll( pattern, ""));
    }
}
