import java.awt.Point;
import java.util.LinkedList;

public class Snake {
    private final LinkedList<Point> body = new LinkedList<>();

    public Snake(int startX, int startY, int initialLength) {
        for (int i = 0; i < initialLength; i++) {
            body.add(new Point(startX + i, startY));
        }
    }

    public Point getHead() { return body.getFirst(); }
    public Point getTail() { return body.getLast(); }
    public LinkedList<Point> getBody() { return body; }

    public void move(boolean grow, int x, int y) {
        body.addLast(new Point(x, y));
        if (!grow) body.removeFirst();
    }

    public boolean hasCollided(int width, int height) {
        Point tail = getTail();
        if (tail.x < 0 || tail.y < 0 || tail.x >= width || tail.y >= height) return true;
        for (int i = 0; i < body.size() - 1; i++)
            if (tail.equals(body.get(i))) return true;
        return false;
    }
}
