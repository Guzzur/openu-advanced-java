public class Card {
    // holds cards names and values as enum
    public enum Face {
        A, Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, J, Q, K
    };

    // holds suit names
    public enum Suit {
        Diamonds, Clubs, Spades, Hearts
    };

    private Face face;
    private Suit suit;

    /**
     * Constructor
     * @param face value
     * @param suit value
     */
    public Card(Face face, Suit suit) {
        this.face = face;
        this.suit = suit;
    }

    /**
     * Copy Constructor
     * @param other card
     */
    public Card(Card other) {
        this.face = other.face;
        this.suit = other.suit;
    }

    /**
     * @return face
     */
    public Face getFace() {
        return face;
    }

    /**
     * @return suit
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * @return value of card
     */
    public int getValue() {
        // Ace's value is 11 until recalculation is done
        if (this.face == Face.A) return 11;
        // J, Q, K values are 10
        else if (this.face.ordinal() > 9) return 10;
        // others are equal to themselves
        else return this.face.ordinal() + 1;
    }


    /**
     * @return string representation of card
     */
    @Override
    public String toString() {
        return "[" + this.face + " of " + this.suit + "]";
    }
}
