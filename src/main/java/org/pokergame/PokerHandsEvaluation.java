package org.pokergame;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class PokerHandsEvaluation {

    /**
     * This method evaluates hands based on Poker hand rules and return the rank and other parameters in case of tie
     * @param hand The hand to be evaluated.
     * @return An array representing the rank and other parameters of the hand
     */
    public static int[] evaluateHand(String[] hand){
        int[] cardValues = new int[5];
        char[] cardSuits = new char[5];

        IntStream.range(0, 5)
                .forEach(i -> {
                    cardValues[i] = CardValue.getValueFromCard(hand[i]);
                    cardSuits[i] = hand[i].charAt(1);
                });

        Arrays.sort(cardValues);

        boolean isStraight = IntStream.range(0, 4)
                .allMatch(i -> cardValues[i] == cardValues[i + 1] - 1);

        boolean isFlush = IntStream.range(0, 4)
                .allMatch(i -> cardSuits[i] == cardSuits[i + 1]);


        if (isStraight && isFlush) {
            if (cardValues[0] == CardValue.TEN.getValue() && cardValues[4] == CardValue.ACE.getValue()) {
                return new int[]{CardValue.TEN.getValue(), 14}; // Royal Flush
            }
            return new int[]{CardValue.NINE.getValue(), cardValues[4]};
        } else if (hasNNumberOfAKind(cardValues, 4)) {
            return new int[]{CardValue.EIGHT.getValue(), cardValues[4], cardValues[0]};
        } else if (hasNNumberOfAKind(cardValues, 3)) {
            if (hasNNumberOfAKind(cardValues, 2)){
                return resultForFullHouse(cardValues);
            }
            return resultForThreeOfAKind(cardValues);
        } else if (isFlush) {
            return new int[]{CardValue.SIX.getValue(), cardValues[4]};
        } else if (isStraight) {
            return new int[]{CardValue.FIVE.getValue(), cardValues[4]};
        } else if (hasTwoPairs(cardValues)) {
            return new int[]{CardValue.THREE.getValue(), cardValues[4], cardValues[2], cardValues[0]};
        } else if (hasNNumberOfAKind(cardValues, 2)) {
            return resultForOnePair(cardValues);
        } else {
           return new int[]{CardValue.ONE.getValue(), cardValues[4], cardValues[3], cardValues[2], cardValues[1]};
        }
    }

    /**
     * This method handles the arrays returned for Three of a Kind, the rank, the triplet value and other parameters
     * @param values The values of the cards in the hand.
     * @return An array representing the rank and other parameters of the hand
     */
    private static int[] resultForThreeOfAKind(int[] values) {
        int tripletValue = getTripletValue(values);
        int[] remainingCards = getRemainingCards(values, tripletValue);

        return IntStream.concat(
                IntStream.of(CardValue.FOUR.getValue(), tripletValue),
                Arrays.stream(remainingCards)
        ).toArray();

    }

    /**
     * This handles the result of one pair ensuring the arrays is arranged for comparison in case of tie
     * @param values The values of the cards in the hand.
     * @return An array representing the rank and other parameters of the hand
     */
    private static int[] resultForOnePair(int[] values) {
        int pairValue = getPairValue(values);
        int highestNonPair = findHighestNonPair(values, pairValue);
        int[] remainingCards = getRemainingCards(values, pairValue);

        return IntStream.concat(
                IntStream.of(CardValue.TWO.getValue(), pairValue, highestNonPair),
                Arrays.stream(remainingCards, 0, 2)
        ).toArray();
    }

    /**
     * This handles the result for a full house hand
     * @param values The values of the cards in the hand.
     * @return An array representing the rank and other parameters of the hand
     */

    private static int[] resultForFullHouse(int[] values) {
        int tripletValue = getTripletValue(values);
        int[] remainingCards = getRemainingCards(values, tripletValue);

        return IntStream.concat(
                IntStream.of(CardValue.SEVEN.getValue(), tripletValue),
                Arrays.stream(remainingCards, 0, 1)
        ).toArray();
    }


    /**
     * Used to get remaining cards in the hands after identifying pair value
     * @param values The values of the cards in the hand.
     * @param pairValue The value of the pair in the hand.
     * @return An array representing other parameters of the hand
     */
    private static int[] getRemainingCards(int[] values, int pairValue) {
        return Arrays.stream(values)
                .filter(value -> value != pairValue)
                .toArray();
    }


    /**
     * This checks the number of times value "n" is repeated in the arrays.
     * @param values The value of the card in the hand.
     * @param n the number of times to validate against
     * @return Returns true if the value appears n times
     */
    private static boolean hasNNumberOfAKind(int[] values, int n) {
        Map<Integer, Integer> valueCounts = new HashMap<>();
        for (int value : values) {
            valueCounts.put(value, valueCounts.getOrDefault(value, 0) + 1);
        }
        return valueCounts.containsValue(n);
    }


    /**
     * The checks if the array has two pairs
     * @param values The value of the card in the hand.
     * @return Returns true if it has two pairs
     */
    private static boolean hasTwoPairs(int[] values) {
        long pairsCount = IntStream.range(0, 4)
                .filter(i -> values[i] == values[i + 1])
                .count();

        return pairsCount == 2;
    }


    /**
     * Used for getting triplet value needed for Three of a kind
     * @param rank The value of the card in the hand.
     * @return Returns the value of the card that appear 3 times
     */
    private static int getTripletValue(int[] rank) {
        return IntStream.range(2, rank.length)
                .filter(i -> rank[i] == rank[i - 1] && rank[i] == rank[i - 2])
                .map(i -> rank[i])
                .findFirst()
                .orElse(-1);
    }

    /**
     * This method returns pair value in the hand
     * @param rank The value of the card in the hand.
     * @return Returns the pair value
     */


    private static int getPairValue(int[] rank) {
        return IntStream.range(1, rank.length)
                .filter(i -> rank[i] == rank[i - 1])
                .map(i -> rank[i])
                .findFirst()
                .orElse(-1);
    }



    /**
     * This method get the highest non-pair value in a player's hand
     * @param values The value of the card in the hand.
     * @param pair The value of the pair in the hand.
     * @return Returns the highest non pair value
     */
    private static int findHighestNonPair(int[] values, int pair) {
        return Arrays.stream(values)
                .filter(value -> value != pair)
                .max()
                .orElse(Integer.MIN_VALUE);
    }
}
