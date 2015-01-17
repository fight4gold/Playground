package net.fratzlow.rx;

import org.junit.Test;
import rx.Observable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * // TODO (FRa) : (FRa) : comment
 *
 * @author ratzlow@gmail.com
 * @since 2014-12-02
 */
public class ApplicationTest {

    @Test
    public void test() throws InterruptedException {
        Application app = new Application();
        app.start();
        //Observable.create( ).
        app.inQ.add("OrderMsg");
        Thread.sleep(500);
        app.stop();
    }


    Map<String, List<String>> createFlow() {
        return Stream.iterate(1, i -> i+1).limit(3).collect(
                toMap(i -> "inMsg_" + i,
                      j -> Arrays.asList("outMsg_1_"+j, "outMsg_3"+j, "outMsg_3"+j ))

        );
    }
}
