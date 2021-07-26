package org.javamentor.social.friend_service.dao;

import org.javamentor.social.friend_service.model.FriendRelationship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRelationshipRepository extends JpaRepository<FriendRelationship, Long> {

    FriendRelationship findByInvitingUserIdAndInvitedUserId(long invitingUserId, long invitedUserId);

}