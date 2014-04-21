package net.fratzlow.disruptor.tech;

import com.lmax.disruptor.EventHandler;
import net.fratzlow.disruptor.model.Bonnet;
import net.fratzlow.disruptor.model.Chassis;
import org.apache.log4j.Logger;

/**
 * Created by frank on 1øo0/02/14.
 */
public class BonnetHandler implements EventHandler<Chassis> {

    private static final Logger LOGGER = Logger.getLogger( BonnetHandler.class );


    @Override
    public void onEvent(Chassis chassis, long sequence, boolean endOfBatch) throws Exception {
        chassis.bonnet = new Bonnet();
        LOGGER.info("Add Bonnet to " + sequence );
    }
}
