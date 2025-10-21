import java.awt.Point;
import java.util.Random;

public class Food {
    private final Point position;
    private final Random random = new Random();

    public Food(int x, int y) {
        position = new Point(x, y);
    }

    public Point getPosition() { return position; }

    public void spawn(int width, int height, Snake snake) {
        int x, y;
        do {
            x = random.nextInt(width);
            y = random.nextInt(height);
        } while (snake.getBody().contains(new Point(x, y))); // avoid spawn on snake
        position.setLocation(x, y);
    }
}
