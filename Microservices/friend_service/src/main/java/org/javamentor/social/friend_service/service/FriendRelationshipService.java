package org.javamentor.social.friend_service.service;

import org.javamentor.social.friend_service.dao.FriendRelationshipRepository;
import org.javamentor.social.friend_service.exceptions.RelationshipAlreadyExistException;
import org.javamentor.social.friend_service.exceptions.RelationshipDontExistException;
import org.javamentor.social.friend_service.model.FriendRelationship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendRelationshipService implements IFriendRelationshipService {

    @Autowired
    private FriendRelationshipRepository friendRelationshipRepository;


    @Override
    public FriendRelationship save(final FriendRelationship friendRelationship) throws RelationshipAlreadyExistException{
        var invitingUserId = friendRelationship.getInvitingUserId();
        var invitedUserId = friendRelationship.getInvitedUserId();
        if (findRelationshipByUsersIdsIfExists(invitingUserId, invitedUserId) == null) {
            return friendRelationshipRepository.saveAndFlush(friendRelationship);
        }
        throw new RelationshipAlreadyExistException("Relationship between user with Id " + invitingUserId +
                " and user with Id " + invitedUserId + " already exists");
    }

    @Override
    public FriendRelationship findRelationshipByUsersIdsIfExists(final long firstUserId, final long secondUserId) {
        var relationship = friendRelationshipRepository.findByInvitingUserIdAndInvitedUserId(firstUserId, secondUserId);

        if (relationship == null) {
            relationship = friendRelationshipRepository.findByInvitingUserIdAndInvitedUserId(secondUserId, firstUserId);
        }
        return relationship;
    }

    @Override
    public Long findRelationshipIdByUsersIdsIfExists(final long firstUserId, final long secondUserId) {
        var relationship = findRelationshipByUsersIdsIfExists(firstUserId, secondUserId);
        if (relationship != null) {
            return relationship.getId();

        }
        return null;
    }

    @Override
    public void delete(final long relationshipId) {

        friendRelationshipRepository.deleteById(relationshipId);
    }

    @Override
    public void delete(final long firstUserId, final long secondUserId) throws RelationshipDontExistException{
        Long relationshipId = findRelationshipIdByUsersIdsIfExists(firstUserId, secondUserId);

        if (relationshipId != null) {
            delete(relationshipId);
        }

        throw new RelationshipDontExistException("Relationship between user with Id " + firstUserId +
                " and user with Id " + secondUserId + " dont exists");
    }


}
