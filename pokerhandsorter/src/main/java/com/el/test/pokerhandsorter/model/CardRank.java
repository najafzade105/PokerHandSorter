package com.el.test.pokerhandsorter.model;

public enum CardRank {
    TWO('2', 1),
    THREE('3', 2),
    FOUR('4', 3),
    FIVE('5', 4),
    SIX('6', 5),
    SEVEN('7', 6),
    EIGHT('8', 7),
    NINE('9', 8),
    TEN('T', 9),
    JACK('J', 10),
    QUEEN('Q', 11),
    KING('K', 12),
    ACE('A', 13);

    private char display;
    private int value;

    CardRank(char display, int value) {
        this.display = display;
        this.value = value;
    }

    public char getDisplay() {
        return display;
    }

    public int getValue() {
        return value;
    }

    public static int getValueFromDisplay(char display) {
        switch (display) {
            case '2':
                return TWO.value;
            case '3':
                return THREE.value;
            case '4':
                return FOUR.value;
            case '5':
                return FIVE.value;
            case '6':
                return SIX.value;
            case '7':
                return SEVEN.value;
            case '8':
                return EIGHT.value;
            case '9':
                return NINE.value;
            case 'T':
                return TEN.value;
            case 'J':
                return JACK.value;
            case 'Q':
                return QUEEN.value;
            case 'K':
                return KING.value;
            case 'A':
                return ACE.value;
            default:
                throw new RuntimeException("Invalid Card Rank!");
        }
    }
}
