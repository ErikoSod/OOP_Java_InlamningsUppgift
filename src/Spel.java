import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Spel extends JFrame implements ActionListener {

    JPanel gamePanel = new JPanel();
    int emptyButtonIdex = 15;

    public Spel() {
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
            ((JButton)c).addActionListener(this);
        }
        JButton buttonEmpty = new JButton("0");
        buttonEmpty.addActionListener(this);
        buttonEmpty.setVisible(false);
        comp.add(buttonEmpty);
    }

    public void buttonRandomize(JPanel comp)  {
        int i = 0;
        while(i<=1000) {
            ((JButton) comp.getComponent(randomClickableButton())).doClick(1);
            i++;
        }
    }

    public boolean checkGameComplete(JPanel comp) {

        boolean complete = false;
        for (int i = 0; i < 15; i++) {
            if(((JButton) comp.getComponent(i)).getText().equals(String.valueOf(i+1))){
                complete = true;
            }
            else {
                complete = false;
                break;
            }
        }
        return complete;
    }
    public boolean clickableButton(int index) {
        int above = emptyButtonIdex-4;
        int below = emptyButtonIdex+4;
        int rightOf = emptyButtonIdex+1;
        int leftOf = emptyButtonIdex-1;

        if(emptyButtonIdex ==3||emptyButtonIdex ==7||emptyButtonIdex ==11) {
            return index == above || index == below || index == leftOf;
        }
        if(emptyButtonIdex ==4||emptyButtonIdex ==8||emptyButtonIdex ==12){
            return index == above || index == below || index == rightOf;
        }
        return index == above || index == below || index == rightOf || index == leftOf;
    }

    public int randomClickableButton () {
        int i;
        do {
             i = (int) (Math.random()*15);
        }
        while (!clickableButton(i));
        return i;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for (Component clickedButton : gamePanel.getComponents()) {

            if (e.getSource() == clickedButton) {

                int index = Arrays.asList(gamePanel.getComponents()).indexOf(clickedButton);

                if (clickableButton(index)) {

                    gamePanel.getComponent(emptyButtonIdex).setVisible(true);
                    ((JButton) gamePanel.getComponent(emptyButtonIdex)).setText(((JButton) clickedButton).getText());
                    clickedButton.setVisible(false);
                    ((JButton) clickedButton).setText("0");
                    emptyButtonIdex = index;

                    if (checkGameComplete(gamePanel)) {

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

    public static void main(String[] args) {
        Spel spel = new Spel();
    }
}