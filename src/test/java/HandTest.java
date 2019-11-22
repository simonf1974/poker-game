import org.junit.Test;

import static org.junit.Assert.*;

public class HandTest {
    @Test
    public void getCardsFromString() {
        Hand hand = new Hand("9S 10D");
        assertEquals(Card.CardRank.TEN, hand.getCards().get(0).getRank());
        assertEquals(Card.Suit.DIAMONDS, hand.getCards().get(0).getSuit());
        assertEquals(Card.CardRank.NINE, hand.getCards().get(1).getRank());
        assertEquals(Card.Suit.SPADES, hand.getCards().get(1).getSuit());
    }

    @Test
    public void hasSameSuit() {
        assertFalse(new Hand("AD KS 3D 4D AD").hasSameSuit());
        assertTrue(new Hand("AD KD 3D 4D AD").hasSameSuit());
    }

    @Test
    public void getGroupedCards() {
        assertEquals(new Hand("AD KS 3D 3D AD").getRankVarieties().get(Card.CardRank.THREE).intValue(), 2);
        assertEquals(new Hand("AD KS 3D 3D AD").getRankVarieties().get(Card.CardRank.ACE).intValue(), 2);
        assertEquals(new Hand("AD KS 3D 3D AD").getRankVarieties().get(Card.CardRank.KING).intValue(), 1);

        assertEquals(new Hand("AD AS AD 3D AD").getRankVarieties().get(Card.CardRank.THREE).intValue(), 1);
        assertEquals(new Hand("AD AS AD 3D AD").getRankVarieties().get(Card.CardRank.ACE).intValue(), 4);
    }

    @Test
    public void isStraight() {
        assertFalse(new Hand("AD KS 3D 3D AD").isStraight());
        assertTrue(new Hand("10D 9S 6D 7D 8D").isStraight());
    }

    @Test
    public void getHighestGrouping() {
        assertEquals(3, new Hand("AC AS AD 3D 3C").getOccurrencesOfHighestOccurringRank());
    }


    @Test
    public void getOrderedCardTypes() {
        assertEquals("[TWO, ACE, THREE]", new Hand("AD 3C 2C 2D").getRanksInOrderOfImportance().toString());
        assertEquals("[THREE, TWO, ACE]", new Hand("AD 3C 3D 2D 2H").getRanksInOrderOfImportance().toString());
        assertEquals("[THREE, TWO]", new Hand("3D 3C 3H 2D 2H").getRanksInOrderOfImportance().toString());
    }

}