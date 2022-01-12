package com.el.test.pokerhandsorter.model;

public enum CardValueEnum {
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

    private char display;
    private int value;

    CardValueEnum(char display, int value) {
        this.display = display;
        this.value = value;
    }

    public char getDisplay() {
        return display;
    }

    public int getValue() {
        return value;
    }

    public static int getValue(char display) {
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
