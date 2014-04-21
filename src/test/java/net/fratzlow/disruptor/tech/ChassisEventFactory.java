package net.fratzlow.disruptor.tech;

import com.lmax.disruptor.EventFactory;
import net.fratzlow.disruptor.model.Chassis;

/**
 * Created by frank on 10/02/14.
 */
public class ChassisEventFactory implements EventFactory<Chassis> {

    @Override
    public Chassis newInstance() {
        return new Chassis();
    }
}
