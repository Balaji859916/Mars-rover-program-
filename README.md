# Mars-rover-program
# Direction Interface and Implementations
public interface Direction {
    Direction turnLeft();
    Direction turnRight();
    Position moveForward(Position position);
    char getDirection();
}

public class North implements Direction {
    @Override
    public Direction turnLeft() {
        return new West();
    }

    @Override
    public Direction turnRight() {
        return new East();
    }

    @Override
    public Position moveForward(Position position) {
        return new Position(position.getX(), position.getY() + 1);
    }

    @Override
    public char getDirection() {
        return 'N';
    }
}

public class South implements Direction {
    @Override
    public Direction turnLeft() {
        return new East();
    }

    @Override
    public Direction turnRight() {
        return new West();
    }

    @Override
    public Position moveForward(Position position) {
        return new Position(position.getX(), position.getY() - 1);
    }

    @Override
    public char getDirection() {
        return 'S';
    }
}

public class East implements Direction {
    @Override
    public Direction turnLeft() {
        return new North();
    }

    @Override
    public Direction turnRight() {
        return new South();
    }

    @Override
    public Position moveForward(Position position) {
        return new Position(position.getX() + 1, position.getY());
    }

    @Override
    public char getDirection() {
        return 'E';
    }
}

public class West implements Direction {
    @Override
    public Direction turnLeft() {
        return new South();
    }

    @Override
    public Direction turnRight() {
        return new North();
    }

    @Override
    public Position moveForward(Position position) {
        return new Position(position.getX() - 1, position.getY());
    }

    @Override
    public char getDirection() {
        return 'W';
    }
}
# command pattern 
public interface Command {
    void execute(Rover rover);
}

public class MoveCommand implements Command {
    @Override
    public void execute(Rover rover) {
        rover.move();
    }
}

public class TurnLeftCommand implements Command {
    @Override
    public void execute(Rover rover) {
        rover.turnLeft();
    }
}

public class TurnRightCommand implements Command {
    @Override
    public void execute(Rover rover) {
        rover.turnRight();
    }
}
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
# Main Application 
import java.util.*;

public class MarsRoverSimulation {
    private static final Logger logger = Logger.getLogger(MarsRoverSimulation.class.getName());

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            Set<Position> obstacles = new HashSet<>();
            obstacles.add(new Position(2, 2));
            obstacles.add(new Position(3, 5));

            Rover rover = new Rover(0, 0, new North(), obstacles, 10, 10);
            Map<Character, Command> commands = new HashMap<>();
            commands.put('M', new MoveCommand());
            commands.put('L', new TurnLeftCommand());
            commands.put('R', new TurnRightCommand());

            while (true) {
                System.out.println("Enter commands (M, L, R) or Q to quit:");
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("Q")) break;

                for (char c : input.toCharArray()) {
                    Command command = commands.get(c);
                    if (command != null) {
                        command.execute(rover);
                    } else {
                        logger.warning("Invalid command: " + c);
                    }
                }
                System.out.println(rover.getStatus());
            }
        } catch (Exception e) {
            logger.severe("An error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
