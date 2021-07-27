package org.javamentor.social.friend_service.service;

import org.javamentor.social.friend_service.dao.FriendsRepository;
import org.javamentor.social.friend_service.exceptions.RelationshipAlreadyExistException;
import org.javamentor.social.friend_service.exceptions.RelationshipDontExistException;
import org.javamentor.social.friend_service.model.Friends;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendsService implements IFriendsService {

    @Autowired
    private FriendsRepository friendsRepository;


    @Override
    public Friends save(final Friends friends) {
        var invitingUserId = friends.getInvitingUserId();
        var invitedUserId = friends.getInvitedUserId();
        if (findRelationshipByUsersIdsIfExists(invitingUserId, invitedUserId) == null) {
            return friendsRepository.saveAndFlush(friends);
        }
        throw new RelationshipAlreadyExistException("Relationship between user with Id " + invitingUserId +
                " and user with Id " + invitedUserId + " already exists");
    }

    @Override
    public Friends findRelationshipByUsersIdsIfExists(final long firstUserId, final long secondUserId) {
        var relationship = friendsRepository.findByInvitingUserIdAndInvitedUserId(firstUserId, secondUserId);

        if (relationship == null) {
            relationship = friendsRepository.findByInvitingUserIdAndInvitedUserId(secondUserId, firstUserId);
        }
        return relationship;
    }

    @Override
    public Long findFriendsIdByUsersIdsIfExists(final long firstUserId, final long secondUserId) {
        var relationship = findRelationshipByUsersIdsIfExists(firstUserId, secondUserId);
        if (relationship != null) {
            return relationship.getId();

        }
        return null;
    }

    @Override
    public void deleteByFriendsId(final long friendsId) {

        friendsRepository.deleteById(friendsId);
    }

    @Override
    public void deleteByUsersIds(final long firstUserId, final long secondUserId) {
        Long friendsId = findFriendsIdByUsersIdsIfExists(firstUserId, secondUserId);

        if (friendsId != null) {
            deleteByFriendsId(friendsId);
        }

        throw new RelationshipDontExistException("Relationship between user with Id " + firstUserId +
                " and user with Id " + secondUserId + " dont exists");
    }


}
