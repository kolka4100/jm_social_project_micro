package org.javamentor.social.friend_service.controller;

import org.javamentor.social.friend_service.model.Friends;
import org.javamentor.social.friend_service.service.IFriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/rest/friend")
public class FriendsController {

    @Autowired
    private IFriendsService friendRelationshipService;

    @PostMapping("/save")
    public Friends saveRelationship(@RequestBody Long invitingUserId, @RequestBody Long invitedUserId) {

        var friendRelationship = new Friends(invitingUserId, invitedUserId);

        return friendRelationshipService.save(friendRelationship);
    }

    @PostMapping("/delete")
    public String deleteRelationship(@RequestBody Long firstUserId, @RequestBody Long secondUserId) {
        friendRelationshipService.deleteByUsersIds(firstUserId, secondUserId);
        return "Relationship between user with id " + firstUserId + " and user with id " + secondUserId + " deleted";
    }


}