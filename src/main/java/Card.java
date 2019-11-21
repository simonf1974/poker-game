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
        ACE(14),
        KING(13),
        QUEEN(12),
        JACK(11),
        TEN(10),
        NINE(9),
        EIGHT(8),
        SEVEN(7),
        SIX(6),
        FIVE(5),
        FOUR(4),
        THREE(3),
        TWO(2);

        private int value;

        public int getValue() {
            return  this.value;
        }

        CardType(int value) {
            this.value = value;
        }
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

    public int getValue() {
        return this.type.getValue();
    }

    @Override
    public String toString() {
        return "Card{" +
                "suit=" + suit +
                ", type=" + type +
                '}';
    }
}
