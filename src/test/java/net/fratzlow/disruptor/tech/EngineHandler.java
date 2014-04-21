package net.fratzlow.disruptor.tech;

import com.lmax.disruptor.EventHandler;
import net.fratzlow.disruptor.model.Chassis;
import net.fratzlow.disruptor.model.Engine;
import org.apache.log4j.Logger;

/**
 * Created by frank on 10/02/14.
 */
public class EngineHandler implements EventHandler<Chassis> {

    private static final Logger LOGGER = Logger.getLogger( EngineHandler.class );

    @Override
    public void onEvent(Chassis chassis, long sequence, boolean endOfBatch) throws Exception {
        chassis.engine = new Engine();
        LOGGER.info("Add Engine to " + sequence );
    }
}
