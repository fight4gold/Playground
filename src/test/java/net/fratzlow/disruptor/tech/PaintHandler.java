package net.fratzlow.disruptor.tech;

import com.lmax.disruptor.EventHandler;
import net.fratzlow.disruptor.model.Chassis;
import org.apache.log4j.Logger;

/**
 * Created by frank on 1øo0/02/14.
 */
public class PaintHandler implements EventHandler<Chassis> {

    private static final Logger LOGGER = Logger.getLogger( PaintHandler.class );

    @Override
    public void onEvent(Chassis chassis, long sequence, boolean endOfBatch) throws Exception {
        chassis.painted = true;
        LOGGER.info("Paint chassis " + sequence );
    }
}
