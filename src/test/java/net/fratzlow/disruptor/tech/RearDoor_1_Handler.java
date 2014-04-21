package net.fratzlow.disruptor.tech;

import com.lmax.disruptor.EventHandler;
import net.fratzlow.disruptor.model.Chassis;
import net.fratzlow.disruptor.model.Door;
import org.apache.log4j.Logger;

/**
 * Created by frank on 1øo0/02/14.
 */
public class RearDoor_1_Handler implements EventHandler<Chassis> {

    private static final Logger LOGGER = Logger.getLogger( RearDoor_1_Handler.class );

    @Override
    public void onEvent(Chassis chassis, long sequence, boolean endOfBatch) throws Exception {
        chassis.backLeftDoor = new Door();
        LOGGER.info("Add left back door to " + sequence );
    }
}
