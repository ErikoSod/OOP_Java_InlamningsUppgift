import javax.swing.*;
import java.awt.*;

public class AppearanceSettings {

    public void colorChange(JPanel gamePanel) {

        String[] options = {"Bakgrundsfärg", "Textfärg", "Avbryt"};
        String[] colors = {"Röd", "Grön", "Blå", "Svart", "Vit"};

        int userChoice = JOptionPane.showOptionDialog(null, "Vad vill du ändra?",
                "Färginställningar", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, null);

        if (userChoice == 0 || userChoice == 1) {
            String colorChoice = showColorChoiceDialog(colors, userChoice == 0 ? "Bakgrund" : "Text");
            if (colorChoice != null) {
                applyColorChange(gamePanel, colorChoice, userChoice == 0);
            }
        }

    }

    private String showColorChoiceDialog(String[] colors, String title) {
        return (String) JOptionPane.showInputDialog(null, "Välj färg:",
                title, JOptionPane.PLAIN_MESSAGE, null, colors, colors[0]);
    }

    private void applyColorChange(JPanel gamePanel, String colorChoice, boolean isBackground) {
        Color color = convertColorFromString(colorChoice);
        if (color != null) {
            for (Component c : gamePanel.getComponents()) {
                if (isBackground) {
                    c.setBackground(color);
                } else {
                    c.setForeground(color);
                }
            }
        }

    }

    private Color convertColorFromString(String color) {
        return switch (color.toLowerCase()) {
            case "röd" -> Color.RED;
            case "grön" -> Color.GREEN;
            case "blå" -> Color.BLUE;
            case "svart" -> Color.BLACK;
            case "vit" -> Color.WHITE;
            default -> null;
        };
    }
}




