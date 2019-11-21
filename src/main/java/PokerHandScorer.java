import com.google.common.base.Splitter;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PokerHandScorer {
    public enum HandType {
        ROYAL_FLUSH(10),
        STRAIGHT_FLUSH(9),
        FOUR_OF_A_KIND(8),
        FULL_HOUSE(7),
        FLUSH(6),
        STRAIGHT(5),
        THREE_OF_A_KIND(4),
        TWO_PAIR(3),
        PAIR(2),
        HIGH_CARD(1);

        private int value;

        public int getValue() {
            return  this.value;
        }

        HandType(int value) {
            this.value = value;
        }
    }

    public static int scoreHand(String hand) {
        return scoreHand(getCardsFromString(hand));
    }

    public static List<Card> getCardsFromString(String cards) {
        return Splitter
                .on(" ")
                .trimResults()
                .omitEmptyStrings()
                .splitToList(cards)
                .stream()
                .map((String card) -> new Card(card))
                .filter(card -> card.isValidCard())
                .collect(Collectors.toList());
    }

    public static int scoreHand(List<Card> cards) {
        cards = cards.stream().filter(card -> card.isValidCard()).collect(Collectors.toList());
        if (cards.size() != 5) return 0;
        cards = sortHand(cards);

        int level1Score = 16000000;
        int level2Score = 800000;
        int level3Score = 40000;
        int level4Score = 2000;
        int level5Score = 100;
        int level6Score = 1;
        List<Card.CardType> orderedCardTypes = getOrderedCardTypes(getGroupedCards(cards));

        level1Score *= getHandType(cards).getValue();
        level2Score *= orderedCardTypes.get(0).getValue();
        if (orderedCardTypes.size() > 1) level3Score *= orderedCardTypes.get(1).getValue();
        if (orderedCardTypes.size() > 2) level4Score *= orderedCardTypes.get(2).getValue();
        if (orderedCardTypes.size() > 3) level5Score *= orderedCardTypes.get(3).getValue();
        if (orderedCardTypes.size() > 4) level6Score *= orderedCardTypes.get(4).getValue();

        return level1Score + level2Score + level3Score + level4Score + level5Score + level6Score;
    }

    static boolean hasSameSuit(List<Card> cards) {
        return cards.stream()
                .map(Card::getSuit)
                .collect(Collectors.toSet())
                .size() == 1;
    }

    static boolean isStraight(List<Card> cards) {
        cards = PokerHandScorer.sortHand(cards);
        int previousCardValue = 0;
        for (Card card : cards) {
            if (previousCardValue == 0) previousCardValue = card.getValue() + 1;
            if (previousCardValue - card.getValue() != 1) return false;
            previousCardValue = card.getValue();
        }
        return true;
    }

    static Map<Card.CardType, Long> getGroupedCards(List<Card> cards) {
        return cards.stream()
                .collect(Collectors.groupingBy(Card::getType, Collectors.counting()));
    }

    static HandType getHandType(List<Card> cards) {
        boolean isStraight = isStraight(cards);
        boolean hasSameSuit = hasSameSuit(cards);
        Map<Card.CardType, Long> groupedCards = getGroupedCards(cards);
        long highestGrouping = getHighestGrouping(groupedCards);

        if (isStraight && hasSameSuit && cards.get(0).getType().equals(Card.CardType.ACE)) {
            return HandType.ROYAL_FLUSH;
        } else if (isStraight && hasSameSuit) {
            return HandType.STRAIGHT_FLUSH;
        } else if (highestGrouping == 4) {
            return HandType.FOUR_OF_A_KIND;
        } else if (groupedCards.size() == 2 && highestGrouping == 3) {
            return HandType.FULL_HOUSE;
        } else if (hasSameSuit) {
            return HandType.FLUSH;
        } else if (isStraight) {
            return HandType.STRAIGHT;
        } else if (highestGrouping == 3) {
            return HandType.THREE_OF_A_KIND;
        } else if (groupedCards.size() == 3) {
            return HandType.TWO_PAIR;
        } else if (groupedCards.size() == 4) {
            return HandType.PAIR;
        } else {
            return HandType.HIGH_CARD;
        }
    }

    static long getHighestGrouping(Map<Card.CardType, Long> groupedCards) {
        return groupedCards.entrySet().stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .get().getValue();
    }

    static List<Card.CardType> getOrderedCardTypes(Map<Card.CardType, Long> groupedCards) {
        return groupedCards.entrySet().stream()
                .sorted((card1, card2) -> {
                    if (card1.getValue() == card2.getValue())
                        return card2.getKey().getValue() - card1.getKey().getValue();
                    else
                        return card2.getValue().intValue() - card1.getValue().intValue();
                })
                .map(item -> item.getKey())
                .collect(Collectors.toList());
    }

    private static List<Card> sortHand(List<Card> cards) {
        Collections.sort(cards, Comparator.comparingInt(Card::getValue).reversed());
        return cards;
    }
}