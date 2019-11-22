import com.google.common.collect.ImmutableMap;

import java.util.Map;

public class Card {
    private Suit suit;
    private CardRank rank;

    public enum Suit {
        HEARTS,
        CLUBS,
        SPADES,
        DIAMONDS
    }

    public enum CardRank {
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

        CardRank(int value) {
            this.value = value;
        }
    }

    private Map<String, Suit> suitMapper = ImmutableMap.<String, Suit> builder()
            .put("D", Suit.DIAMONDS)
            .put("C", Suit.CLUBS)
            .put("H", Suit.HEARTS)
            .put("S", Suit.SPADES)
            .build();

    private Map<String, CardRank> cardValueMapper = ImmutableMap.<String, CardRank> builder()
            .put("A", CardRank.ACE)
            .put("K", CardRank.KING)
            .put("Q", CardRank.QUEEN)
            .put("J", CardRank.JACK)
            .put("10", CardRank.TEN)
            .put("9", CardRank.NINE)
            .put("8", CardRank.EIGHT)
            .put("7", CardRank.SEVEN)
            .put("6", CardRank.SIX)
            .put("5", CardRank.FIVE)
            .put("4", CardRank.FOUR)
            .put("3", CardRank.THREE)
            .put("2", CardRank.TWO)
            .build();

    public Card(String card) {
        this.suit = suitMapper.get(card.substring(card.length()-1));
        if (this.suit == null) {
            this.suit = suitMapper.get(card.substring(0, 1));
            this.rank = cardValueMapper.get(card.substring(1, card.length()));
        } else {
            this.rank = cardValueMapper.get(card.substring(0, card.length()-1));
        }
    }

    public Card(Suit suit, CardRank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public boolean isValidCard() {
        return rank != null && suit !=null;
    }

    public Suit getSuit() {
        return suit;
    }

    public CardRank getRank() {
        return rank;
    }

    public int getCardValue() {
        return this.rank.getValue();
    }

    @Override
    public String toString() {
        return "Card{" +
                "suit=" + suit +
                ", type=" + rank +
                '}';
    }
}
