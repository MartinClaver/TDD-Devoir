package test;

import main.poker.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PokerTest {

    @Test
    void testCardCreation() {
        Card card = new Card(Rank.ACE, Suit.SPADES);
        assertEquals(Rank.ACE, card.getRank());
        assertEquals(Suit.SPADES, card.getSuit());
        assertEquals("ACE of SPADES", card.toString());
    }

    @Test
    void shouldReturnHighCardCategory() {
        List<Card> hole = List.of(new Card(Rank.ACE, Suit.CLUBS), new Card(Rank.TWO, Suit.DIAMONDS));
        List<Card> board = List.of(
                new Card(Rank.FOUR, Suit.SPADES),
                new Card(Rank.SIX, Suit.HEARTS),
                new Card(Rank.EIGHT, Suit.CLUBS),
                new Card(Rank.TEN, Suit.SPADES),
                new Card(Rank.JACK, Suit.HEARTS)
        );

        HandEvaluator evaluator = new HandEvaluator();
        BestHand result = evaluator.getBestHand(hole, board);
        List<Card> expectedHand = List.of(
                new Card(Rank.ACE, Suit.CLUBS),
                new Card(Rank.JACK, Suit.HEARTS),
                new Card(Rank.TEN, Suit.SPADES),
                new Card(Rank.EIGHT, Suit.CLUBS),
                new Card(Rank.SIX, Suit.HEARTS)
                );

        assertEquals(HandCategory.HIGH_CARD, result.getCategory());
        assertEquals(Rank.ACE, result.getChosen5().get(0).getRank());
        assertEquals(expectedHand, result.getChosen5());
    }
}