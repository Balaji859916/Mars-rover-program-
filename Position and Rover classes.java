# Position and Rover classes 
public class Position {
    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

public class Rover {
    private Position position;
    private Direction direction;
    private final Set<Position> obstacles;
    private final int gridWidth;
    private final int gridHeight;

    public Rover(int x, int y, Direction direction, Set<Position> obstacles, int gridWidth, int gridHeight) {
        this.position = new Position(x, y);
        this.direction = direction;
        this.obstacles = obstacles;
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
    }

    public void move() {
        Position newPosition = direction.moveForward(position);
        if (isValidPosition(newPosition) && !obstacles.contains(newPosition)) {
            position = newPosition;
        }
    }

    public void turnLeft() {
        direction = direction.turnLeft();
    }

    public void turnRight() {
        direction = direction.turnRight();
    }

    private boolean isValidPosition(Position position) {
        return position.getX() >= 0 && position.getX() < gridWidth && position.getY() >= 0 && position.getY() < gridHeight;
    }

    public String getStatus() {
        return String.format("Rover is at (%d, %d) facing %s.", position.getX(), position.getY(), direction.getDirection());
    }
}
