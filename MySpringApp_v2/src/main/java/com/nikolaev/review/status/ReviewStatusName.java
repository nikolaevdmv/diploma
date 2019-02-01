package com.nikolaev.review.status;

public enum  ReviewStatusName {
    REJECT(0), PROBABLY_REJECT(1), NO_DECISION(2), PROBABLY_ACCEPT(3), ACCEPT(4);

    private int value;

    ReviewStatusName(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ReviewStatusName fromInt(int i) {
        for (ReviewStatusName name : ReviewStatusName.values()) {
            if (name.getValue() == i) {
                return name;
            }
        }
        return null;
    }
}
