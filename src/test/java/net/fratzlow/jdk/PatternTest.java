package net.fratzlow.jdk;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @Test
    public void testReplaceMultiple() throws IOException {
        String text = String.join("", Files.readAllLines(Paths.get("src/test/resources/escapeText.txt")));
        Pattern pattern = Pattern.compile("((\\\\\"id\\\\\"\\s*:\\s*)(\\d*))");
        Matcher matcher = pattern.matcher(text);
        StringBuffer sb = new StringBuffer();

        long ts = System.currentTimeMillis();
        while ( matcher.find() ) {
            matcher.appendReplacement(sb, matcher.group(2) + ++ts );
        }

        matcher.appendTail(sb);

        System.out.println(sb);
    }
}
