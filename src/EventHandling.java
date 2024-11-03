import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class EventHandling implements ActionListener {

    private final JPanel gamePanel;
    private final GameLogic gameLogic;
    //private final AppearanceSettings settings = new AppearanceSettings();

    public EventHandling(JPanel gamePanel, GameLogic gameLogic) {
        this.gamePanel = gamePanel;
        this.gameLogic = gameLogic;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (((JButton) e.getSource()).getText().equals("Nytt spel")) {
            for (Component comp : gamePanel.getComponents()) {
                comp.setBackground(null);
                comp.setForeground(null);
                comp.setEnabled(true);
            }
            gameLogic.buttonRandomize(gamePanel);
        }
/*
        if (((JButton) e.getSource()).getText().equals("Färginställningar")) {
            settings.colorChange(gamePanel);
        }

 */

        for (Component clickedButton : gamePanel.getComponents()) {

            if (e.getSource() == clickedButton) {
                int index = Arrays.asList(gamePanel.getComponents()).indexOf(clickedButton);

                if (gameLogic.clickableButton(index)) {
                    gamePanel.getComponent(gameLogic.getEmptyButtonIndex()).setVisible(true);
                    ((JButton) gamePanel.getComponent(gameLogic.getEmptyButtonIndex())).setText(((JButton) clickedButton).getText());
                    clickedButton.setVisible(false);
                    ((JButton) clickedButton).setText("0");
                    gameLogic.setEmptyButtonIndex(index);

                    if (gameLogic.checkGameComplete(gamePanel)) {

                        for (Component comp : gamePanel.getComponents()) {
                            comp.setBackground(Color.GREEN);
                            comp.setEnabled(false);
                        }
                        JOptionPane.showMessageDialog(gamePanel, "Grattis, du vann!");
                    }
                }
            }
        }
    }
}
