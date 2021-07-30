package org.javamentor.social.friend_service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
public enum Relationship {

    WAIT("WAIT"),
    ACCEPTED("ACCEPTED")
            ;

    private final String text;

    Relationship(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
