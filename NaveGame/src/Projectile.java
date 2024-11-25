import java.awt.*;

public class Projectile {
    private int x, y;
    private int speed;
    private final int WIDTH = 5;
    private final int HEIGHT = 15;

    public Projectile(int x, int y) {
        this.x = x;
        this.y = y;
        this.speed = 10;
    }

    public void move() {
        this.y -= speed;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);  // Cor branca para o projétil
        g.fillRect(x, y, WIDTH, HEIGHT);  // Desenha o retângulo branco
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

    public int getY() {
        return y;
    }
}
