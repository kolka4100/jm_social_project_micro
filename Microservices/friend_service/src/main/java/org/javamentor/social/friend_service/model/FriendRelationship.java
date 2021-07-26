package org.javamentor.social.friend_service.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "friend_relationship", schema = "public")
public class FriendRelationship{

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

    @ManyToOne
    @JoinColumn(name = "relationship_status_id")
    private  RelationshipStatus relationshipStatus;

    public FriendRelationship(@NonNull Long invitingUserId,
                   @NonNull Long invitedUserId, RelationshipStatus relationshipStatus) {
        this.invitingUserId = invitingUserId;
        this.invitedUserId = invitedUserId;
        this.relationshipStatus = relationshipStatus;
    }

    public FriendRelationship(@NonNull Long invitingUserId,
                              @NonNull Long invitedUserId) {
        this.invitingUserId = invitingUserId;
        this.invitedUserId = invitedUserId;
        this.relationshipStatus = new RelationshipStatus(PossibleStatus.WAIT);
    }
}
