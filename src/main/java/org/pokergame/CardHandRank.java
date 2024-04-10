package org.pokergame;

import java.util.Arrays;
import java.util.stream.IntStream;

public class CardHandRank {
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
        } else if (hasNNumberOfAKind(values, 3) && hasNNumberOfAKind(values, 2)) {
            return new int[]{7, values[4], values[0]};
        } else if (isFlush) {
            return new int[]{6, values[4]};
        } else if (isStraight) {
            return new int[]{5, values[4]};
        } else if (hasNNumberOfAKind(values, 3)) {
            return new int[]{4, values[4], values[3], values[0]};
        } else if (hasTwoPairs(values)) {
            return new int[]{3, values[4], values[2], values[0]};
        } else if (hasNNumberOfAKind(values, 2)) {
            return new int[]{2, values[4], values[3], values[2], values[0]};
        } else {
            return new int[]{1, values[4], values[3], values[2], values[1]};
        }
    }

    private static boolean hasNNumberOfAKind(int[] values, int n) {
        return IntStream.range(0, 6 - n)
                .anyMatch(i -> values[i] == values[i + n - 1]);
    }

    private static boolean hasTwoPairs(int[] values) {
        long pairsCount = IntStream.range(0, 4)
                .filter(i -> values[i] == values[i + 1])
                .count();

        return pairsCount == 2;
    }
}
