# Poker Game

This project implements a Poker Game in Java. It evaluates poker hands and determines the winner based on traditional poker rules.

## Table of Contents

- [Technology Used](#technology-used)
- [Rules of Poker](#rules-of-poker)
- [Data](#data)
- [Project Goal](#project-goal)
- [Usage](#usage)

## Technology Used

- **Java Version**: v21.
- **Maven**.
- **Junit Jupiter for Testing**: 5.10.2.
- **Apache Logging**: 2.23.1.


## Rules of Poker

In the card game poker, a hand consists of five cards and is ranked according to the following hierarchy:

- **High Card**: Highest value card.
- **One Pair**: Two cards of the same value.
- **Two Pairs**: Two different pairs.
- **Three of a Kind**: Three cards of the same value.
- **Straight**: All cards are consecutive values.
- **Flush**: All cards of the same suit.
- **Full House**: Three of a kind and a pair.
- **Four of a Kind**: Four cards of the same value.
- **Straight Flush**: All cards are consecutive values of the same suit.
- **Royal Flush**: Ten, Jack, Queen, King, Ace, in the same suit.

The cards are valued in the order: 2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen, King, Ace.

If two players have the same ranked hands, the rank made up of the highest value wins. If ranks tie, the highest cards in each hand are compared, and so on.

## Data

The `poker.txt` file contains one-thousand random hands dealt to two players located in ""main/resources"" path. Each line of the file contains ten cards separated by a single space: the first five are Player 1's cards, and the last five are Player 2's cards. All hands are valid, each player's hand is in no specific order, and in each hand, there is a clear winner.

## Project Goal

The goal of this project is to determine how many hands Player 1 wins out of the one-thousand random hands provided in the `poker.txt` file.

## Usage

To run the Poker Game and determine the number of hands Player 1 wins:

1. Clone the repository:

    ```
    git clone https://github.com/vhictor/pokergame.git
    ```

2. Navigate to the project directory:

    ```
    cd poker-game
    ```

3. Compile the Java source files:

    ```
    javac *.java
    ```

4. Run the main Java class:

    ```
    java Main
    ```

5. The program will read the `poker.txt` file, evaluate the hands, determine the winner, and display the number of hands Player 1 wins.
