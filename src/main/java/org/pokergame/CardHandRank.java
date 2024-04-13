package org.pokergame;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class CardHandRank {

    private static final Logger log = LogManager.getLogger(CardHandRank.class);

    public static int compareHandsRank(String[] playerOneHand, String[] playerTwoHand) {
        int[] playerOneRank = evaluateHand(playerOneHand);
        int[] playerTwoRank = evaluateHand(playerTwoHand);
        System.out.println("Player 1 rank: " + Arrays.toString(playerOneRank));
        System.out.println("Player 2 rank: " + Arrays.toString(playerTwoRank));
        if (playerOneRank[0] != playerTwoRank[0]) {
            return Integer.compare(playerOneRank[0], playerTwoRank[0]);

        } else {
            System.out.println("Same ranks but don't know if it is pair or Full house same");
            if (playerOneRank[0] == CardValue.SEVEN.getValue()){
                return 0;
            }
            int playerOnePairValue = findHighestNonPair(playerOneRank,getPairValue(playerOneRank));
            int playerTwoPairValue = findHighestNonPair(playerTwoRank,getPairValue(playerTwoRank));
            System.out.println("Player one pair is " + playerOnePairValue);
            System.out.println("Player two pair is " + playerTwoPairValue);

            if (playerOnePairValue != playerTwoPairValue) {
                return Integer.compare(playerOnePairValue, playerTwoPairValue);
        }else {
            System.out.println("Same ranks but no pair");
            return IntStream.range(1, playerOneRank.length)
                    .map(i -> Integer.compare(playerOneRank[i], playerTwoRank[i]))
                    .filter(result -> result != 0)
                    .findFirst()
                    .orElse(0);
        }
    }
    }


    public static int[] evaluateHand(String[] hand){
        int[] values = new int[5];
        char[] suits = new char[5];

        IntStream.range(0, 5)
                .forEach(i -> {
                    values[i] = CardValue.getValueFromCard(hand[i]);
                    suits[i] = hand[i].charAt(1);
                });

        Arrays.sort(values);

        boolean isStraight = IntStream.range(0, 4)
                .allMatch(i -> values[i] == values[i + 1] - 1);

        boolean isFlush = IntStream.range(0, 4)
                .allMatch(i -> suits[i] == suits[i + 1]);


        if (isStraight && isFlush) {
            return new int[]{9, values[4]};
        } else if (hasNNumberOfAKind(values, 4)) {
            return new int[]{8, values[4], values[0]};
        } else if (hasNNumberOfAKind(values, 3)) {
            if (hasNNumberOfAKind(values, 2)){
                System.out.println("Full house here");
                return new int[]{7, values[4], values[0]};
            }
            System.out.println("Three of a kind here");
            return new int[]{4, values[4], values[3], values[0]};
        } else if (hasNNumberOfAKind(values, 3) && hasNNumberOfAKind(values, 2)) {
            System.out.println("Full house here");
            return new int[]{7, values[4], values[0]};
        } else if (isFlush) {
            return new int[]{6, values[4]};
        } else if (isStraight) {
            return new int[]{5, values[4]};
        } else if (hasTwoPairs(values)) {
            return new int[]{3, values[4], values[2], values[0]};
        } else if (hasNNumberOfAKind(values, 2)) {
            System.out.println("One Pair card");
            return new int[]{2, values[4], values[3], values[2], values[0]};
        } else {
            return new int[]{1, values[4], values[3], values[2], values[1]};
        }
    }

//    private static boolean hasNNumberOfAKind(int[] values, int n) {
//        return IntStream.range(0, 6 - n)
//                .anyMatch(i -> values[i] == values[i + n - 1]);
//    }

    private static boolean hasNNumberOfAKind(int[] values, int n) {
        Map<Integer, Integer> valueCounts = new HashMap<>();
        for (int value : values) {
            valueCounts.put(value, valueCounts.getOrDefault(value, 0) + 1);
        }
        return valueCounts.containsValue(n);
    }

    private static boolean hasTwoPairs(int[] values) {
        long pairsCount = IntStream.range(0, 4)
                .filter(i -> values[i] == values[i + 1])
                .count();

        return pairsCount == 2;
    }

    private static int getPairValue(int[] rank) {
        return IntStream.range(1, rank.length)
                .filter(i -> rank[i] == rank[i - 1])
                .map(i -> rank[i])
                .findFirst()
                .orElse(-1);
    }

    private static int findHighestNonPair(int[] values, int pair) {
        int highestNonPair = Integer.MIN_VALUE;
        for (int value : values) {
            if (value != pair && value > highestNonPair) {
                highestNonPair = value;
            }
        }
        return highestNonPair;
    }
}
