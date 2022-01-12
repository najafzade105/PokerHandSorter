package com.el.test.pokerhandsorter.model;

public enum PokerRankEnum {
    HIGH_CARD(1),               //used when ties
    PAIR(2),                    //two of a kind
    TWO_PAIRS(3),               //two of a kind + two of a kind
    THREE_OF_A_KIND(4),         //three of a kind
    STRAIGHT(5),                //consecutive value
    FLUSH(6),                   //same suit
    FULL_HOUSE(7),              //three of a kind + two of a kind
    FOUR_OF_A_KIND(8),          //four of a kind
    STRAIGHT_FLUSH(9),          //straight && flush
    ROYAL_FLUSH(10);            //flush && high card starting from ACE

    private int value;

    PokerRankEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static PokerRankEnum getPokerRankEnum(int value) {
        switch (value) {
            case 1:
                return PokerRankEnum.HIGH_CARD;
            case 2:
                return PokerRankEnum.PAIR;
            case 3:
                return PokerRankEnum.TWO_PAIRS;
            case 4:
                return PokerRankEnum.THREE_OF_A_KIND;
            case 5:
                return PokerRankEnum.STRAIGHT;
            case 6:
                return PokerRankEnum.FLUSH;
            case 7:
                return PokerRankEnum.FULL_HOUSE;
            case 8:
                return PokerRankEnum.FOUR_OF_A_KIND;
            case 9:
                return PokerRankEnum.STRAIGHT_FLUSH;
            case 10:
                return PokerRankEnum.ROYAL_FLUSH;
            default:
                throw new RuntimeException("Value is not supported!");
        }
    }
}
