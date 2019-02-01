package com.nikolaev.conference_role;

public enum ConferenceRoleName {
    SUBMITTER(0),
    REVIEWER(1),
    CREATOR(2),
    ADMIN(3);

    private int value;

    ConferenceRoleName(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ConferenceRoleName fromInt(int i) {
        for (ConferenceRoleName name : ConferenceRoleName.values()) {
            if (name.getValue() == i) {
                return name;
            }
        }
        return null;
    }
}
