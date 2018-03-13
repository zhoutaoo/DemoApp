package parkingLot;

public class EmptyParkingLot extends ParkingLot {
    public EmptyParkingLot() {
        super(0);
    }

    @Override
    public boolean isAvailable() {
        return false;
    }

    @Override
    public boolean isFull() {
        return true;
    }
}
