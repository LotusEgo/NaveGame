import javax.swing.*;
import java.awt.*;

public class Credits {
    private JFrame frame;

    public Credits() {
        frame = new JFrame("Créditos");
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Define o layout vertical
        panel.setAlignmentX(Component.CENTER_ALIGNMENT); // Centraliza o painel

        JLabel programmingLabel = new JLabel("Programação: Mayan, Costa, Fusca.");
        JLabel musicLabel = new JLabel("Música: Judiscleibe");

        // Centraliza as etiquetas
        programmingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        musicLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Adiciona um espaço vertical entre as linhas
        panel.add(Box.createVerticalGlue()); // Adiciona espaço flexível antes das etiquetas
        panel.add(programmingLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Espaço fixo de 20 pixels
        panel.add(musicLabel);
        panel.add(Box.createVerticalGlue()); // Adiciona espaço flexível após as etiquetas

        frame.add(panel);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    public void show() {
        frame.setVisible(true);
    }
}
