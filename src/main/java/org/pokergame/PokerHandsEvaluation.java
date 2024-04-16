package org.pokergame;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class PokerHandsEvaluation {

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

                int pairValue = getTripletValue(values);
                int[] remainingCards = Arrays.stream(values)
                        .filter(value -> value != pairValue)
                        .toArray();
                int[] result = new int[3];
                result[0] = 7; // Hand rank for one pair
                result[1] = pairValue; // Pair value
                System.arraycopy(remainingCards, 0, result, 2, 1); // Copy remaining cards
                return result;
            }
            System.out.println("Three of a kind here");
            return new int[]{4, values[4], values[3], values[0]};
        } else if (isFlush) {
            return new int[]{6, values[4]};
        } else if (isStraight) {
            return new int[]{5, values[4]};
        } else if (hasTwoPairs(values)) {
            return new int[]{3, values[4], values[2], values[0]};
        } else if (hasNNumberOfAKind(values, 2)) {
            System.out.println("One Pair card with pair value " + getPairValue(values));

            int pairValue = getPairValue(values);
            int[] remainingCards = Arrays.stream(values)
                    .filter(value -> value != pairValue)
                    .toArray();

            int[] result = new int[5];
            result[0] = 2; // Hand rank for one pair
            result[1] = pairValue; // Pair value
            result[2] = findHighestNonPair(values,pairValue);
            System.arraycopy(remainingCards, 0, result, 3, 2); // Copy remaining cards
            return result;
            //return new int[]{2, getPairValue(values), values[3], values[2], values[0]};
        } else {
            return new int[]{1, values[4], values[3], values[2], values[1]};
        }
    }



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


    private static int getTripletValue(int[] rank) {
        return IntStream.range(2, rank.length)
                .filter(i -> rank[i] == rank[i - 1] && rank[i] == rank[i - 2])
                .map(i -> rank[i])
                .findFirst()
                .orElse(-1);
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
