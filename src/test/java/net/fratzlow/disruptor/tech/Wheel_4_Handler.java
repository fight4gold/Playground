package net.fratzlow.disruptor.tech;

import com.lmax.disruptor.EventHandler;
import net.fratzlow.disruptor.model.Chassis;
import net.fratzlow.disruptor.model.Wheel;
import org.apache.log4j.Logger;

/**
 * Created by frank on 1øo0/02/14.
 */
public class Wheel_4_Handler implements EventHandler<Chassis> {

    private static final Logger LOGGER = Logger.getLogger( Wheel_4_Handler.class );

    @Override
    public void onEvent(Chassis chassis, long sequence, boolean endOfBatch) throws Exception {
        chassis.backRightWheel = new Wheel();
        LOGGER.info("Add back right wheel to " + sequence );
    }
}
