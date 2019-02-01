package com.nikolaev.conference_request.status;

public enum ConferenceRequestStatusName {
    PENDING(0), DECLINED(1), ACCEPTED(2);

    private int value;

    ConferenceRequestStatusName(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ConferenceRequestStatusName fromInt(int i) {
        for (ConferenceRequestStatusName name : ConferenceRequestStatusName.values()) {
            if (name.getValue() == i) {
                return name;
            }
        }
        return null;
    }
}
