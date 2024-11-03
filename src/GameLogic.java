import javax.swing.*;

public class GameLogic {

    private int emptyButtonIndex = 15;
    private boolean gameRandomizer = false;

    public void buttonRandomize(JPanel comp)  {
        int i = 0;
        gameRandomizer = true;
        while(i<=150) {
            ((JButton) comp.getComponent(randomClickableButton())).doClick(1);
            i++;
        }
        gameRandomizer = false;
    }

    public int randomClickableButton () {
        int i;
        do {
            i = (int) (Math.random()*16);
        }
        while (!clickableButton(i));
        return i;
    }

    public boolean clickableButton(int index) {
        int above = emptyButtonIndex-4;
        int below = emptyButtonIndex+4;
        int rightOf = emptyButtonIndex+1;
        int leftOf = emptyButtonIndex-1;

        if(emptyButtonIndex ==3||emptyButtonIndex ==7||emptyButtonIndex ==11) {
            return index == above || index == below || index == leftOf;
        }
        if(emptyButtonIndex ==4||emptyButtonIndex ==8||emptyButtonIndex ==12){
            return index == above || index == below || index == rightOf;
        }
        return index == above || index == below || index == rightOf || index == leftOf;
    }

    public boolean checkGameComplete(JPanel comp) {

        boolean complete = false;
        if (gameRandomizer) {
            return false;
        }
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

    public int getEmptyButtonIndex () {
        return emptyButtonIndex;
    }

    public void setEmptyButtonIndex (int emptyButtonIndex) {
        this.emptyButtonIndex = emptyButtonIndex;
    }
}
