package net.fratzlow.disruptor.model;

/**
 * Created by frank on 10/02/14.
 */
public class Chassis {

    public Seat driver, passenger, rear;
    public Door frontLeftDoor, frontRightDoor, backLeftDoor, backRightDoor;
    public Engine engine;
    public Bonnet bonnet;
    public boolean painted = false;
    public Wheel frontLeftWheel, frontRightWheel, backLeftWheel, backRightWheel;

    public void reset() {
        driver = passenger = rear = null;
        frontLeftDoor = frontRightDoor = backRightDoor = backLeftDoor = null;
        engine = null;
        bonnet = null;
        painted = false;
        frontLeftWheel = frontRightWheel = backLeftWheel = backRightWheel = null;
    }
}
