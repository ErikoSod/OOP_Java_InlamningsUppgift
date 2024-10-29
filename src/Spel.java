import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Spel extends JFrame {

    JPanel gamePanel = new JPanel();
    GameLogic gameLogic = new GameLogic();
    EventHandling eventHandling;

    public Spel() {
        this.eventHandling = new EventHandling(gamePanel, gameLogic);
        this.setVisible(true);
        this.setTitle("Spel");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(gamePanel, BorderLayout.CENTER);
        gamePanel.setLayout(new GridLayout(4, 4));
        buttonCreate(gamePanel);
        this.setLocationRelativeTo(null);
        pack();
        buttonRandomize(gamePanel);
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
    }

    public void buttonRandomize(JPanel comp)  {
        int i = 0;
        while(i<=100) {
            ((JButton) comp.getComponent(randomClickableButton())).doClick(1);
            i++;
        }
    }

    public int randomClickableButton () {
        int i;
        do {
             i = (int) (Math.random()*15);
        }
        while (!gameLogic.clickableButton(i));
        return i;
    }

    public static void main(String[] args) {
        Spel spel = new Spel();
    }
}