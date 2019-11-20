import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class PokerHandTest {

    @Test
    public void getCards() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.ACE));
        cards.add(new Card(Card.Suit.SPADES, Card.CardType.KING));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.THREE));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.THREE));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.ACE));

        PokerHand pokerHand = new PokerHand(cards);
        System.out.println(pokerHand);
    }

    @Test
    public void hasSameSuit() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.ACE));
        cards.add(new Card(Card.Suit.SPADES, Card.CardType.KING));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.THREE));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.THREE));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.ACE));

        PokerHand pokerHand = new PokerHand(cards);
        assertFalse(pokerHand.hasSameSuit());

        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.ACE));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.KING));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.THREE));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.THREE));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.ACE));

        pokerHand = new PokerHand(cards);
        assertTrue(pokerHand.hasSameSuit());
    }

    @Test
    public void getGroupedCards() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.ACE));
        cards.add(new Card(Card.Suit.SPADES, Card.CardType.KING));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.THREE));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.THREE));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.ACE));

        PokerHand pokerHand = new PokerHand(cards);
        assertEquals(pokerHand.getGroupedCards().get(Card.CardType.THREE).intValue(), 2);
        assertEquals(pokerHand.getGroupedCards().get(Card.CardType.ACE).intValue(), 2);
        assertEquals(pokerHand.getGroupedCards().get(Card.CardType.KING).intValue(), 1);


        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.ACE));
        cards.add(new Card(Card.Suit.SPADES, Card.CardType.ACE));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.ACE));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.THREE));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.ACE));

        pokerHand = new PokerHand(cards);
        assertEquals(pokerHand.getGroupedCards().get(Card.CardType.THREE).intValue(), 1);
        assertEquals(pokerHand.getGroupedCards().get(Card.CardType.ACE).intValue(), 4);
    }

    @Test
    public void isStraight() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.ACE));
        cards.add(new Card(Card.Suit.SPADES, Card.CardType.KING));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.THREE));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.THREE));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.ACE));

        PokerHand pokerHand = new PokerHand(cards);
        assertFalse(pokerHand.isStraight());

        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.TEN));
        cards.add(new Card(Card.Suit.SPADES, Card.CardType.NINE));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.SIX));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.SEVEN));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.EIGHT));

        pokerHand = new PokerHand(cards);
        assertTrue(pokerHand.isStraight());
    }

    @Test
    public void getHandType() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.ACE));
        cards.add(new Card(Card.Suit.SPADES, Card.CardType.KING));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.THREE));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.THREE));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.ACE));

        PokerHand pokerHand = new PokerHand(cards);
        assertEquals(pokerHand.getHandType(), PokerHand.HandType.TWO_PAIR);

        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.TEN));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.ACE));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.KING));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.JACK));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.QUEEN));

        pokerHand = new PokerHand(cards);
        assertEquals(pokerHand.getHandType(), PokerHand.HandType.ROYAL_FLUSH);

        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.TEN));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.NINE));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.KING));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.JACK));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.QUEEN));

        pokerHand = new PokerHand(cards);
        assertEquals(pokerHand.getHandType(), PokerHand.HandType.STRAIGHT_FLUSH);

        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.SPADES, Card.CardType.TEN));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.ACE));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.TEN));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.TEN));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.TEN));

        pokerHand = new PokerHand(cards);
        assertEquals(pokerHand.getHandType(), PokerHand.HandType.FOUR_OF_A_KIND);

        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.SPADES, Card.CardType.TEN));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.SEVEN));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.TEN));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.TEN));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.SEVEN));

        pokerHand = new PokerHand(cards);
        assertEquals(pokerHand.getHandType(), PokerHand.HandType.FULL_HOUSE);

        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.TWO));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.ACE));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.KING));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.JACK));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.QUEEN));

        pokerHand = new PokerHand(cards);
        assertEquals(pokerHand.getHandType(), PokerHand.HandType.FLUSH);

        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.SPADES, Card.CardType.TWO));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.FIVE));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.FOUR));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.THREE));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.SIX));

        pokerHand = new PokerHand(cards);
        assertEquals(pokerHand.getHandType(), PokerHand.HandType.STRAIGHT);

        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.CLUBS, Card.CardType.TEN));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.TEN));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.KING));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.TEN));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.QUEEN));

        pokerHand = new PokerHand(cards);
        assertEquals(pokerHand.getHandType(), PokerHand.HandType.THREE_OF_A_KIND);

        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.TEN));
        cards.add(new Card(Card.Suit.CLUBS, Card.CardType.ACE));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.KING));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.TEN));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.KING));

        pokerHand = new PokerHand(cards);
        assertEquals(pokerHand.getHandType(), PokerHand.HandType.TWO_PAIR);

        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.CLUBS, Card.CardType.TEN));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.TEN));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.KING));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.JACK));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.QUEEN));

        pokerHand = new PokerHand(cards);
        assertEquals(pokerHand.getHandType(), PokerHand.HandType.PAIR);

        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.TEN));
        cards.add(new Card(Card.Suit.CLUBS, Card.CardType.TWO));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.KING));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.FIVE));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.QUEEN));

        pokerHand = new PokerHand(cards);
        assertEquals(pokerHand.getHandType(), PokerHand.HandType.HIGH_CARD);
    }

    @Test
    public void getHighestGrouping() {
        Map<Card.CardType, Long> groupedCards = new HashMap<>();
        groupedCards.put(Card.CardType.ACE, 3l);
        groupedCards.put(Card.CardType.THREE, 2l);

        PokerHand pokerHand = new PokerHand(getAnyHand());
        assertEquals(pokerHand.getHighestGrouping(groupedCards), 3);
    }

    private List<Card> getAnyHand() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.ACE));
        cards.add(new Card(Card.Suit.SPADES, Card.CardType.KING));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.THREE));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.THREE));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.ACE));
        return cards;
    }

    @Test
    public void getOrderedCardTypes() {
        Map<Card.CardType, Long> groupedCards = new HashMap<>();
        groupedCards.put(Card.CardType.ACE, 1l);
        groupedCards.put(Card.CardType.THREE, 1l);
        groupedCards.put(Card.CardType.TWO, 2l);
        PokerHand pokerHand = new PokerHand(getAnyHand());
        assertEquals(pokerHand.getOrderedCardTypes(groupedCards).toString(), "[TWO, ACE, THREE]");

        groupedCards = new HashMap<>();
        groupedCards.put(Card.CardType.ACE, 1l);
        groupedCards.put(Card.CardType.THREE, 2l);
        groupedCards.put(Card.CardType.TWO, 2l);
        assertEquals(pokerHand.getOrderedCardTypes(groupedCards).toString(), "[THREE, TWO, ACE]");

        groupedCards = new HashMap<>();
        groupedCards.put(Card.CardType.THREE, 2l);
        groupedCards.put(Card.CardType.TWO, 3l);
        assertEquals(pokerHand.getOrderedCardTypes(groupedCards).toString(), "[TWO, THREE]");
    }

    @Test
    public void getScore() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.ACE));
        cards.add(new Card(Card.Suit.SPADES, Card.CardType.KING));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.THREE));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.THREE));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.ACE));
        PokerHand twoPair = new PokerHand(cards);

        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.TEN));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.ACE));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.KING));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.JACK));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.QUEEN));
        PokerHand royalFlush = new PokerHand(cards);

        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.SEVEN));
        cards.add(new Card(Card.Suit.SPADES, Card.CardType.KING));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.THREE));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.THREE));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.SEVEN));
        PokerHand worseTwoPair = new PokerHand(cards);


        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.TEN));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.NINE));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.KING));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.JACK));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.QUEEN));
        PokerHand straightFlush = new PokerHand(cards);

        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.SPADES, Card.CardType.TEN));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.ACE));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.TEN));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.TEN));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.TEN));
        PokerHand fourOfAKind = new PokerHand(cards);

        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.SPADES, Card.CardType.TEN));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.SEVEN));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.TEN));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.TEN));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.SEVEN));
        PokerHand fullHouse = new PokerHand(cards);

        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.TWO));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.ACE));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.KING));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.JACK));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.QUEEN));
        PokerHand flush = new PokerHand(cards);

        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.SPADES, Card.CardType.TWO));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.FIVE));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.FOUR));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.THREE));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.SIX));
        PokerHand straight = new PokerHand(cards);

        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.CLUBS, Card.CardType.TEN));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.TEN));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.KING));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.TEN));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.QUEEN));
        PokerHand threeOfAKind = new PokerHand(cards);


        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.CLUBS, Card.CardType.TEN));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.TEN));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.KING));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.JACK));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.QUEEN));
        PokerHand pair = new PokerHand(cards);

        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.TEN));
        cards.add(new Card(Card.Suit.CLUBS, Card.CardType.TWO));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.KING));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.FIVE));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.QUEEN));
        PokerHand highCard = new PokerHand(cards);

        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.TEN));
        cards.add(new Card(Card.Suit.CLUBS, Card.CardType.THREE));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.KING));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.FIVE));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.CardType.QUEEN));
        PokerHand slightlyBetterHighCard = new PokerHand(cards);


        assertTrue(royalFlush.getScore() > straightFlush.getScore());
        assertTrue(straightFlush.getScore() > fourOfAKind.getScore());
        assertTrue(fourOfAKind.getScore() > fullHouse.getScore());
        assertTrue(fullHouse.getScore() > flush.getScore());
        assertTrue(flush.getScore() > straight.getScore());
        assertTrue(straight.getScore() > threeOfAKind.getScore());
        assertTrue(threeOfAKind.getScore() > twoPair.getScore());
        assertTrue(twoPair.getScore() > pair.getScore());
        assertTrue(pair.getScore() > highCard.getScore());

        assertTrue(twoPair.getScore() > worseTwoPair.getScore());
        assertTrue(slightlyBetterHighCard.getScore() > highCard.getScore());
    }
}