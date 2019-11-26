package io.nology.poker.engine;

import java.util.*;
import java.util.stream.Collectors;

import com.google.common.collect.Sets;
import io.nology.poker.entity.Player;
import io.nology.poker.entity.Table;

public class Holdem {
    public static String getFormattedScores(String tableHand, String... playerHands) {
        Map<String, Integer> scores = getScores(tableHand, playerHands);
        return scores.toString();
    }

    public static Map<String, Integer> getScores(String tableHand, String... playerHands) {
        return getScores(tableHand, Arrays.asList(playerHands));
    }

    public static Table scoreTable(Table table) {
        Map<String, Integer> scores = getScores(table.getTableCards(), table.getPlayerCards());

        table.getPlayers().stream()
                .forEach(p -> p.setScore(scores.get(p.getHand())));

        List<Player> orderedPlayers = table.getPlayers().stream()
                .sorted(Comparator.comparing(Player::getScore).reversed())
                .collect(Collectors.toList());

        int rank = 1;
        int prevScore = orderedPlayers.get(0).getScore();
        for (Player player : orderedPlayers) {
            if (player.getScore() == prevScore) {
                player.setRank(rank);
            } else {
                player.setRank(++rank);
                prevScore = player.getScore();
            }
        }

        table.setPlayers(orderedPlayers);

        return table;
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
