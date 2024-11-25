import java.awt.*;
import java.util.Random;

public class Enemy {
    private int x, y;
    private int speed;
    private final int WIDTH = 30;
    private final int HEIGHT = 30;
    private Color color;

    public Enemy() {
        this.x = new Random().nextInt(750);
        this.y = 0;
        this.speed = new Random().nextInt(5) + 1;
        this.color = Color.YELLOW; // Define a cor amarela
    }

    public void move() {
        this.y += speed;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, WIDTH, HEIGHT); // Desenha o retângulo amarelo
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

    public int getY() {
        return y;
    }
}
