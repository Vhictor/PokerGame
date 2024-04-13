package org.pokergame;

import java.io.*;
import java.util.Arrays;

import static org.pokergame.CardHandRank.compareHandsRank;

public class Wins {

    public static int countPlayerOneWins(String filename){
        int playerOneWins = 0;
        BufferedReader reader = null;
        InputStream inputStream = null;
        try {
            inputStream = Main.class.getResourceAsStream(filename);
            if (inputStream==null){
                return 0;
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
            throw new  IllegalArgumentException (exception.getMessage());
        }
        return playerOneWins;
    }

}
