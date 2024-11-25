import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Victory {
    private JFrame frame;
    private JPanel panel;
    private JLabel scoreLabel;
    private JButton menuButton, retryButton;
    private int score;
    private Player player;

    public Victory(int score, Player player) {
        this.score = score;
        this.player = player;

        frame = new JFrame("Vitória");
        panel = new JPanel();
        scoreLabel = new JLabel("Pontuação: " + score);
        menuButton = new JButton("Menu Principal");
        retryButton = new JButton("Jogar Novamente");

        panel.add(scoreLabel);
        panel.add(menuButton);
        panel.add(retryButton);
        frame.add(panel);

        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        menuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Menu menu = new Menu(player);
                menu.show();
            }
        });

        retryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Game game = new Game(player);
                game.start();
            }
        });
    }

    public void show() {
        frame.setVisible(true);
    }
}
