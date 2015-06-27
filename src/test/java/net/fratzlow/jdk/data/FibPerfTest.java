package net.fratzlow.jdk.data;

import org.junit.Test;

/**
 * @author ratzlow@gmail.com
 * @since 2015-04-22
 */
public class FibPerfTest {

    @Test
    public void testFib() {
        long max = 5_000_000_000L;

        long start = System.currentTimeMillis();
        long sum = 0;
        for (long i=0; i < max; i++) {
            sum += i;
        }

        System.out.println(String.format("Duration for sum=%d in ms %d",
                sum, System.currentTimeMillis() - start));
    }
}
