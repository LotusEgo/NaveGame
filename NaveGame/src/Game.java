import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class Game extends JPanel implements ActionListener, KeyListener {
    private Timer timer;
    private ArrayList<Enemy> enemies;
    private ArrayList<Projectile> projectiles;
    private Player player;
    private Music gameMusic;
    private int score;
    private boolean gameRunning;
    private boolean gamePaused;

    public Game(Player player) {
        this.player = player;
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);

        timer = new Timer(20, this);
        enemies = new ArrayList<>();
        projectiles = new ArrayList<>();
        gameMusic = new Music("assets/music/game_music.mp3");
        score = 0;
        gameRunning = true;
        gamePaused = false;

        gameMusic.play();
        timer.start();
    }

    public void start() {
        JFrame frame = new JFrame("Jogo de Nave");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameRunning && !gamePaused) {
            spawnEnemies();
            updateGame();
            checkCollisions();
            checkVictoryCondition();  // Verifica a condição de vitória
            repaint();
        }
    }

    private void spawnEnemies() {
        if (new Random().nextInt(50) == 0) {
            enemies.add(new Enemy());
        }
    }

    private void updateGame() {
        for (Enemy enemy : enemies) {
            enemy.move();
        }
        for (Projectile projectile : projectiles) {
            projectile.move();
        }
        player.move();
    }

    private void checkCollisions() {
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            if (enemy.getBounds().intersects(player.getBounds())) {
                gameOver();
            }
            if (enemy.getY() > getHeight()) {
                gameOver();
            }

            for (int j = 0; j < projectiles.size(); j++) {
                Projectile projectile = projectiles.get(j);
                if (projectile.getBounds().intersects(enemy.getBounds())) {
                    enemies.remove(i);
                    projectiles.remove(j);
                    score += 100;  // Aumenta a pontuação
                    break;
                }
            }
        }
    }

    private void checkVictoryCondition() {
        if (score >= 5000) {
            victory();
        }
    }

    private void gameOver() {
        gameRunning = false;
        gameMusic.stop();
        GameOver gameOver = new GameOver(score, player);
        gameOver.show();
    }

    private void victory() {
        gameRunning = false;
        gameMusic.stop();
        Victory victory = new Victory(score, player);
        victory.show();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Enemy enemy : enemies) {
            enemy.draw(g);
        }
        for (Projectile projectile : projectiles) {
            projectile.draw(g);
        }
        player.draw(g);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 24)); // Define a fonte com tamanho 24
        g.drawString("Score: " + score, 10, 30); // Ajusta a posição do texto
        
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 16)); // Define a fonte com tamanho 24
        g.drawString("Pausar: P", 715, 25); // Ajusta a posição do texto
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        player.keyPressed(e);
        if (e.getKeyCode() == player.getShootKey() && player.canShoot()) {
            projectiles.add(new Projectile(player.getX(), player.getY()));
        }
        if (e.getKeyCode() == KeyEvent.VK_P) {
            gamePaused = !gamePaused;
            if (gamePaused) {
                gameMusic.stop();
            } else {
                gameMusic.play();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        player.keyReleased(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Não utilizado
    }
}
