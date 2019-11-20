import java.util.List;

public class Holdem {
    private List<Card> playerOneCards;
    private List<Card> playerTwoCards;

    public Holdem(List<Card> playerOneCards, List<Card> playerTwoCards, List<Card> tableCards) {
        this.playerOneCards = playerOneCards;
        this.playerOneCards.addAll(tableCards);
        this.playerTwoCards = playerTwoCards;
        this.playerTwoCards.addAll(tableCards);
    }

    public List<Card> getBestFiveCards(List<Card> cards) {
        return null;
    }
}
