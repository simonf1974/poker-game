import java.util.*;
import java.util.stream.Collectors;

import com.google.common.collect.Sets;

public class Holdem {
    public static Map<String, Integer> getScores(List<String> playerHands, String tableHand) {
        List<Integer> scores = playerHands.stream()
                .map(playerHand -> getScoreOfBestFiveCardsFromSevenCards(getMergedHand(playerHand, tableHand)))
                .collect(Collectors.toList());

        Map<String, Integer> playerScores = new HashMap<>();
        for (int i = 0; i < playerHands.size(); i++) {
            playerScores.put(playerHands.get(i), scores.get(i));
        }

        return playerScores;
    }

    private static List<Card> getMergedHand(String playerHand, String tableHand) {
        return PokerHandScorer.getCardsFromString(playerHand + " " + tableHand);
    }

    private static int getScoreOfBestFiveCardsFromSevenCards(List<Card> cards) {
        List<ArrayList> handCombinations =
                Sets.combinations(cards.stream().collect(Collectors.toSet()), 5)
                .stream()
                .map(item -> new ArrayList(item)).collect(Collectors.toList());

        int highestScoringHand = 0;
        for (List<Card> hand : handCombinations) {
            int score = PokerHandScorer.scoreHand(hand);
            if (score > highestScoringHand) {
                highestScoringHand = score;
            }
        }
        return highestScoringHand;
    }
}
