package org.javamentor.social.friend_service.model;

public enum PossibleStatus {
    WAIT("WAIT"),
    ACCEPTED("ACCEPTED")
    ;

    private final String text;

    PossibleStatus(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
