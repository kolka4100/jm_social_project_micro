package org.javamentor.social.friend_service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "relationship_status", schema = "public")
public class RelationshipStatus {

    @Id
    @Column(name = "id", nullable = false)
    private PossibleStatus status;

    @Column(name = "status_name", nullable = false)
    private String statusName;

    public RelationshipStatus(final PossibleStatus status) {
        this.status = status;
        this.statusName = status.toString();
    }
}
