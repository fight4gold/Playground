package net.fratzlow.disruptor.tech;

import com.lmax.disruptor.EventHandler;
import net.fratzlow.disruptor.model.Chassis;
import net.fratzlow.disruptor.model.Seat;
import org.apache.log4j.Logger;

/**
 * Created by frank on 10/02/14.
 */
public class DriverSeatHandler implements EventHandler<Chassis> {

    private static final Logger LOGGER = Logger.getLogger( DriverSeatHandler.class );

    @Override
    public void onEvent(Chassis chassis, long sequence, boolean endOfBatch) throws Exception {
        chassis.driver = new Seat();
        LOGGER.info("Add DriverSeat to " + sequence );
    }
}
