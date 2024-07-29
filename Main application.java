# Main application 
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
