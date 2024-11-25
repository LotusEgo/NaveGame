import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Options {
    private JFrame frame;
    private Player player;

    public Options(Player player) {
        this.player = player;
        frame = new JFrame("Opções");
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel volumeLabel = new JLabel("Volume:");
        JSlider volumeSlider = new JSlider(0, 100, 50);
        panel.add(volumeLabel);
        panel.add(volumeSlider);

        JLabel controlLabel = new JLabel("Controles:");
        JLabel leftLabel = new JLabel("Esquerda: " + KeyEvent.getKeyText(player.getLeftKey()));
        JLabel rightLabel = new JLabel("Direita: " + KeyEvent.getKeyText(player.getRightKey()));
        JLabel shootLabel = new JLabel("Atirar: " + KeyEvent.getKeyText(player.getShootKey()));

        JButton leftButton = new JButton("Modificar Esquerda");
        JButton rightButton = new JButton("Modificar Direita");
        JButton shootButton = new JButton("Modificar Atirar");

        leftButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                captureKey("Esquerda", leftLabel, new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        if (!isKeyInUse(e.getKeyCode())) {
                            player.setLeftKey(e.getKeyCode());
                            leftLabel.setText("Esquerda: " + KeyEvent.getKeyText(e.getKeyCode()));
                            frame.removeKeyListener(this);
                        } else {
                            JOptionPane.showMessageDialog(frame, "Tecla já está em uso!");
                        }
                    }
                });
            }
        });

        rightButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                captureKey("Direita", rightLabel, new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        if (!isKeyInUse(e.getKeyCode())) {
                            player.setRightKey(e.getKeyCode());
                            rightLabel.setText("Direita: " + KeyEvent.getKeyText(e.getKeyCode()));
                            frame.removeKeyListener(this);
                        } else {
                            JOptionPane.showMessageDialog(frame, "Tecla já está em uso!");
                        }
                    }
                });
            }
        });

        shootButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                captureKey("Atirar", shootLabel, new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        if (!isKeyInUse(e.getKeyCode())) {
                            player.setShootKey(e.getKeyCode());
                            shootLabel.setText("Atirar: " + KeyEvent.getKeyText(e.getKeyCode()));
                            frame.removeKeyListener(this);
                        } else {
                            JOptionPane.showMessageDialog(frame, "Tecla já está em uso!");
                        }
                    }
                });
            }
        });

        JLabel speedLabel = new JLabel("Velocidade do Jogador:");
        JSlider speedSlider = new JSlider(1, 20, player.getSpeed()); // Limites de velocidade de 1 a 20
        speedSlider.addChangeListener(e -> player.setSpeed(speedSlider.getValue()));

        panel.add(controlLabel);
        panel.add(leftLabel);
        panel.add(leftButton);
        panel.add(rightLabel);
        panel.add(rightButton);
        panel.add(shootLabel);
        panel.add(shootButton);
        panel.add(speedLabel);
        panel.add(speedSlider);

        frame.add(panel);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    public void show() {
        frame.setVisible(true);
    }

    private boolean isKeyInUse(int keyCode) {
        return keyCode == player.getLeftKey() || keyCode == player.getRightKey() || keyCode == player.getShootKey();
    }

    private void captureKey(String action, JLabel label, KeyAdapter keyAdapter) {
        JOptionPane.showMessageDialog(frame, "Pressione a nova tecla para " + action);
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                keyAdapter.keyPressed(e);
                if (!isKeyInUse(e.getKeyCode())) {
                    frame.removeKeyListener(this);
                    label.setText(action + ": " + KeyEvent.getKeyText(e.getKeyCode()));
                } else {
                    JOptionPane.showMessageDialog(frame, "Tecla já está em uso!");
                }
            }
        });
    }
}
