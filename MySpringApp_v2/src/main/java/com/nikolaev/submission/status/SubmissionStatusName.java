package com.nikolaev.submission.status;

public enum SubmissionStatusName {
    PENDING(0), REJECT(1), ACCEPT(2);

    private int value;

    SubmissionStatusName(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static SubmissionStatusName fromInt(int i) {
        for (SubmissionStatusName name : SubmissionStatusName.values()) {
            if (name.getValue() == i) {
                return name;
            }
        }
        return null;
    }
}
