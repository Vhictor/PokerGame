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

        assertArrayEquals(expectedStraightFlush, CardHandRank.evaluateHand(straightFlushHand));
        assertArrayEquals(expectedFourOfAKind, CardHandRank.evaluateHand(fourOfAKind));
        assertArrayEquals(expectedHandWithTwoPair, CardHandRank.evaluateHand(handWithTwoPair));
    }

    @Disabled
    @Test
    public void testCompareHandsRank_Player1WinsWithHighCard() {
        String[] player1Hand = {"5H", "5C", "6S", "7S", "KD"};
        String[] player2Hand = {"2C", "3S", "8S", "8D", "TD"};
        int result = CardHandRank.compareHandsRank(player1Hand, player2Hand);
        assertEquals(-1, result, "Player 2 should win with pairs of 8");
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

    @Disabled
    @Test
    public void testCompareHandsRankWithTwoFullHouseAndThreeFoursWins() {
        String[] player1Hand = {"2H", "2D", "4C", "4D", "4S"};
        String[] player2Hand = {"3C", "3D", "3S", "9S", "9D"};
        int result = CardHandRank.compareHandsRank(player1Hand, player2Hand);
        assertEquals(1, result, "Player 1 should win with Three Fours even though they are both full house");
    }


    @Test
    public void testCompareHandsRank_Player2WinsWithPair() {
        String[] player1Hand = {"2H", "3D", "5S", "8C", "AH"};
        String[] player2Hand = {"2C", "2D", "4S", "8D", "KD"};
        int result = CardHandRank.compareHandsRank(player1Hand, player2Hand);
        assertEquals(-1, result, "Player 2 should win with a pair of 2s");
    }

    @Test
    public void testCompareHandsRank_TieWithEqualHighCard() {
        String[] player1Hand = {"2H", "3D", "5S", "8C", "AH"};
        String[] player2Hand = {"2C", "3H", "5D", "8D", "AS"};
        int result = CardHandRank.compareHandsRank(player1Hand, player2Hand);
        assertEquals(0, result, "It should be a tie with equal high card");
    }

    @Test
    public void testCompareHandsRank_Player1WinsWithFlush() {
        String[] player1Hand = {"2H", "3H", "5H", "8H", "AH"};
        String[] player2Hand = {"2C", "3C", "4C", "8C", "KC"};
        int result = CardHandRank.compareHandsRank(player1Hand, player2Hand);
        assertEquals(1, result, "Player 1 should win with a flush");
    }

    @Test
    public void testCompareHandsRank_Player1WinsWithStraight() {
        String[] player1Hand = {"2H", "3D", "4S", "5C", "6H"};
        String[] player2Hand = {"2C", "3H", "4D", "5S", "7S"};
        int result = CardHandRank.compareHandsRank(player1Hand, player2Hand);
        assertEquals(1, result, "Player 1 should win with a straight");
    }

    @Test
    public void testCompareHandsRank_Player2WinsWithThreeOfAKind() {
        String[] player1Hand = {"2H", "2D", "2S", "5C", "6H"};
        String[] player2Hand = {"3C", "3H", "3D", "8S", "9S"};
        int result = CardHandRank.compareHandsRank(player1Hand, player2Hand);
        assertEquals(-1, result, "Player 2 should win with three of a kind");
    }

    @Test
    public void testTwoRanksTieHighestCardValueWins() {
        String[] player1Hand = {"4D", "6S", "9H", "QH", "QC"};
        String[] player2Hand = {"3D", "6D", "7H", "QD", "QS"};
        int result = CardHandRank.compareHandsRank(player1Hand, player2Hand);
        assertEquals(1, result, "Player one should win because highest card is 9");
    }


    @Test
    public void testCompareHandsRank_Player2WinsWithFullHouse() {
        String[] player1Hand = {"2H", "2D", "2S", "3C", "3H"};
        String[] player2Hand = {"AC", "AH", "4D", "2S", "3D"};
        int result = CardHandRank.compareHandsRank(player1Hand, player2Hand);
        assertEquals(1, result, "Player 1 should win with a full house");
    }

    @Disabled
    @Test
    public void testCompareHandsRank_Player2WinsWithFullHouseAndHighestValue() {
        String[] player1Hand = {"2H", "2D", "2S", "3C", "3H"};
        String[] player2Hand = {"AC", "AH", "AD", "3S", "3D"};
        int result = CardHandRank.compareHandsRank(player1Hand, player2Hand);
        assertEquals(-1, result, "Player 1 should win with highest value " +
                "even though they are both full house");
    }

    @Test
    public void testCompareHandsRank_Player2WinsWithFourOfAKind() {
        String[] player1Hand = {"2H", "2D", "2S", "2C", "3H"};
        String[] player2Hand = {"AC", "AH", "AD", "AS", "3D"};
        int result = CardHandRank.compareHandsRank(player1Hand, player2Hand);
        assertEquals(-1, result, "Player 2 should win with four of a kind");
    }

    @Test
    public void testCompareHandsRank_TieWithStraightFlush() {
        String[] player1Hand = {"2H", "3H", "4H", "5H", "6H"};
        String[] player2Hand = {"2C", "3C", "4C", "5C", "6C"};
        int result = CardHandRank.compareHandsRank(player1Hand, player2Hand);
        assertEquals(0, result, "It should be a tie with straight flush");
    }

    @Test
    public void testCompareHandsRank_Player1WinsWithRoyalFlush() {
        String[] player1Hand = {"TH", "JH", "QH", "KH", "AH"};
        String[] player2Hand = {"2C", "3C", "4C", "KC", "AC"};
        int result = CardHandRank.compareHandsRank(player1Hand, player2Hand);
        assertEquals(1, result, "Player 1 should win with royal flush");
    }




}