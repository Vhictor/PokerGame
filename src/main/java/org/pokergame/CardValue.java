package org.pokergame;

public enum CardValue {
    ONE('1', 1),
    TWO('2', 2),
    THREE('3', 3),
    FOUR('4', 4),
    FIVE('5', 5),
    SIX('6', 6),
    SEVEN('7', 7),
    EIGHT('8', 8),
    NINE('9', 9),
    TEN('T', 10),
    JACK('J', 11),
    QUEEN('Q', 12),
    KING('K', 13),
    ACE('A', 14);

    private final char symbol;
    private final int value;

    CardValue(char symbol, int value) {
        this.symbol = symbol;
        this.value = value;
    }

    public static int getValueFromCard(String card) {
        char symbol = card.charAt(0);
        for (CardValue cardValue : CardValue.values()) {
            if (cardValue.symbol == symbol) {
                return cardValue.value;
            }
        }
        throw new IllegalArgumentException("Invalid card symbol: " + symbol);
    }

    public int getValue() {
        return this.value;
    }

}