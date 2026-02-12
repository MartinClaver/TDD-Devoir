package test;

import org.junit.jupiter.api.Test;
import poker.Card;
import poker.Rank;
import poker.Suit;

import static org.junit.jupiter.api.Assertions.*;

public class PokerTest {

    @Test
    void testCardCreation() {
        Card card = new Card(Rank.ACE, Suit.SPADES);
        assertEquals(Rank.ACE, card.getRank());
        assertEquals(Suit.SPADES, card.getSuit());
        assertEquals("ACE of SPADES", card.toString());
    }
}