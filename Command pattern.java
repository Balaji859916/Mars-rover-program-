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
