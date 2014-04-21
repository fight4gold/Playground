package net.fratzlow.disruptor.tech;

import com.lmax.disruptor.EventHandler;
import net.fratzlow.disruptor.model.Chassis;
import net.fratzlow.disruptor.model.Seat;
import org.apache.log4j.Logger;

/**
 * Created by frank on 10/02/14.
 */
public class RearSeatHandler implements EventHandler<Chassis> {

    private static final Logger LOGGER = Logger.getLogger( RearSeatHandler.class );

    @Override
    public void onEvent(Chassis chassis, long sequence, boolean endOfBatch) throws Exception {
        chassis.rear = new Seat();
        LOGGER.info("Add rear seat to " + sequence );

    }
}
