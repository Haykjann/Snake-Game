import java.util.Scanner;
import java.io.IOException;

public class SnakeGameDemo {
    public static void main(String[] args) throws InterruptedException {
        int width = 20, height = 10, initialSnakeLength = 4;
        Action game = new Action(width, height, initialSnakeLength);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Snake Game");
        System.out.println("Controls: W = UP, S = DOWN, A = LEFT, D = RIGHT");
        System.out.println("Please remember to press ENTER after every input");
        System.out.println("Press ENTER to start the game...");
        scanner.nextLine();

        game.printBoard();

        while (!game.isGameOver()) {
            long start = System.currentTimeMillis();
            while (System.currentTimeMillis() - start < 300 && !game.isGameOver()) {
                try {
                    if (System.in.available() > 0) {
                        String line = scanner.nextLine().trim();
                        if (!line.isEmpty()) {
                            char input = Character.toUpperCase(line.charAt(0));
                            if (input == 'W') game.setDirection("UP");
                            else if (input == 'S') game.setDirection("DOWN");
                            else if (input == 'A') game.setDirection("LEFT");
                            else if (input == 'D') game.setDirection("RIGHT");
                        }
                    }
                } catch (IOException e) {
                }
                Thread.sleep(10);
            }
            System.out.print("\033[H\033[2J");
            System.out.flush();
            game.update();
            game.printBoard();
        }

        System.out.println("Game Over! Final Score: " + game.getScore());
        scanner.close();
    }

}
