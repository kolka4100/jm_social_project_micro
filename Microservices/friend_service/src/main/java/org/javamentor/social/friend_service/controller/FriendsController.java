package org.javamentor.social.friend_service.controller;

import io.swagger.annotations.ApiOperation;
import org.javamentor.social.friend_service.model.Friends;
import org.javamentor.social.friend_service.service.IFriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/rest/friend")
public class FriendsController {

    @Autowired
    private IFriendsService friendRelationshipService;

    @GetMapping("/friends")
    @ApiOperation(value = "Find friends",
            notes = "Returns list of friends")
    public List<Friends> getFriends() {
        return friendRelationshipService.getFriends();
    }

    @PostMapping("/save")
    @ApiOperation(value = "Save friends",
            notes = "Provide an Id inviting user and Id invited user for save friends")
    public void saveFriends(@RequestBody Long invitingUserId, @RequestBody Long invitedUserId) {

        var friendRelationship = new Friends(invitingUserId, invitedUserId);

        friendRelationshipService.save(friendRelationship);
    }

    @PostMapping("/delete")
    @ApiOperation(value = "Deletes friends",
            notes = "Provide an Id first user and Id second user for delete friends")
    public String deleteFriends(@RequestBody Long firstUserId, @RequestBody Long secondUserId) {
        friendRelationshipService.deleteByUsersIds(firstUserId, secondUserId);
        return "Relationship between user with id " + firstUserId + " and user with id " + secondUserId + " deleted";
    }

    @PostMapping("/accept")
    @ApiOperation(value = "Accepts friends invite",
            notes = "Provide an Id first user and Id second user for accepts invite")
    public String acceptFriendsInvite(@RequestBody Long firstUserId, @RequestBody Long secondUserId) {
        friendRelationshipService.acceptFriendsInvite(firstUserId, secondUserId);
        return "Relationship between user with id " + firstUserId + " and user with id " + secondUserId + " accepted";
    }


}