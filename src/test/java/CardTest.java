import org.junit.Test;

import static org.junit.Assert.*;

public class CardTest {

    @Test
    public void getValue() {
        Card card = new Card(Card.Suit.CLUBS, Card.CardType.ACE);
        assertEquals(card.getValue(), 14);
        card = new Card(Card.Suit.CLUBS, Card.CardType.KING);
        assertEquals(card.getValue(), 13);
        card = new Card(Card.Suit.CLUBS, Card.CardType.QUEEN);
        assertEquals(card.getValue(), 12);
        card = new Card(Card.Suit.CLUBS, Card.CardType.JACK);
        assertEquals(card.getValue(), 11);
        card = new Card(Card.Suit.CLUBS, Card.CardType.TEN);
        assertEquals(card.getValue(), 10);
        card = new Card(Card.Suit.CLUBS, Card.CardType.NINE);
        assertEquals(card.getValue(), 9);
        card = new Card(Card.Suit.CLUBS, Card.CardType.EIGHT);
        assertEquals(card.getValue(), 8);
        card = new Card(Card.Suit.CLUBS, Card.CardType.SEVEN);
        assertEquals(card.getValue(), 7);
        card = new Card(Card.Suit.CLUBS, Card.CardType.SIX);
        assertEquals(card.getValue(), 6);
        card = new Card(Card.Suit.CLUBS, Card.CardType.FIVE);
        assertEquals(card.getValue(), 5);
        card = new Card(Card.Suit.CLUBS, Card.CardType.FOUR);
        assertEquals(card.getValue(), 4);
        card = new Card(Card.Suit.CLUBS, Card.CardType.THREE);
        assertEquals(card.getValue(), 3);
        card = new Card(Card.Suit.CLUBS, Card.CardType.TWO);
        assertEquals(card.getValue(), 2);
        card = new Card(Card.Suit.CLUBS, Card.CardType.ONE);
        assertEquals(card.getValue(), 1);
    }

}