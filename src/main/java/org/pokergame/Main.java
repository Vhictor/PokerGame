package org.pokergame;

public class Main {
    public static void main(String[] args) {
        String filename = "/poker.txt";
        System.out.println("Player 1 wins: " + Wins.countPlayerOneWins(filename));
    }
}