package org.javamentor.social.friend_service.controller;

import org.javamentor.social.friend_service.exceptions.RelationshipAlreadyExistException;
import org.javamentor.social.friend_service.exceptions.RelationshipDontExistException;
import org.javamentor.social.friend_service.model.FriendRelationship;
import org.javamentor.social.friend_service.service.IFriendRelationshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/rest/friend")
public class FriendsController {

    @Autowired
    private IFriendRelationshipService friendRelationshipService;

    @PostMapping("/save")
    public FriendRelationship saveRelationship(@RequestBody Long invitingUserId, @RequestBody Long invitedUserId) {

            var friendRelationship = new FriendRelationship(invitingUserId, invitedUserId);

            return friendRelationshipService.save(friendRelationship);


    }

    @PostMapping("/delete")
    public String deleteRelationship(@RequestBody Long firstUserId, @RequestBody Long secondUserId) {

        Long relationshipId = friendRelationshipService.findRelationshipIdByUsersIdsIfExists(firstUserId, secondUserId);

        if (relationshipId != null) {
            friendRelationshipService.delete(relationshipId);
        }

        throw new RelationshipDontExistException("Relationship between user with Id " + firstUserId +
                " and user with Id " + secondUserId + " dont exists");
    }


}