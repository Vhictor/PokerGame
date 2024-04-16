package org.pokergame;

public class Main {
    public static void main(String[] args) {
        getPokerResultForPlayerOne();
    }


    /*
    poker.txt file is located in the resource folder.
     */
    public static void getPokerResultForPlayerOne(){
        String filename = "/poker.txt";
        System.out.println("Player 1 wins: " + Wins.countPlayerOneWins(filename));
    }
}