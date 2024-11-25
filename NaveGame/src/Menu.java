import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {
    private JFrame frame;
    private JPanel panel;
    private JButton startButton, optionsButton, creditsButton;
    private Player player;

    public Menu(Player player) {
        this.player = player;
        frame = new JFrame("Menu Principal");
        panel = new JPanel();
        startButton = new JButton("Iniciar");
        optionsButton = new JButton("Opções");
        creditsButton = new JButton("Créditos");

        panel.add(startButton);
        panel.add(optionsButton);
        panel.add(creditsButton);
        frame.add(panel);

        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Game game = new Game(player);
                game.start();
            }
        });

        optionsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Options options = new Options(player);
                options.show();
            }
        });

        creditsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Credits credits = new Credits();
                credits.show();
            }
        });
    }

    public void show() {
        frame.setVisible(true);
    }
}
