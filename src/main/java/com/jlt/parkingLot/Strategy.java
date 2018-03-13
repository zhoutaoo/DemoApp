package parkingLot;

public interface Strategy {

    ParkingLot findWorkabilityParkingLot();

    ParkingLot findParkingLot(Receipt receipt) ;
}
