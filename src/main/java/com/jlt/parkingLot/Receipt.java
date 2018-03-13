package parkingLot;

/**
 * Created by zhoutaoo on 11/14/14.
 */
public class Receipt {
    private String parkingLotId;

    public Receipt(String parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    @Override
    public String toString() {
        return this.parkingLotId;
    }
}
