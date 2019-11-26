package io.nology.poker.engine;

import java.util.List;
import java.util.Map;

public class HandScorer {
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
        return scoreHand(new Hand(hand));
    }

    public static int scoreHand(List<Card> cards) {
        return scoreHand(new Hand(cards));
    }

    public static int scoreHand(Hand hand) {
        if (hand.getSize() != 5) return 0;

        int level1Score = 16000000;
        int level2Score = 800000;
        int level3Score = 40000;
        int level4Score = 2000;
        int level5Score = 100;
        int level6Score = 1;
        List<Card.CardRank> ranksInOrderOfImportance = hand.getRanksInOrderOfImportance();

        level1Score *= getHandType(hand).getValue();
        level2Score *= ranksInOrderOfImportance.get(0).getValue();
        if (ranksInOrderOfImportance.size() > 1) level3Score *= ranksInOrderOfImportance.get(1).getValue();
        if (ranksInOrderOfImportance.size() > 2) level4Score *= ranksInOrderOfImportance.get(2).getValue();
        if (ranksInOrderOfImportance.size() > 3) level5Score *= ranksInOrderOfImportance.get(3).getValue();
        if (ranksInOrderOfImportance.size() > 4) level6Score *= ranksInOrderOfImportance.get(4).getValue();

        return level1Score + level2Score + level3Score + level4Score + level5Score + level6Score;
    }

    static HandScorer.HandType getHandType(Hand hand) {
        Map<Card.CardRank, Long> rankVarieties = hand.getRankVarieties();
        long occurrencesOfHighestOccurringRank = hand.getOccurrencesOfHighestOccurringRank();

        if (hand.isStraight() && hand.hasSameSuit() && hand.getCards().get(0).getRank().equals(Card.CardRank.ACE)) {
            return HandScorer.HandType.ROYAL_FLUSH;
        } else if (hand.isStraight() && hand.hasSameSuit()) {
            return HandScorer.HandType.STRAIGHT_FLUSH;
        } else if (occurrencesOfHighestOccurringRank == 4) {
            return HandScorer.HandType.FOUR_OF_A_KIND;
        } else if (rankVarieties.size() == 2 && occurrencesOfHighestOccurringRank == 3) {
            return HandScorer.HandType.FULL_HOUSE;
        } else if (hand.hasSameSuit()) {
            return HandScorer.HandType.FLUSH;
        } else if (hand.isStraight()) {
            return HandScorer.HandType.STRAIGHT;
        } else if (occurrencesOfHighestOccurringRank == 3) {
            return HandScorer.HandType.THREE_OF_A_KIND;
        } else if (rankVarieties.size() == 3) {
            return HandScorer.HandType.TWO_PAIR;
        } else if (rankVarieties.size() == 4) {
            return HandScorer.HandType.PAIR;
        } else {
            return HandScorer.HandType.HIGH_CARD;
        }
    }
}
