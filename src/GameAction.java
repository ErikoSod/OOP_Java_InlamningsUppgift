import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class GameAction implements ActionListener {

    private final JPanel gamePanel;
    private final GameLogic gameLogic;

    public GameAction(JPanel gamePanel, GameLogic gameLogic) {
        this.gamePanel = gamePanel;
        this.gameLogic = gameLogic;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

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
                            comp.setEnabled(false);
                        }
                        JOptionPane.showMessageDialog(gamePanel, "Grattis, du vann!");
                    }
                }
            }
        }
    }
}
