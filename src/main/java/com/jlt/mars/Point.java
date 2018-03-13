package mars;

public class Point {
    private int x;
    private int y;
    private Direction direction;

    public Point(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public Point xMove(int offset) {
        x = x + offset;
        return this;
    }

    public Point yMove(int offset) {
        y = y + offset;
        return this;
    }

    public boolean isSameDirection(Direction direction) {
        return direction == this.direction;
    }

    public Point turn(Around around) {
        Direction afterDirection = Direction.indexOf((4 + direction.ordinal() + around.index()) % 4);
        return new Point(this.x, this.y, afterDirection);
    }

    @Override
    public String toString() {
        return x + " " + y + " " + direction;
    }

}
