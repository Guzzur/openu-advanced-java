import javax.swing.JOptionPane;

public class BlackJack {
    // holds game deck
    private Deck deck;
    // players: computer and human
    private Player comp, user;

    /**
     * C'tor
     * @param name
     */
    public BlackJack(String name) {
        // one of the best russians black jackers,
        // the ultimate Vasily a.k.a computer
        this.comp = new Player("Vasily Vasiliev");
        // user player
        this.user = new Player(name);

        this.deck = new Deck();
        this.deck.Shuffle();
    }

    /**
     * Runs the game
     */
    public void startNewGame() {
        // starting two cards for user
        user.addCard(deck.getTopCard());
        user.addCard(deck.getTopCard());

        //starting two cards for computer
        comp.addCard(deck.getTopCard());
        comp.addCard(deck.getTopCard());

        // holds turns counter
        int turn = 1;
        // prints current turn data
        System.out.println("=== Turn " + turn + " ======================");
        System.out.println("User's data: " + user);
        System.out.println("Count: " + user.getCount() + "\n");

        // holds the decision to take a card
        boolean takeCard;
        // holds the message to be printed
        String msg;

        // while one of the players is still playing
        while (!comp.isDone() || !user.isDone()) {
            // user's logic
            if(!user.isDone()){
                msg = this.user + "\nYour count is " + user.getCount() +
                        "\nDo you want to take a card?";
                // ask user to take another card
                takeCard = JOptionPane.showConfirmDialog(null, msg) ==
                        JOptionPane.OK_OPTION;

                // user took a card
                if (takeCard) {
                    // add card to user's hand
                    user.addCard(deck.getTopCard());
                    // recalculate hand if needed
                    user.recalculateHand();
                    // prints current turn data
                    System.out.println("=== Turn " + ++turn + " ======================");
                    System.out.println("User's data: " + user);
                    System.out.println("Count: " + user.getCount() + "\n");
                }
                // user decided to finish
                else user.setDone();
                // overflow
                if (user.getCount() > user.GOAL_COUNT)
                    user.setDone();
            }
            // comp's logic
            if (!comp.isDone()){
                // didn't reach the limit
                if (comp.getCount() < comp.MAX_COMP_HAND_COUNT) {
                    // add card to comp's hand
                    comp.addCard(deck.getTopCard());
                    // recalculate hand if needed
                    comp.recalculateHand();
                }
                // reached the limit or overflow
                if (comp.getCount() >= comp.MAX_COMP_HAND_COUNT)
                    comp.setDone();
            }
        }

        // prints conclusion message
        System.out.println("=== Finished ====================");
        msg = "User's data: " + user +
                "Count: " + user.getCount() + "\n\n" +
                "Comp's data: " + comp +
                "Count: " + comp.getCount() + "\n\n";
        System.out.println(msg);

        // checks who is the winner
        Player winner = declareWinner();

        // winner exist
        if (winner != null)
            msg += "The winner is " + winner.getName();
        // no winner
        else
            msg += "No winner!";

        // informs the user who won
        JOptionPane.showMessageDialog(null, msg, "Black Jack",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Checks who was the nearest to the goal
     * @return winning player
     */
    public Player declareWinner() {
        // user win
        if (((user.getCount() <= user.GOAL_COUNT) && (user.getCount() > comp.getCount())) ||
                ((user.getCount() <= user.GOAL_COUNT) && (comp.getCount() > comp.GOAL_COUNT)))
            return user;
        // comp win
        else if (comp.getCount() <= comp.GOAL_COUNT && comp.getCount() != user.getCount()) return comp;

        // no winner
        return null;
    }
}
