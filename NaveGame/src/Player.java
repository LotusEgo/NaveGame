import java.awt.*;
import java.awt.event.KeyEvent;

public class Player {
    private int x, y;
    private int dx, dy;
    private final int BASE = 30;  // Largura da base do triângulo
    private final int HEIGHT = 30;  // Altura do triângulo
    private int leftKey;
    private int rightKey;
    private int shootKey;
    private long lastShootTime;
    private int speed; // Nova variável de velocidade

    public Player() {
        this.x = 400;
        this.y = 500;
        this.leftKey = KeyEvent.VK_A;
        this.rightKey = KeyEvent.VK_D;
        this.shootKey = KeyEvent.VK_W;
        this.lastShootTime = 0;
        this.speed = 10; // Define a velocidade padrão
    }

    public void move() {
        x += dx;
        y += dy;
    }

    public void draw(Graphics g) {
        int[] xPoints = {x, x - BASE / 2, x + BASE / 2};
        int[] yPoints = {y, y + HEIGHT, y + HEIGHT};
        g.setColor(Color.BLUE);  // Cor azul para o jogador
        g.fillPolygon(xPoints, yPoints, 3);  // Desenha o triângulo
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == leftKey) {
            dx = -speed; // Usa a variável de velocidade
        } else if (key == rightKey) {
            dx = speed; // Usa a variável de velocidade
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == leftKey || key == rightKey) {
            dx = 0;
        }
    }

    public boolean canShoot() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastShootTime >= 500) {  // 0.5 segundos
            lastShootTime = currentTime;
            return true;
        }
        return false;
    }

    public Rectangle getBounds() {
        return new Rectangle(x - BASE / 2, y, BASE, HEIGHT);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setLeftKey(int key) {
        this.leftKey = key;
    }

    public void setRightKey(int key) {
        this.rightKey = key;
    }

    public void setShootKey(int key) {
        this.shootKey = key;
    }

    public int getLeftKey() {
        return leftKey;
    }

    public int getRightKey() {
        return rightKey;
    }

    public int getShootKey() {
        return shootKey;
    }

    // Novo método para definir a velocidade do jogador
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    // Novo método para obter a velocidade do jogador
    public int getSpeed() {
        return speed;
    }
}
