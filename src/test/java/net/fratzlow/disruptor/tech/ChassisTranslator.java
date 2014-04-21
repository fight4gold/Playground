package net.fratzlow.disruptor.tech;

import com.lmax.disruptor.EventTranslator;
import net.fratzlow.disruptor.model.Chassis;

/**
 * Created by frank on 10/02/14.
 */
public class ChassisTranslator implements EventTranslator<Chassis> {

    @Override
    public void translateTo(Chassis event, long sequence) {
        event.reset();
    }
}
