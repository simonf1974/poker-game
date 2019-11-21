import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class PokerHandTest {

    @Test
    public void hasSameSuit() {
        assertFalse(PokerHand.hasSameSuit( PokerHand.getCardsFromString("AD KS 3D 4D AD")));
        assertTrue(PokerHand.hasSameSuit(PokerHand.getCardsFromString("AD KD 3D 4D AD")));
    }

    @Test
    public void getGroupedCards() {
        assertEquals(PokerHand.getGroupedCards(PokerHand.getCardsFromString("AD KS 3D 3D AD")).get(Card.CardType.THREE).intValue(), 2);
        assertEquals(PokerHand.getGroupedCards(PokerHand.getCardsFromString("AD KS 3D 3D AD")).get(Card.CardType.ACE).intValue(), 2);
        assertEquals(PokerHand.getGroupedCards(PokerHand.getCardsFromString("AD KS 3D 3D AD")).get(Card.CardType.KING).intValue(), 1);

        assertEquals(PokerHand.getGroupedCards(PokerHand.getCardsFromString("AD AS AD 3D AD")).get(Card.CardType.THREE).intValue(), 1);
        assertEquals(PokerHand.getGroupedCards(PokerHand.getCardsFromString("AD AS AD 3D AD")).get(Card.CardType.ACE).intValue(), 4);
    }

    @Test
    public void isStraight() {
        assertFalse(PokerHand.isStraight(PokerHand.getCardsFromString("AD KS 3D 3D AD")));
        assertTrue(PokerHand.isStraight(PokerHand.getCardsFromString("10D 9S 6D 7D 8D")));
    }

    @Test
    public void getHandType() {
        assertEquals(PokerHand.getHandType(PokerHand.getCardsFromString("AD KS 3D 3D AD")), PokerHand.HandType.TWO_PAIR);
        assertEquals(PokerHand.getHandType(PokerHand.getCardsFromString("10D AD KD JD QD")), PokerHand.HandType.ROYAL_FLUSH);
        assertEquals(PokerHand.getHandType(PokerHand.getCardsFromString("10D 9D KD JD QD")), PokerHand.HandType.STRAIGHT_FLUSH);
        assertEquals(PokerHand.getHandType(PokerHand.getCardsFromString("10S AD 10C 10H 10D")), PokerHand.HandType.FOUR_OF_A_KIND);
        assertEquals(PokerHand.getHandType(PokerHand.getCardsFromString("10S 7D 10C 10H 7C")), PokerHand.HandType.FULL_HOUSE);
        assertEquals(PokerHand.getHandType(PokerHand.getCardsFromString("2D AD KD JD QD")), PokerHand.HandType.FLUSH);
        assertEquals(PokerHand.getHandType(PokerHand.getCardsFromString("2S 5D 4D 3D 6D")), PokerHand.HandType.STRAIGHT);
        assertEquals(PokerHand.getHandType(PokerHand.getCardsFromString("10C 10D KD 10D QD")), PokerHand.HandType.THREE_OF_A_KIND);
        assertEquals(PokerHand.getHandType(PokerHand.getCardsFromString("10D AC KD 10S KC")), PokerHand.HandType.TWO_PAIR);
        assertEquals(PokerHand.getHandType(PokerHand.getCardsFromString("10C 10D KD JD QD")), PokerHand.HandType.PAIR);
        assertEquals(PokerHand.getHandType(PokerHand.getCardsFromString("10D 2C KD 5D QD")), PokerHand.HandType.HIGH_CARD);
    }

    @Test
    public void getHighestGrouping() {
        Map<Card.CardType, Long> groupedCards = new HashMap<>();
        groupedCards.put(Card.CardType.ACE, 3l);
        groupedCards.put(Card.CardType.THREE, 2l);

        assertEquals(PokerHand.getHighestGrouping(groupedCards), 3);
    }


    @Test
    public void getOrderedCardTypes() {
        Map<Card.CardType, Long> groupedCards = new HashMap<>();
        groupedCards.put(Card.CardType.ACE, 1l);
        groupedCards.put(Card.CardType.THREE, 1l);
        groupedCards.put(Card.CardType.TWO, 2l);
        assertEquals(PokerHand.getOrderedCardTypes(groupedCards).toString(), "[TWO, ACE, THREE]");

        groupedCards = new HashMap<>();
        groupedCards.put(Card.CardType.ACE, 1l);
        groupedCards.put(Card.CardType.THREE, 2l);
        groupedCards.put(Card.CardType.TWO, 2l);
        assertEquals(PokerHand.getOrderedCardTypes(groupedCards).toString(), "[THREE, TWO, ACE]");

        groupedCards = new HashMap<>();
        groupedCards.put(Card.CardType.THREE, 2l);
        groupedCards.put(Card.CardType.TWO, 3l);
        assertEquals(PokerHand.getOrderedCardTypes(groupedCards).toString(), "[TWO, THREE]");
    }

    @Test
    public void getScore() {
        int twoPair = PokerHand.scoreHand(PokerHand.getCardsFromString("AD KS 3D 3D AD"));
        int royalFlush = PokerHand.scoreHand(PokerHand.getCardsFromString("10D AD KD JD QD"));
        int worseTwoPair = PokerHand.scoreHand(PokerHand.getCardsFromString("7D KS 3D 3D 7D"));
        int straightFlush = PokerHand.scoreHand(PokerHand.getCardsFromString("10D 9D KD JD QD"));
        int fourOfAKind = PokerHand.scoreHand(PokerHand.getCardsFromString("10S AD 10C 10H 10D"));
        int fullHouse = PokerHand.scoreHand(PokerHand.getCardsFromString("10S 7D 10C 10H 7C"));
        int flush = PokerHand.scoreHand(PokerHand.getCardsFromString("2D AD KD JD QD"));
        int straight = PokerHand.scoreHand(PokerHand.getCardsFromString("2S 5D 4D 3D 6D"));
        int threeOfAKind = PokerHand.scoreHand(PokerHand.getCardsFromString("10C 10D KD 10D QD"));
        int pair = PokerHand.scoreHand(PokerHand.getCardsFromString("10C 10D KD JD QD"));
        int highCard = PokerHand.scoreHand(PokerHand.getCardsFromString("10D 2C KD 5D QD"));
        int slightlyBetterHighCard = PokerHand.scoreHand(PokerHand.getCardsFromString("10D 3C KD 5D QD"));

        assertTrue(royalFlush > straightFlush);
        assertTrue(straightFlush > fourOfAKind);
        assertTrue(fourOfAKind > fullHouse);
        assertTrue(fullHouse > flush);
        assertTrue(flush > straight);
        assertTrue(straight > threeOfAKind);
        assertTrue(threeOfAKind > twoPair);
        assertTrue(twoPair > pair);
        assertTrue(pair > highCard);

        assertTrue(twoPair > worseTwoPair);
        assertTrue(slightlyBetterHighCard > highCard);
    }

    @Test
    public void getScoreNegativeTest() {
        assertEquals(0, PokerHand.scoreHand(PokerHand.getCardsFromString("hgjhdgsagdhsa")));
        assertEquals(0, PokerHand.scoreHand(PokerHand.getCardsFromString("10D 2C KD 5D")));
        assertEquals(0, PokerHand.scoreHand(PokerHand.getCardsFromString("10D 2C KD 5D 3C 5C")));
    }

    @Test
    public void getCardsFromString() {
        List<Card> cards = PokerHand.getCardsFromString("9S 10D");
        assertEquals(Card.CardType.NINE, cards.get(0).getType());
        assertEquals(Card.Suit.SPADES, cards.get(0).getSuit());
        assertEquals(Card.CardType.TEN, cards.get(1).getType());
        assertEquals(Card.Suit.DIAMONDS, cards.get(1).getSuit());
    }
}