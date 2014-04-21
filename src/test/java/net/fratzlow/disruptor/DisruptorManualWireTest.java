package net.fratzlow.disruptor;

import com.lmax.disruptor.BatchEventProcessor;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SequenceBarrier;
import com.lmax.disruptor.SleepingWaitStrategy;
import net.fratzlow.disruptor.model.Chassis;
import net.fratzlow.disruptor.tech.*;
import org.junit.Test;

/**
 * Created by frank on 20/02/14.
 */
public class DisruptorManualWireTest {
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
    public void testPureWiring() {
        RingBuffer<Chassis> ringBuffer =
                RingBuffer.createSingleProducer(new ChassisEventFactory(), 32, new SleepingWaitStrategy());

        SequenceBarrier ringBufferBarrier = ringBuffer.newBarrier();

        //--------------------------------------------------------------------------------------------------------------
        // 1st stage
        //--------------------------------------------------------------------------------------------------------------
        BatchEventProcessor<Chassis> engineProcessor =
                new BatchEventProcessor<Chassis>(ringBuffer,ringBufferBarrier, engineHandler);

        BatchEventProcessor<Chassis> driverSeatProcessor =
                new BatchEventProcessor<Chassis>(ringBuffer,ringBufferBarrier, driverSeatHandler);
        BatchEventProcessor<Chassis> passengerSeatProcessor =
                new BatchEventProcessor<Chassis>(ringBuffer,ringBufferBarrier, passengerSeatHandler);
        BatchEventProcessor<Chassis> rearSeatProcessor =
                new BatchEventProcessor<Chassis>(ringBuffer,ringBufferBarrier, rearSeatHandler);

        //--------------------------------------------------------------------------------------------------------------
        // 2nd stage
        //--------------------------------------------------------------------------------------------------------------

        // 2a) after engine has finished go to bonnet
        SequenceBarrier engineBarrier = ringBuffer.newBarrier(engineProcessor.getSequence());

        BatchEventProcessor<Chassis> bonnetProcessor =
                new BatchEventProcessor<Chassis>(ringBuffer,engineBarrier, bonnetHandler);

        // 2b) after the the seats assemble doors
        SequenceBarrier seatsBarrier = ringBuffer.newBarrier(
                driverSeatProcessor.getSequence(),
                passengerSeatProcessor.getSequence(),
                rearSeatProcessor.getSequence()
        );

        BatchEventProcessor<Chassis> doorProcessor_1 =
                new BatchEventProcessor<Chassis>(ringBuffer,seatsBarrier, doorHandler_1);
        BatchEventProcessor<Chassis> doorProcessor_2 =
                new BatchEventProcessor<Chassis>(ringBuffer,seatsBarrier, doorHandler_2);
        BatchEventProcessor<Chassis> doorProcessor_3 =
                new BatchEventProcessor<Chassis>(ringBuffer,seatsBarrier, doorHandler_3);
        BatchEventProcessor<Chassis> doorProcessor_4 =
                new BatchEventProcessor<Chassis>(ringBuffer,seatsBarrier, doorHandler_4);

        //--------------------------------------------------------------------------------------------------------------
        // 3rd stage
        //--------------------------------------------------------------------------------------------------------------

        // 2b) after the the seats assemble doors
        SequenceBarrier secondStageBarrier = ringBuffer.newBarrier(
                bonnetProcessor.getSequence(),
                doorProcessor_1.getSequence(),
                doorProcessor_2.getSequence(),
                doorProcessor_3.getSequence(),
                doorProcessor_4.getSequence()
        );

        BatchEventProcessor<Chassis> paintProcessor =
                new BatchEventProcessor<Chassis>(ringBuffer,secondStageBarrier, paintHandler);

        //--------------------------------------------------------------------------------------------------------------
        // 5th stage
        //--------------------------------------------------------------------------------------------------------------

        SequenceBarrier paintBarrier = ringBuffer.newBarrier(paintProcessor.getSequence());

        BatchEventProcessor<Chassis> wheelProcessor_1 =
                new BatchEventProcessor<Chassis>(ringBuffer,paintBarrier, wheel_1_handler);
        BatchEventProcessor<Chassis> wheelProcessor_2 =
                new BatchEventProcessor<Chassis>(ringBuffer,paintBarrier, wheel_2_handler);
        BatchEventProcessor<Chassis> wheelProcessor_3 =
                new BatchEventProcessor<Chassis>(ringBuffer,paintBarrier, wheel_3_handler);
        BatchEventProcessor<Chassis> wheelProcessor_4 =
                new BatchEventProcessor<Chassis>(ringBuffer,paintBarrier, wheel_4_handler);


    }

}
