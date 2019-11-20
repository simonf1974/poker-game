public class Card {
    private Suit suit;
    private CardType type;

    public enum Suit {
        HEARTS,
        CLUBS,
        SPADES,
        DIAMONDS
    }

    public enum CardType {
        ACE,
        KING,
        QUEEN,
        JACK,
        TEN,
        NINE,
        EIGHT,
        SEVEN,
        SIX,
        FIVE,
        FOUR,
        THREE,
        TWO,
        ONE;
    }

    public Card(Suit suit, CardType type) {
        this.suit = suit;
        this.type = type;
    }

    public Suit getSuit() {
        return suit;
    }

    public CardType getType() {
        return type;
    }

    public static int getCardValue(CardType cardType) {
        switch (cardType) {
            case ACE:
                return 14;
            case KING:
                return 13;
            case QUEEN:
                return 12;
            case JACK:
                return 11;
            case TEN:
                return 10;
            case NINE:
                return 9;
            case EIGHT:
                return 8;
            case SEVEN:
                return 7;
            case SIX:
                return 6;
            case FIVE:
                return 5;
            case FOUR:
                return 4;
            case THREE:
                return 3;
            case TWO:
                return 2;
        }
        return 0;
    }

    public int getValue() {
        return getCardValue(this.type);
    }

    @Override
    public String toString() {
        return "Card{" +
                "suit=" + suit +
                ", type=" + type +
                '}';
    }
}
