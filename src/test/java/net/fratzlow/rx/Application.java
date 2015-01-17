package net.fratzlow.rx;

import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * // TODO (FRa) : (FRa) : comment
 *
 * @author ratzlow@gmail.com
 * @since 2014-12-02
 */
public class Application {
    static Logger LOGGER = Logger.getLogger(Application.class);

    Queue<String> inQ = new ArrayBlockingQueue<>(1000);
    Queue<String> outQ = new ArrayBlockingQueue<>(1000);
    volatile boolean running = false;
    ExecutorService executorService;

    void start() {
        LOGGER.info("Starting App ...");
        executorService = Executors.newFixedThreadPool(2);
        running = true;
        executorService.submit(this::receive);
        LOGGER.info("Started App!");
    }

    void stop() {
        running = false;
        executorService.shutdownNow();
    }

    private void receive() {
        while (running) {
            String msg = inQ.poll();
            if (msg != null) {
                process(msg);
            } else {
                try {
                    Thread.sleep(3);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private void process(String msg) {
        LOGGER.info("Process msg " + msg );
        String isin = findInstrument();
        BigDecimal price = findPrice(isin);
        String id = createID();
        List<String> outMsgs = createNotifications(msg);
        send(outMsgs);
    }

    private List<String> createNotifications(String msg) {
        return Stream.iterate( 0, i -> i+1).limit(3).map(i -> "OutMsg_" + msg + "_" + i)
                .collect(Collectors.toList());
    }

    void send( List<String> msgs ) {
        msgs.stream().peek(msg -> LOGGER.info("Send msg to outQ: " + msg)).forEach(outQ::offer);
    }

    private String createID() {
        return UUID.randomUUID().toString();
    }

    private BigDecimal findPrice(String isin) {
        return BigDecimal.valueOf(87.9);
    }

    private String findInstrument() {
        return "GOOG";
    }
}
