package net.fratzlow.disruptor;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import mockit.Invocation;
import mockit.Mock;
import mockit.MockUp;
import mockit.integration.junit4.JMockit;
import net.fratzlow.disruptor.model.Chassis;
import net.fratzlow.disruptor.tech.*;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.Executors;


/**
 * This disruptor is wired to reflect the config presented at
 * http://qconlondon.com/dl/qcon-london-2012/slides/TrishaGee_ConcurrentProgrammingUsingTheDisruptor.pdf
 */
@RunWith(JMockit.class)
public class DisruptorMainTest {

    private static final int carsToProduceNo = 5;

    EngineHandler engineHandler = new EngineHandler();

    DriverSeatHandler driverSeatHandler = new DriverSeatHandler();
    PassengerSeatHandler passengerSeatHandler = new PassengerSeatHandler();
    RearSeatHandler rearSeatHandler = new RearSeatHandler();

    FrontDoor_1_Handler doorHandler_1 = new FrontDoor_1_Handler();
    FrontDoor_2_Handler doorHandler_2 = new FrontDoor_2_Handler();
    RearDoor_1_Handler doorHandler_3 = new RearDoor_1_Handler();
    RearDoor_2_Handler doorHandler_4 = new RearDoor_2_Handler();

    BonnetHandler bonnetHandler = new BonnetHandler();
    PaintHandler paintHandler = new PaintHandler();

    Wheel_1_Handler wheel_1_handler = new Wheel_1_Handler();
    Wheel_2_Handler wheel_2_handler = new Wheel_2_Handler();
    Wheel_3_Handler wheel_3_handler = new Wheel_3_Handler();
    Wheel_4_Handler wheel_4_handler = new Wheel_4_Handler();



    @Test
    public void testDslWiring() throws InterruptedException {
        mockDisruptorClasses();

        //--------------------------------------------------------------------------------------------------------------
        // Setup Disruptor
        //--------------------------------------------------------------------------------------------------------------
        Disruptor<Chassis> disruptor = new Disruptor<Chassis>(
                new ChassisEventFactory(),
                8,
                Executors.newFixedThreadPool(16),
                ProducerType.SINGLE,
                new SleepingWaitStrategy()
        );

        configureDsl(disruptor);


        disruptor.start();

        //--------------------------------------------------------------------------------------------------------------
        // start production of cars
        //--------------------------------------------------------------------------------------------------------------
        final ChassisTranslator translator = new ChassisTranslator();
        for ( int i=0; i < carsToProduceNo; i++ ) {
            disruptor.publishEvent( translator );
        }

        disruptor.getBarrierFor(paintHandler);


        //--------------------------------------------------------------------------------------------------------------
        // Stop Disruptor
        //--------------------------------------------------------------------------------------------------------------

        disruptor.shutdown();

        Thread.sleep(1000);
    }

    private void mockDisruptorClasses() {
        new MockUp<BatchEventProcessor>() {
            @Mock public Sequence getSequence(Invocation invocation) {
                Sequence sequence = invocation.proceed();
                System.out.println("==> getSequence() = " + sequence.get());
                return sequence;
            }
        };

        new MockUp<RingBuffer>() {
            @Mock public Object get(Invocation invocation, long sequence) {
                Object event = invocation.proceed();
                System.out.println("==> Return event = " + event + "@" + sequence);
                return event;
            }
        };
    }




    private void configureDsl(Disruptor<Chassis> disruptor) {

        // create RingBuffer -> |SequenceBarrier| -> n seatHandlers + engineHandler
        disruptor.handleEventsWith(
                engineHandler,
                driverSeatHandler, passengerSeatHandler, rearSeatHandler
        );

        // create engineHandler -> |SequenceBarrier| -> bonnetHandler
        disruptor.after(engineHandler).then(bonnetHandler);

        // n seatHandlers -> |SequenceBarrier| -> n doorHandlers
        disruptor.after( driverSeatHandler, passengerSeatHandler, rearSeatHandler )
                .then(doorHandler_1, doorHandler_2, doorHandler_3, doorHandler_4);

        // n doorHandlers + bonnetHandler -> |SequenceBarrier| -> paintHandler -> |SequenceBarrier| -> n wheelHandlers
        disruptor.after( bonnetHandler, doorHandler_1, doorHandler_2, doorHandler_3, doorHandler_4 )
                .then(paintHandler)
                .then( wheel_1_handler, wheel_2_handler, wheel_3_handler, wheel_4_handler );
    }
}
