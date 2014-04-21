package net.fratzlow.disruptor.tech;

import com.lmax.disruptor.EventHandler;
import net.fratzlow.disruptor.model.Chassis;
import net.fratzlow.disruptor.model.Seat;
import org.apache.log4j.Logger;

/**
 * Created by frank on 10/02/14.
 */
public class PassengerSeatHandler implements EventHandler<Chassis> {

    private static final Logger LOGGER = Logger.getLogger( PassengerSeatHandler.class );

    @Override
    public void onEvent(Chassis chassis, long sequence, boolean endOfBatch) throws Exception {
        chassis.passenger = new Seat();
        LOGGER.info("Add passenger seat to " + sequence );
    }
}
