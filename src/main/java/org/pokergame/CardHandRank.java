package org.pokergame;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import static org.pokergame.PokerHandsEvaluation.evaluateHand;

public class CardHandRank {

    private static final Logger log = LogManager.getLogger(CardHandRank.class);

    public static int compareHandsRank(String[] playerOneHand, String[] playerTwoHand) {
        int[] playerOneRank = evaluateHand(playerOneHand);
        int[] playerTwoRank = evaluateHand(playerTwoHand);
        if (playerOneRank[0] != playerTwoRank[0]) {
            return Integer.compare(playerOneRank[0], playerTwoRank[0]);

        } else {
            for (int i = 1; i < playerOneRank.length ; i++) {
                if(playerOneRank[i] != playerTwoRank[i]){
                    log.info("Player one pair is {} and player two pair is {}", playerOneRank[i], playerTwoRank[i]);
                    return Integer.compare(playerOneRank[i], playerTwoRank[i]);
                }
            }
    }
    return  0;
    }




}
