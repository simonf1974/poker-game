import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class HoldemTest {

    @Test
    public void getWinner() {
        List<Card> playerOneCards = new ArrayList<>();
        playerOneCards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.ACE));
        playerOneCards.add(new Card(Card.Suit.SPADES, Card.CardType.KING));
        playerOneCards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.THREE));
        playerOneCards.add(new Card(Card.Suit.CLUBS, Card.CardType.THREE));
        playerOneCards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.ACE));

        List<Card> playerTwoCards = new ArrayList<>();
        playerTwoCards.add(new Card(Card.Suit.CLUBS, Card.CardType.ACE));
        playerTwoCards.add(new Card(Card.Suit.HEARTS, Card.CardType.KING));
        playerTwoCards.add(new Card(Card.Suit.CLUBS, Card.CardType.TWO));
        playerTwoCards.add(new Card(Card.Suit.CLUBS, Card.CardType.THREE));
        playerTwoCards.add(new Card(Card.Suit.CLUBS, Card.CardType.EIGHT));

        List<Card> tableCards = new ArrayList<>();
        tableCards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.EIGHT));
        tableCards.add(new Card(Card.Suit.SPADES, Card.CardType.EIGHT));

        Holdem holdem = new Holdem(playerOneCards, playerTwoCards, tableCards);

        System.out.println(holdem.getWinner());
    }
}