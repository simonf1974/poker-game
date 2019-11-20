import java.util.*;
import java.util.stream.Collectors;

import com.google.common.collect.Sets;

public class Holdem {
    private List<Card> playerOneCards;
    private List<Card> playerTwoCards;

    public Holdem(List<Card> playerOneCards, List<Card> playerTwoCards, List<Card> tableCards) {
        this.playerOneCards = playerOneCards;
        this.playerOneCards.addAll(tableCards);
        this.playerTwoCards = playerTwoCards;
        this.playerTwoCards.addAll(tableCards);
    }

    public String getWinner() {
        int playerOneScore = getScoreOfBestFiveCardsFromSevenCards(playerOneCards);
        int playerTwoScore = getScoreOfBestFiveCardsFromSevenCards(playerTwoCards);

        if (playerOneScore > playerTwoScore) {
            return "Player 1 wins!";
        } else if (playerTwoScore > playerOneScore) {
            return "Player 2 wins!";
        } else {
            return "It's a draw!";
        }
    }

    public int getScoreOfBestFiveCardsFromSevenCards(List<Card> cards) {
        List<ArrayList> handCombinations =
                Sets.combinations(cards.stream().collect(Collectors.toSet()), 5)
                .stream()
                .map(item -> new ArrayList(item)).collect(Collectors.toList());

        PokerHand bestHand = null;
        int highestScoringHand = 0;

        for (List<Card> hand : handCombinations) {
            PokerHand ph = new PokerHand(hand);
            int score = ph.getScore();
            if (score > highestScoringHand) {
                highestScoringHand = score;
                bestHand = ph;
            }
        }
        return bestHand.getScore();
    }
}
