package net.fratzlow.rx;

import org.apache.log4j.Logger;
import org.junit.Test;
import rx.Observable;
import rx.Scheduler;
import rx.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

/**
 * // TODO (FRa) : (FRa) : comment
 *
 * @author ratzlow@gmail.com
 * @since 2014-12-05
 */
public class SamplesTest {
    private Logger LOGGER = Logger.getLogger(SamplesTest.class);

    @Test
    public void test() throws InterruptedException {
        Integer[] ar = {1, 2, 3, 4};
        Scheduler scheduler = Schedulers.computation();
        Observable.interval(500, TimeUnit.MILLISECONDS, scheduler).range(1, 10).subscribe( LOGGER::info);
        Thread.sleep(5000);
    }
}
