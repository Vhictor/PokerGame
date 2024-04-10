package org.pokergame;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class CardHandRankTest {
    @Test
    public void testEvaluatingHand() {
        String[] straightFlushHand = {"2H", "3H", "4H", "5H", "6H"};
        String[] fourOfAKind = {"2H", "2C", "2D", "2S", "7H"};
        String[] handWithTwoPair = {"2H", "2C", "3D", "3S", "4H"};

        int[] expectedStraightFlush = {9, 6}; // Straight Flush: Rank 9
        int[] expectedFourOfAKind = {8, 7, 2}; // Four of a Kind: Rank 8
        int[] expectedHandWithTwoPair = {3, 4, 3, 2}; // Two Pair: Rank 3

        assertArrayEquals(expectedStraightFlush, CardHandRank.evaluateHand(straightFlushHand));
        assertArrayEquals(expectedFourOfAKind, CardHandRank.evaluateHand(fourOfAKind));
        assertArrayEquals(expectedHandWithTwoPair, CardHandRank.evaluateHand(handWithTwoPair));
    }


}