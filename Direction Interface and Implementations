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
