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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Chassis{");
        sb.append("driver=").append(isDone(driver));
        sb.append(", passenger=").append(isDone(passenger));
        sb.append(", rear=").append(isDone(rear));
        sb.append(", frontLeftDoor=").append(isDone(frontLeftDoor));
        sb.append(", frontRightDoor=").append(isDone(frontRightDoor));
        sb.append(", backLeftDoor=").append(isDone(backLeftDoor));
        sb.append(", backRightDoor=").append(isDone(backRightDoor));
        sb.append(", engine=").append(isDone(engine));
        sb.append(", bonnet=").append(isDone(bonnet));
        sb.append(", painted=").append(isDone(painted));
        sb.append(", frontLeftWheel=").append(isDone(frontLeftWheel));
        sb.append(", frontRightWheel=").append(isDone(frontRightWheel));
        sb.append(", backLeftWheel=").append(isDone(backLeftWheel));
        sb.append(", backRightWheel=").append(isDone(backRightWheel));
        sb.append('}');
        return sb.toString();
    }

    private boolean isDone(Object o ) { return o != null; }

}
