package com.example.szkolenie.entities;

public enum Status {
    OPEN("Open"),
    CLOSED("Closed"),
    REJECTED("Rejected"),
    IN_PROGRESS("In Progress");

    private String displayName;

    Status(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}