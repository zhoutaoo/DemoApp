package parkingLot;

import java.util.Iterator;
import java.util.Map;

public class SmartStrategy extends BaseStrategy {

    public SmartStrategy(ParkingLot... parkingLots) {
        super(parkingLots);
    }

    @Override
    public ParkingLot findWorkabilityParkingLot() {
        Iterator<Map.Entry<String, ParkingLot>> iterator = this.parkingLots.entrySet().iterator();
        ParkingLot parkingLot = new EmptyParkingLot();
        while (iterator.hasNext()) {
            ParkingLot p = iterator.next().getValue();
            parkingLot = p.remainder() > parkingLot.remainder() ? p : parkingLot;
        }
        return parkingLot;
    }
}
