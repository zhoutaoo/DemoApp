package parkingLot;

public class ParkingBuddy {
    private Strategy strategy;

    private ParkingBuddy(Strategy strategy) {
        this.strategy = strategy;
    }

    public static ParkingBuddy createSimpleParkingBuddy(ParkingLot... parkingLots) {
        return new ParkingBuddy(new SimpleStrategy(parkingLots));
    }

    public static ParkingBuddy createSmartParkingBuddy(ParkingLot... parkingLots) {
        return new ParkingBuddy(new SmartStrategy(parkingLots));
    }

    public Receipt park(Car car) {
        return this.strategy.findWorkabilityParkingLot().park(car);
    }

    public Car take(Receipt receipt) {
        return this.strategy.findParkingLot(receipt).take(receipt);
    }

}
