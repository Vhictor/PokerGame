package org.pokergame;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PokerHandsEvaluationTest {

    @Test
    public void testEvaluatingHand() {
        String[] straightFlushHand = {"2H", "3H", "4H", "5H", "6H"};
        String[] fourOfAKind = {"2H", "2C", "2D", "2S", "7H"};
        String[] handWithTwoPair = {"2H", "2C", "3D", "3S", "4H"};
        String[] onePairHand = {"2H", "2C", "3D", "4S", "5H"};
        String[] highCardHand = {"2H", "3C", "4D", "5S", "7H"};
        String[] flushHand = {"2H", "4H", "6H", "8H", "TH"};
        String[] straightHand = {"2H", "3C", "4D", "5S", "6H"};
        String[] fullHouseHand = {"2H", "2C", "3D", "3S", "3H"};
        String[] threeOfAKindHand = {"2H", "2C", "2D", "5S", "7H"};
        String[] royalFlushHand = {"TD", "JD", "QD", "KD", "AD"};


        int[] expectedStraightFlush = {9, 6}; // Straight Flush: Rank 9
        int[] expectedFourOfAKind = {8, 7, 2}; // Four of a Kind: Rank 8
        int[] expectedHandWithTwoPair = {3, 4, 3, 2}; // Two Pair: Rank 3
        int[] expectedOnePair = {2, 2, 5, 3, 4}; // One Pair: Rank 2
        int[] expectedHighCard = {1, 7, 5, 4, 3}; // High Card: Rank 1
        int[] expectedFlush = {6, 10}; // Flush: Rank 6
        int[] expectedStraight = {5, 6}; // Straight: Rank 5
        int[] expectedFullHouse = {7, 3, 2}; // Full House: Rank 7
        int[] expectedThreeOfAKind = {4, 2, 5, 7}; // Three of a Kind: Rank 4
        int[] expectedRoyalFlush = {10, 14}; // Royal Flush: Rank 10



        assertArrayEquals(expectedStraightFlush, PokerHandsEvaluation.evaluateHand(straightFlushHand));
        assertArrayEquals(expectedFourOfAKind, PokerHandsEvaluation.evaluateHand(fourOfAKind));
        assertArrayEquals(expectedHandWithTwoPair, PokerHandsEvaluation.evaluateHand(handWithTwoPair));
        assertArrayEquals(expectedOnePair, PokerHandsEvaluation.evaluateHand(onePairHand));
        assertArrayEquals(expectedHighCard, PokerHandsEvaluation.evaluateHand(highCardHand));
        assertArrayEquals(expectedFlush, PokerHandsEvaluation.evaluateHand(flushHand));
        assertArrayEquals(expectedStraight, PokerHandsEvaluation.evaluateHand(straightHand));
        assertArrayEquals(expectedThreeOfAKind, PokerHandsEvaluation.evaluateHand(threeOfAKindHand));
        assertArrayEquals(expectedFullHouse, PokerHandsEvaluation.evaluateHand(fullHouseHand));
        assertArrayEquals(expectedRoyalFlush, PokerHandsEvaluation.evaluateHand(royalFlushHand));
    }
}