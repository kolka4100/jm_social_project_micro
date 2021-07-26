package org.javamentor.social.friend_service.service;

import org.javamentor.social.friend_service.model.FriendRelationship;


public interface IFriendRelationshipService {


    FriendRelationship save(FriendRelationship relationship);

    FriendRelationship findRelationshipByUsersIdsIfExists(long firstUserId, long secondUserId);

    Long findRelationshipIdByUsersIdsIfExists(long firstUserId, long secondUserId);

    void delete(long relationshipId);

    void delete(long firstUserId, long secondUserId);
}
