import java.util.ArrayList;

public class Player {
    // holds current player's hand
    private ArrayList<Card> hand;
    // holds current sum of card's values
    private int count;
    // holds players name
    private String name;
    // holds index of last recalculated card in hand
    private int lastRecalculated;
    // hold the willing of playerr to continue
    private boolean done;

    // declares maximum for computer to request cards
    public final int MAX_COMP_HAND_COUNT = 18;

    // declares goal count
    public final int GOAL_COUNT = 21;

    /**
     * C'tor
     * @param name of player
     */
    public Player(String name) {
        this.hand = new ArrayList<>();
        this.count = 0;
        this.name = name;
        this.lastRecalculated = -1;
        done = false;
    }

    /**
     * Adds given card to player's hand
     * @param currentCard
     */
    public void addCard(Card currentCard) {
        this.hand.add(currentCard);
        this.count += currentCard.getValue();
    }

    /**
     * Recalculates player's hand,
     * if current hand's count is greater than goal
     * use Ace's value of 1 instead of 11
     * until the count returns to be <= goal
     */
    public void recalculateHand() {
        for (int index = this.lastRecalculated + 1; index < this.hand.size() && this.count > GOAL_COUNT; index++) {
            // got Ace
            if (this.hand.get(index).getValue() == 11) {
                this.count -= 10;
                this.lastRecalculated = index;
            }
        }
    }

    /**
     * @return count
     */
    public int getCount() {
        return count;
    }

    /**
     * @return done
     */
    public boolean isDone() {
        return this.done;
    }

    /**
     * Sets done = true
     */
    public void setDone() {
        this.done = true;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    @Override
    /**
     * @return String representation of Player
     */
    public String toString() {
        String str = this.name + "'s hand:\n";
        for (int index = 0; index < this.hand.size(); index++)
            str += "\t" + this.hand.get(index) + "\n";
        return str;
    }
}
