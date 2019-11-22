import org.junit.Test;

import static org.junit.Assert.*;

public class CardTest {

    @Test
    public void constructCardWithStrings() {
        Card card = new Card("9C");
        assertEquals(Card.CardRank.NINE, card.getRank());
        assertEquals(Card.Suit.CLUBS, card.getSuit());
        assertTrue(card.isValidCard());

        card = new Card("10D");
        assertEquals(Card.CardRank.TEN, card.getRank());
        assertEquals(Card.Suit.DIAMONDS, card.getSuit());
        assertTrue(card.isValidCard());

        card = new Card("15D");
        assertFalse(card.isValidCard());

        card = new Card("9F");
        assertFalse(card.isValidCard());

        card = new Card("asdsadsa");
        assertFalse(card.isValidCard());
    }

    @Test
    public void getValue() {
        Card card = new Card(Card.Suit.CLUBS, Card.CardRank.ACE);
        assertEquals(card.getCardValue(), 14);
        card = new Card(Card.Suit.CLUBS, Card.CardRank.KING);
        assertEquals(card.getCardValue(), 13);
        card = new Card(Card.Suit.CLUBS, Card.CardRank.QUEEN);
        assertEquals(card.getCardValue(), 12);
        card = new Card(Card.Suit.CLUBS, Card.CardRank.JACK);
        assertEquals(card.getCardValue(), 11);
        card = new Card(Card.Suit.CLUBS, Card.CardRank.TEN);
        assertEquals(card.getCardValue(), 10);
        card = new Card(Card.Suit.CLUBS, Card.CardRank.NINE);
        assertEquals(card.getCardValue(), 9);
        card = new Card(Card.Suit.CLUBS, Card.CardRank.EIGHT);
        assertEquals(card.getCardValue(), 8);
        card = new Card(Card.Suit.CLUBS, Card.CardRank.SEVEN);
        assertEquals(card.getCardValue(), 7);
        card = new Card(Card.Suit.CLUBS, Card.CardRank.SIX);
        assertEquals(card.getCardValue(), 6);
        card = new Card(Card.Suit.CLUBS, Card.CardRank.FIVE);
        assertEquals(card.getCardValue(), 5);
        card = new Card(Card.Suit.CLUBS, Card.CardRank.FOUR);
        assertEquals(card.getCardValue(), 4);
        card = new Card(Card.Suit.CLUBS, Card.CardRank.THREE);
        assertEquals(card.getCardValue(), 3);
        card = new Card(Card.Suit.CLUBS, Card.CardRank.TWO);
        assertEquals(card.getCardValue(), 2);
    }

    @Test
    public void getCardTypeValue() {
        Card card = new Card(Card.Suit.CLUBS, Card.CardRank.ACE);
        assertEquals(card.getRank().getValue(), 14);
        card = new Card(Card.Suit.CLUBS, Card.CardRank.KING);
        assertEquals(card.getRank().getValue(), 13);
        card = new Card(Card.Suit.CLUBS, Card.CardRank.QUEEN);
        assertEquals(card.getRank().getValue(), 12);
        card = new Card(Card.Suit.CLUBS, Card.CardRank.JACK);
        assertEquals(card.getRank().getValue(), 11);
        card = new Card(Card.Suit.CLUBS, Card.CardRank.TEN);
        assertEquals(card.getRank().getValue(), 10);
        card = new Card(Card.Suit.CLUBS, Card.CardRank.NINE);
        assertEquals(card.getRank().getValue(), 9);
        card = new Card(Card.Suit.CLUBS, Card.CardRank.EIGHT);
        assertEquals(card.getRank().getValue(), 8);
        card = new Card(Card.Suit.CLUBS, Card.CardRank.SEVEN);
        assertEquals(card.getRank().getValue(), 7);
        card = new Card(Card.Suit.CLUBS, Card.CardRank.SIX);
        assertEquals(card.getRank().getValue(), 6);
        card = new Card(Card.Suit.CLUBS, Card.CardRank.FIVE);
        assertEquals(card.getRank().getValue(), 5);
        card = new Card(Card.Suit.CLUBS, Card.CardRank.FOUR);
        assertEquals(card.getRank().getValue(), 4);
        card = new Card(Card.Suit.CLUBS, Card.CardRank.THREE);
        assertEquals(card.getRank().getValue(), 3);
        card = new Card(Card.Suit.CLUBS, Card.CardRank.TWO);
        assertEquals(card.getRank().getValue(), 2);
    }

}