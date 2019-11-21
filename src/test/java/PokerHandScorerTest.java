import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class PokerHandScorerTest {

    @Test
    public void hasSameSuit() {
        assertFalse(PokerHandScorer.hasSameSuit( PokerHandScorer.getCardsFromString("AD KS 3D 4D AD")));
        assertTrue(PokerHandScorer.hasSameSuit(PokerHandScorer.getCardsFromString("AD KD 3D 4D AD")));
    }

    @Test
    public void getGroupedCards() {
        assertEquals(PokerHandScorer.getGroupedCards(PokerHandScorer.getCardsFromString("AD KS 3D 3D AD")).get(Card.CardType.THREE).intValue(), 2);
        assertEquals(PokerHandScorer.getGroupedCards(PokerHandScorer.getCardsFromString("AD KS 3D 3D AD")).get(Card.CardType.ACE).intValue(), 2);
        assertEquals(PokerHandScorer.getGroupedCards(PokerHandScorer.getCardsFromString("AD KS 3D 3D AD")).get(Card.CardType.KING).intValue(), 1);

        assertEquals(PokerHandScorer.getGroupedCards(PokerHandScorer.getCardsFromString("AD AS AD 3D AD")).get(Card.CardType.THREE).intValue(), 1);
        assertEquals(PokerHandScorer.getGroupedCards(PokerHandScorer.getCardsFromString("AD AS AD 3D AD")).get(Card.CardType.ACE).intValue(), 4);
    }

    @Test
    public void isStraight() {
        assertFalse(PokerHandScorer.isStraight(PokerHandScorer.getCardsFromString("AD KS 3D 3D AD")));
        assertTrue(PokerHandScorer.isStraight(PokerHandScorer.getCardsFromString("10D 9S 6D 7D 8D")));
    }

    @Test
    public void getHandType() {
        assertEquals(PokerHandScorer.getHandType(PokerHandScorer.getCardsFromString("AD KS 3D 3D AD")), PokerHandScorer.HandType.TWO_PAIR);
        assertEquals(PokerHandScorer.getHandType(PokerHandScorer.getCardsFromString("10D AD KD JD QD")), PokerHandScorer.HandType.ROYAL_FLUSH);
        assertEquals(PokerHandScorer.getHandType(PokerHandScorer.getCardsFromString("10D 9D KD JD QD")), PokerHandScorer.HandType.STRAIGHT_FLUSH);
        assertEquals(PokerHandScorer.getHandType(PokerHandScorer.getCardsFromString("10S AD 10C 10H 10D")), PokerHandScorer.HandType.FOUR_OF_A_KIND);
        assertEquals(PokerHandScorer.getHandType(PokerHandScorer.getCardsFromString("10S 7D 10C 10H 7C")), PokerHandScorer.HandType.FULL_HOUSE);
        assertEquals(PokerHandScorer.getHandType(PokerHandScorer.getCardsFromString("2D AD KD JD QD")), PokerHandScorer.HandType.FLUSH);
        assertEquals(PokerHandScorer.getHandType(PokerHandScorer.getCardsFromString("2S 5D 4D 3D 6D")), PokerHandScorer.HandType.STRAIGHT);
        assertEquals(PokerHandScorer.getHandType(PokerHandScorer.getCardsFromString("10C 10D KD 10D QD")), PokerHandScorer.HandType.THREE_OF_A_KIND);
        assertEquals(PokerHandScorer.getHandType(PokerHandScorer.getCardsFromString("10D AC KD 10S KC")), PokerHandScorer.HandType.TWO_PAIR);
        assertEquals(PokerHandScorer.getHandType(PokerHandScorer.getCardsFromString("10C 10D KD JD QD")), PokerHandScorer.HandType.PAIR);
        assertEquals(PokerHandScorer.getHandType(PokerHandScorer.getCardsFromString("10D 2C KD 5D QD")), PokerHandScorer.HandType.HIGH_CARD);
    }

    @Test
    public void getHighestGrouping() {
        Map<Card.CardType, Long> groupedCards = new HashMap<>();
        groupedCards.put(Card.CardType.ACE, 3l);
        groupedCards.put(Card.CardType.THREE, 2l);

        assertEquals(PokerHandScorer.getHighestGrouping(groupedCards), 3);
    }


    @Test
    public void getOrderedCardTypes() {
        Map<Card.CardType, Long> groupedCards = new HashMap<>();
        groupedCards.put(Card.CardType.ACE, 1l);
        groupedCards.put(Card.CardType.THREE, 1l);
        groupedCards.put(Card.CardType.TWO, 2l);
        assertEquals(PokerHandScorer.getOrderedCardTypes(groupedCards).toString(), "[TWO, ACE, THREE]");

        groupedCards = new HashMap<>();
        groupedCards.put(Card.CardType.ACE, 1l);
        groupedCards.put(Card.CardType.THREE, 2l);
        groupedCards.put(Card.CardType.TWO, 2l);
        assertEquals(PokerHandScorer.getOrderedCardTypes(groupedCards).toString(), "[THREE, TWO, ACE]");

        groupedCards = new HashMap<>();
        groupedCards.put(Card.CardType.THREE, 2l);
        groupedCards.put(Card.CardType.TWO, 3l);
        assertEquals(PokerHandScorer.getOrderedCardTypes(groupedCards).toString(), "[TWO, THREE]");
    }

    @Test
    public void getScore() {
        int twoPair = PokerHandScorer.scoreHand("AD KS 3D 3D AD");
        int royalFlush = PokerHandScorer.scoreHand("10D AD KD JD QD");
        int worseTwoPair = PokerHandScorer.scoreHand("7D KS 3D 3D 7D");
        int straightFlush = PokerHandScorer.scoreHand("10D 9D KD JD QD");
        int fourOfAKind = PokerHandScorer.scoreHand("10S AD 10C 10H 10D");
        int fullHouse = PokerHandScorer.scoreHand("10S 7D 10C 10H 7C");
        int flush = PokerHandScorer.scoreHand("2D AD KD JD QD");
        int straight = PokerHandScorer.scoreHand("2S 5D 4D 3D 6D");
        int threeOfAKind = PokerHandScorer.scoreHand("10C 10D KD 10D QD");
        int pair = PokerHandScorer.scoreHand("10C 10D KD JD QD");
        int highCard = PokerHandScorer.scoreHand("10D 2C KD 5D QD");
        int slightlyBetterHighCard = PokerHandScorer.scoreHand("10D 3C KD 5D QD");

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
        assertEquals(0, PokerHandScorer.scoreHand("hgjhdgsagdhsa"));
        assertEquals(0, PokerHandScorer.scoreHand("10D 2C KD 5D"));
        assertEquals(0, PokerHandScorer.scoreHand("10D 2C KD 5D 3C 5C"));
    }

    @Test
    public void getCardsFromString() {
        List<Card> cards = PokerHandScorer.getCardsFromString("9S 10D");
        assertEquals(Card.CardType.NINE, cards.get(0).getType());
        assertEquals(Card.Suit.SPADES, cards.get(0).getSuit());
        assertEquals(Card.CardType.TEN, cards.get(1).getType());
        assertEquals(Card.Suit.DIAMONDS, cards.get(1).getSuit());
    }
}