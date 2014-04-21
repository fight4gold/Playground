package net.fratzlow.disruptor.tech;

import com.lmax.disruptor.EventHandler;
import net.fratzlow.disruptor.model.Chassis;
import net.fratzlow.disruptor.model.Door;
import org.apache.log4j.Logger;

/**
 * Created by frank on 1øo0/02/14.
 */
public class FrontDoor_2_Handler implements EventHandler<Chassis> {

    private static final Logger LOGGER = Logger.getLogger( FrontDoor_2_Handler.class );

    @Override
    public void onEvent(Chassis chassis, long sequence, boolean endOfBatch) throws Exception {
        chassis.frontRightDoor = new Door();
        LOGGER.info("Add right front door to " + sequence );
    }
}
