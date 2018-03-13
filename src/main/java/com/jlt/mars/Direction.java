package mars;

/**
 * Created by zhoutaoo on 7/11/14.
 */
public enum Direction {
    W, S, E, N;


    public static Direction indexOf(int index) {
        for (Direction direction : Direction.values())
            if (direction.ordinal() == index)
                return direction;
        return null;
    }

}
