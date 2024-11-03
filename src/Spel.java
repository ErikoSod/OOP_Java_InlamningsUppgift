import javax.swing.*;
import java.awt.*;

public class Spel extends JFrame {

    private final JPanel gamePanel = new JPanel();
    private final JPanel buttonPanel = new JPanel();
    private final GameLogic gameLogic = new GameLogic();
    private final GameAction gameAction;
    private final AppearanceSettings settings = new AppearanceSettings();

    public Spel() {
        gameAction = new GameAction(gamePanel, gameLogic);
        setVisible(true);
        setTitle("Spel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        this.add(gamePanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        gamePanel.setLayout(new GridLayout(4, 4));
        buttonCreate(gamePanel);
        pack();
        gameLogic.buttonRandomize(gamePanel);
    }

    public void buttonCreate(JPanel component) {

        for (int i = 1; i < 16; i++) {
            component.add(new JButton(String.valueOf(i)));
        }
        for (Component c : gamePanel.getComponents()) {
            ((JButton)c).addActionListener(gameAction);
        }
        JButton buttonEmpty = new JButton("0");
        buttonEmpty.addActionListener(gameAction);
        buttonEmpty.setVisible(false);
        component.add(buttonEmpty);

        JButton newGameButton = new JButton("Nytt spel");
        buttonPanel.add(newGameButton);
        newGameButton.addActionListener(_ -> {
            for (Component comp : gamePanel.getComponents()) {
                comp.setEnabled(true);
            }
            gameLogic.buttonRandomize(gamePanel);
        });

        JButton colorSettings = new JButton("Färginställningar");
        buttonPanel.add(colorSettings);
        colorSettings.addActionListener(_ -> settings.colorChange(gamePanel));
    }

    public static void main(String[] args) {
        new Spel();
    }
}