import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // holds user's name
        String name = "Rachmany Roy";

        // if you want to name the player, unmark the next line
        // name = JOptionPane.showInputDialog("What's your name?");

        // creates new game with given user
        BlackJack blackJack = new BlackJack(name);
        // runs the game
        blackJack.startNewGame();
    }
}
