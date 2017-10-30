import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    // holds current list of cards
    private ArrayList<Card> deck = new ArrayList<>();

    /**
     * C'tor
     */
    public Deck() {
        // adds cards
        for (Card.Face face: Card.Face.values())
            for (Card.Suit suit: Card.Suit.values()) {
                this.deck.add(new Card(face, suit));
            }
    }

    /**
     * Shuffles the cards in the deck
     */
    public void Shuffle() {
        Collections.shuffle(this.deck);
    }

    /**
     * @return Top card of the deck
     */
    public Card getTopCard() {
        Card card = new Card(deck.get(0));
        deck.remove(0);
        return card;
    }

    /**
     * @return String representation of deck
     */
    @Override
    public String toString() {
        String str = "";
        for (int index = 0; index < this.deck.size(); index++)
            str += this.deck.get(index) + "\n";
        return str;
    }
}
