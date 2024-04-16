package org.pokergame;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CardHandRankTest {
    @Test
    public void testEvaluatingHand() {
        String[] straightFlushHand = {"2H", "3H", "4H", "5H", "6H"};
        String[] fourOfAKind = {"2H", "2C", "2D", "2S", "7H"};
        String[] handWithTwoPair = {"2H", "2C", "3D", "3S", "4H"};

        int[] expectedStraightFlush = {9, 6}; // Straight Flush: Rank 9
        int[] expectedFourOfAKind = {8, 7, 2}; // Four of a Kind: Rank 8
        int[] expectedHandWithTwoPair = {3, 4, 3, 2}; // Two Pair: Rank 3

        assertArrayEquals(expectedStraightFlush, PokerHandsEvaluation.evaluateHand(straightFlushHand));
        assertArrayEquals(expectedFourOfAKind, PokerHandsEvaluation.evaluateHand(fourOfAKind));
        assertArrayEquals(expectedHandWithTwoPair, PokerHandsEvaluation.evaluateHand(handWithTwoPair));
    }

    @Test
    public void testCompareHandsRankHighestCardWins() {
        String[] player1Hand = {"5D", "8C", "9S", "JS", "AC"};
        String[] player2Hand = {"2C", "5C", "7D", "8S", "QH"};
        int result = CardHandRank.compareHandsRank(player1Hand, player2Hand);
        assertEquals(1, result, "Player 1 should Ace");
    }

    @Test
    public void testCompareHandsRankThreeAcesAndFlushWithDiamonds() {
        String[] player1Hand = {"2D", "9C", "AS", "AH", "AC"};
        String[] player2Hand = {"3D", "6D", "7D", "QD", "QD"};
        int result = CardHandRank.compareHandsRank(player1Hand, player2Hand);
        assertEquals(-1, result, "Player 2 should win with flush of Diamonds");
    }

    @Test
    public void testCompareHandsRankWithPairsOfQueensAndHighestCardWins() {
        String[] player1Hand = {"4D", "6S", "9H", "QH", "QC"};
        String[] player2Hand = {"3D", "6D", "7H", "QD", "QS"};
        int result = CardHandRank.compareHandsRank(player1Hand, player2Hand);
        assertEquals(1, result, "Player 1 should win with highest card being 9");
    }

    @Test
    public void testCompareHandsRankWithTwoFullHouseAndThreeFoursWins() {
        String[] player1Hand = {"2H", "2D", "4C", "4D", "4S"};
        String[] player2Hand = {"3C", "3D", "3S", "9S", "9D"};
        int result = CardHandRank.compareHandsRank(player1Hand, player2Hand);
        assertEquals(1, result, "Player 1 should win with Three Fours even though they are both full house");
    }


    @Test
    public void testCompareHandsRankPlayerOneWinsWithAce() {
        String[] player1Hand = {"2H", "3H", "5H", "8H", "AH"};
        String[] player2Hand = {"2C", "3C", "4C", "8C", "KC"};
        int result = CardHandRank.compareHandsRank(player1Hand, player2Hand);
        assertEquals(1, result, "Player 1 should win Ace since both are Flush");
    }

}