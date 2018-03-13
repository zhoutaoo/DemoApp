package parkingLot;

import java.util.Iterator;
import java.util.Map;

public class SimpleStrategy extends BaseStrategy {

    public SimpleStrategy(ParkingLot... parkingLots) {
        super(parkingLots);
    }

    @Override
    public ParkingLot findWorkabilityParkingLot() {
        Iterator<Map.Entry<String, ParkingLot>> iterator = this.parkingLots.entrySet().iterator();
        ParkingLot parkingLot = new EmptyParkingLot();
        while (iterator.hasNext() && parkingLot.isFull()) {
            ParkingLot p = iterator.next().getValue();
            parkingLot = p.isFull() ? parkingLot : p;
        }
        return parkingLot;
    }
}
