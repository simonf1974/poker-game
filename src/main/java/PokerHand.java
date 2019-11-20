import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PokerHand {
    private List<Card> cards;
    public enum HandType {
        ROYAL_FLUSH,
        STRAIGHT_FLUSH,
        FOUR_OF_A_KIND,
        FULL_HOUSE,
        FLUSH,
        STRAIGHT,
        THREE_OF_A_KIND,
        TWO_PAIR,
        PAIR,
        HIGH_CARD
    }

    public PokerHand(List<Card> cards) {
        Collections.sort(cards, Comparator.comparingInt(Card::getValue).reversed());
        this.cards = cards;
    }

    public List<Card> getCards() {
        return cards;
    }

    public boolean hasSameSuit() {
        return cards.stream()
                .map(Card::getSuit)
                .collect(Collectors.toSet())
                .size() == 1;
    }

    public boolean isStraight() {
        int previousCardValue = 0;
        for (Card card : cards) {
            if (previousCardValue == 0) previousCardValue = card.getValue() + 1;
            if (previousCardValue - card.getValue() != 1) return false;
            previousCardValue = card.getValue();
        }
        return true;
    }

    public Map<Card.CardType, Long> getGroupedCards() {
        return cards.stream()
                .collect(Collectors.groupingBy(Card::getType, Collectors.counting()));
    }

    public HandType getHandType() {
        boolean isStraight = isStraight();
        boolean hasSameSuit = hasSameSuit();
        Map<Card.CardType, Long> groupedCards = getGroupedCards();
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

    public long getHighestGrouping(Map<Card.CardType, Long> groupedCards) {
        return groupedCards.entrySet().stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .get().getValue();
    }

    public List<Card.CardType> getOrderedCardTypes(Map<Card.CardType, Long> groupedCards) {
        return groupedCards.entrySet().stream()
                .sorted((card1, card2) -> {
                    if (card1.getValue() == card2.getValue())
                        return Card.getCardValue(card2.getKey()) - Card.getCardValue(card1.getKey());
                    else
                        return card2.getValue().intValue() - card1.getValue().intValue();
                })
                .map(item -> item.getKey())
                .collect(Collectors.toList());
    }

    public int getHandTypeValue() {
        HandType handType = getHandType();
        switch (handType) {
            case ROYAL_FLUSH:
                return 10;
            case STRAIGHT_FLUSH:
                return 9;
            case FOUR_OF_A_KIND:
                return 8;
            case FULL_HOUSE:
                return  7;
            case FLUSH:
                return 6;
            case STRAIGHT:
                return 5;
            case THREE_OF_A_KIND:
                return  4;
            case TWO_PAIR:
                return 3;
            case PAIR:
                return 2;
            case HIGH_CARD:
                return  1;
        }
        return 0;
    }

    public int getScore() {
        int level1Score = 16000000;
        int level2Score = 800000;
        int level3Score = 40000;
        int level4Score = 2000;
        int level5Score = 100;
        int level6Score = 1;
        List<Card.CardType> orderedCardTypes = getOrderedCardTypes(getGroupedCards());

        level1Score *= getHandTypeValue();
        level2Score *= Card.getCardValue(orderedCardTypes.get(0));
        if (orderedCardTypes.size() > 1) level3Score *= Card.getCardValue(orderedCardTypes.get(1));
        if (orderedCardTypes.size() > 2) level4Score *= Card.getCardValue(orderedCardTypes.get(2));
        if (orderedCardTypes.size() > 3) level5Score *= Card.getCardValue(orderedCardTypes.get(3));
        if (orderedCardTypes.size() > 4) level6Score *= Card.getCardValue(orderedCardTypes.get(4));

        return level1Score + level2Score + level3Score + level4Score + level5Score + level6Score;
    }

    @Override
    public String toString() {
        return "PokerHand{" +
                "cards=" + cards +
                '}';
    }
}