package org.javamentor.social.friend_service.dao;

import org.javamentor.social.friend_service.model.Friends;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendsRepository extends JpaRepository<Friends, Long> {

    Friends findByInvitingUserIdAndInvitedUserId(long invitingUserId, long invitedUserId);

}