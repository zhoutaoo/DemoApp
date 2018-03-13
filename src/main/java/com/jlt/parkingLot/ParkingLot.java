package parkingLot;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private int capacity;
    private Map<Receipt, Car> parkingSpaces = new HashMap<Receipt, Car>();

    public ParkingLot(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("capacity is not less than zero");
        }
        this.capacity = capacity;
    }

    public Receipt park(Car car) {
        if (isFull())
            return null;
        Receipt receipt = new Receipt(this.toString());
        this.parkingSpaces.put(receipt, car);
        return receipt;
    }

    public Car take(Receipt receipt) {
        return this.parkingSpaces.remove(receipt);
    }

    public boolean isFull() {
        return this.parkingSpaces.size() >= this.capacity;
    }

    public boolean isAvailable() {
        return true;
    }

    public float rate() {
        float rate = (float) this.parkingSpaces.size() / this.capacity;
        float result = new BigDecimal(rate).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
        return result;
    }

    public float remainder() {
        return this.capacity - this.parkingSpaces.size();
    }
}
