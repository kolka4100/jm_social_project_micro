package org.javamentor.social.friend_service.model;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "friend_relationship", schema = "public")
public class Friends {

    @NonNull
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(name = "inviting_user_id")
    private Long invitingUserId;

    @NonNull
    @Column(name = "invited_user_id")
    private Long invitedUserId;

    @Column(name = "relationship_status")
    private String relationship;

    public Friends(@NonNull Long invitingUserId,
                   @NonNull Long invitedUserId, String relationship) {
        this.invitingUserId = invitingUserId;
        this.invitedUserId = invitedUserId;
        this.relationship = relationship;
    }

    public Friends(@NonNull Long invitingUserId,
                   @NonNull Long invitedUserId) {
        this.invitingUserId = invitingUserId;
        this.invitedUserId = invitedUserId;
        this.relationship = Relationship.WAIT.toString();
    }
}
