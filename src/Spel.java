import javax.swing.*;
import java.awt.*;

public class Spel extends JFrame {

    private final JPanel gamePanel = new JPanel();
    private final JPanel buttonPanel = new JPanel();
    private final GameLogic gameLogic = new GameLogic();
    private final EventHandling eventHandling;
    private final AppearanceSettings settings = new AppearanceSettings();

    public Spel() {
        this.eventHandling = new EventHandling(gamePanel, gameLogic);
        this.setVisible(true);
        this.setTitle("Spel");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(gamePanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        gamePanel.setLayout(new GridLayout(4, 4));
        buttonCreate(gamePanel);
        this.setLocationRelativeTo(null);
        pack();
        newGame();
    }

    public void buttonCreate(JPanel comp) {

        for (int i = 1; i < 16; i++) {
            comp.add(new JButton(String.valueOf(i)));
        }
        for (Component c : gamePanel.getComponents()) {
            ((JButton)c).addActionListener(eventHandling);
        }
        JButton buttonEmpty = new JButton("0");
        buttonEmpty.addActionListener(eventHandling);
        buttonEmpty.setVisible(false);
        comp.add(buttonEmpty);

        JButton newGameButton = new JButton("Nytt spel");
        buttonPanel.add(newGameButton);
        newGameButton.addActionListener(eventHandling);

        JButton colorSettings = new JButton("Färginställningar");
        buttonPanel.add(colorSettings);
        colorSettings.addActionListener(e -> {settings.colorChange(gamePanel);});
    }

    public void newGame() {
        gameLogic.buttonRandomize(gamePanel);
    }

    public static void main(String[] args) {
        Spel spel = new Spel();
    }
}