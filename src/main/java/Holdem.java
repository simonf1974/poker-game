import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.Sets;

public class Holdem {
    public static String getFormattedScores(String tableHand, String... playerHands) {
        Map<String, Integer> scores = getScores(tableHand, playerHands);
        return scores.toString();
    }

    public static Map<String, Integer> getScores(String tableHand, String... playerHands) {
        return getScores(tableHand, Arrays.asList(playerHands));
    }

    public static Map<String, Integer> getScores(String tableHand, List<String> playerHands) {
        List<Integer> scores = playerHands.stream()
                .map(playerHand -> getScoreOfBestFiveCardsFromSevenCards(new Hand(playerHand + " " + tableHand)))
                .collect(Collectors.toList());

        Map<String, Integer> playerScores = new HashMap<>();
        for (int i = 0; i < playerHands.size(); i++) {
            playerScores.put(playerHands.get(i), scores.get(i));
        }

        return playerScores;
    }

    private static int getScoreOfBestFiveCardsFromSevenCards(Hand hand) {
        List<ArrayList> handCombinations =
                Sets.combinations(hand.getCards().stream().collect(Collectors.toSet()), 5)
                .stream()
                .map(item -> new ArrayList(item)).collect(Collectors.toList());

        int highestScoringHand = 0;
        for (List<Card> h : handCombinations) {
            int score = HandScorer.scoreHand(h);
            if (score > highestScoringHand) {
                highestScoringHand = score;
            }
        }
        return highestScoringHand;
    }
}
