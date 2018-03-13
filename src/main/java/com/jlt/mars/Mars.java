package mars;

public class Mars {
    private Point point;

    public Mars(Point point) {
        this.point = point;
    }

    public String coordinates() {
        return point.toString();
    }

    public void move(String commands) {
        for (String command : commands.split("")) {
            moveOneCommand(command);
        }
    }

    private void moveOneCommand(String command) {
        point = turn(Around.codeOf(command));
        moves(command);
    }

    private Point turn(Around around) {
        return point.turn(around);
    }

    private void moves(String command) {
        if (!"M".equals(command)) return;
        if (point.isSameDirection(Direction.E)) point.xMove(1);
        if (point.isSameDirection(Direction.W)) point.xMove(-1);
        if (point.isSameDirection(Direction.N)) point.yMove(1);
        if (point.isSameDirection(Direction.S)) point.yMove(-1);
    }
}
