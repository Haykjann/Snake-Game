import java.awt.Point;

public class Action {
    private final Snake snake;
    private final Food food;
    private final int width, height;
    private int score = 0;
    private String direction = "RIGHT", lastDirection = "RIGHT";

    public Action(int width, int height, int initialSnakeLength) {
        this.width = width;
        this.height = height;
        this.snake = new Snake(width/2 - initialSnakeLength/2, height/2, initialSnakeLength);
        this.food = new Food(0, 0);
        this.food.spawn(width, height, snake);
    }

    public void setDirection(String direction) {
        if ((this.lastDirection.equals("UP") && direction.equals("DOWN")) ||
            (this.lastDirection.equals("DOWN") && direction.equals("UP")) ||
            (this.lastDirection.equals("LEFT") && direction.equals("RIGHT")) ||
            (this.lastDirection.equals("RIGHT") && direction.equals("LEFT"))) return;
        this.direction = direction;
    }

    public void update() {
        Point tail = snake.getTail();
        int x = tail.x, y = tail.y;
        if (direction.equals("UP")) y++;
        else if (direction.equals("DOWN")) y--;
        else if (direction.equals("LEFT")) x--;
        else if (direction.equals("RIGHT")) x++;
        boolean ateFood = (x == food.getPosition().x && y == food.getPosition().y);
        snake.move(ateFood, x, y);
        if (ateFood) {
            score++;
            food.spawn(width, height, snake);
        }
        lastDirection = direction;
    }

    public boolean isGameOver() { return snake.hasCollided(width, height); }
    public int getScore() { return score; }

    public void printBoard() {
        char[][] board = new char[width][height];
        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++)
                board[x][y] = '·';
        for (Point p : snake.getBody())
            if (p.x >= 0 && p.x < width && p.y >= 0 && p.y < height)
                board[p.x][p.y] = '■';
        Point f = food.getPosition();
        if (f.x >= 0 && f.x < width && f.y >= 0 && f.y < height)
            board[f.x][f.y] = '●';
        for (int y = height - 1; y >= 0; y--) {
            for (int x = 0; x < width; x++)
                System.out.print(board[x][y] + " ");
            System.out.println();
        }
        System.out.println("Score: " + score);
        System.out.println();
    }
}
