import com.google.common.collect.ImmutableMap;

import java.util.Map;

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

    private Map<String, Suit> suitMapper = ImmutableMap.<String, Suit> builder()
            .put("D", Suit.DIAMONDS)
            .put("C", Suit.CLUBS)
            .put("H", Suit.HEARTS)
            .put("S", Suit.SPADES)
            .build();

    private Map<String, CardType> cardTypeMapper = ImmutableMap.<String, CardType> builder()
            .put("A", CardType.ACE)
            .put("K", CardType.KING)
            .put("Q", CardType.QUEEN)
            .put("J", CardType.JACK)
            .put("10", CardType.TEN)
            .put("9", CardType.NINE)
            .put("8", CardType.EIGHT)
            .put("7", CardType.SEVEN)
            .put("6", CardType.SIX)
            .put("5", CardType.FIVE)
            .put("4", CardType.FOUR)
            .put("3", CardType.THREE)
            .put("2", CardType.TWO)
            .build();

    public Card(String card) {
        this.type = cardTypeMapper.get(card.substring(0, card.length()-1));
        this.suit = suitMapper.get(card.substring(card.length()-1));
    }

    public Card(Suit suit, CardType type) {
        this.suit = suit;
        this.type = type;
    }

    public boolean isValidCard() {
        return type != null && suit !=null;
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
