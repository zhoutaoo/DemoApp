package parkingLot;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseStrategy implements Strategy {
    protected Map<String, ParkingLot> parkingLots;

    public BaseStrategy(ParkingLot... parkingLots) {
        this.parkingLots = new HashMap<String, ParkingLot>();
        for (ParkingLot parkingLot : parkingLots) {
            this.parkingLots.put(parkingLot.toString(), parkingLot);
        }
    }

    @Override
    public abstract ParkingLot findWorkabilityParkingLot();

    @Override
    public ParkingLot findParkingLot(Receipt receipt) {
        return this.parkingLots.getOrDefault(receipt.toString(), new EmptyParkingLot());
    }
}
