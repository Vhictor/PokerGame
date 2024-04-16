package org.pokergame;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.Arrays;
import java.util.OptionalInt;
import java.util.stream.IntStream;

import static org.pokergame.PokerHandsEvaluation.evaluateHand;

public class CardHandRank {

    private static final Logger log = LogManager.getLogger(CardHandRank.class);

    /**
     * This method compare two hands rank and if they tie, it further checks the next value and compare.
     * @param playerOneHand The value of the cards in player one hand.
     * @param playerTwoHand The value of the cards in player two hand.
     * @return A negative integer, zero, or a positive integer as player one's rank is less than, equal to, or greater than player two's rank.
     *
     */
    public static int compareHandsRank(String[] playerOneHand, String[] playerTwoHand) {
        int[] playerOneRank = evaluateHand(playerOneHand);
        int[] playerTwoRank = evaluateHand(playerTwoHand);
        if (playerOneRank[0] != playerTwoRank[0]) {
            return Integer.compare(playerOneRank[0], playerTwoRank[0]);
        } else {
            OptionalInt differentIndex = IntStream.range(1, playerOneRank.length)
                    .filter(i -> playerOneRank[i] != playerTwoRank[i])
                    .findFirst();

            if (differentIndex.isPresent()) {
                int index = differentIndex.getAsInt();
                log.info("Player one pair is {} and player two pair is {}", playerOneRank[index], playerTwoRank[index]);
                return Integer.compare(playerOneRank[index], playerTwoRank[index]);
            }
        }
        return 0;
    }


    /**
     * This method returns the value of wins by player one and it takes file name as parameter
     * @param filename The name of the file containing hands data.
     * @return The number of wins by player one.
     */

    public static int countPlayerOneWins(String filename){
        int playerOneWins = 0;
        BufferedReader reader = null;
        InputStream inputStream = null;
        try {
            inputStream = Main.class.getResourceAsStream(filename);
            if (inputStream==null){
                throw new FileNotFoundException("File not founded in the specified path");
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] cards = line.split(" ");
                String[] handOnePlayer = Arrays.copyOfRange(cards, 0, 5);
                String[] handTwoPlayer = Arrays.copyOfRange(cards, 5, 10);
                if (compareHandsRank(handOnePlayer, handTwoPlayer) > 0) {
                    playerOneWins++;
                }
            }
            reader.close();
        }catch (IOException exception){
            throw new  IllegalArgumentException(exception.getMessage());
        }
        return playerOneWins;
    }

}
