package org.javamentor.social.friend_service.controller;

import org.javamentor.social.friend_service.model.Friends;
import org.javamentor.social.friend_service.service.IFriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.Response;

@RestController
public class RController {

    @Autowired
    private IFriendsService friendRelationshipService;

    @GetMapping("/friends")
    public Response getFriends(){
        friendRelationshipService.getFriends();
        return Response.ok().build();
    }

    @PostMapping("/save")
    public Response saveFriends(@RequestBody Long invitingUserId, @RequestBody Long invitedUserId){
        var friendRelationship = new Friends(invitingUserId, invitedUserId);
        friendRelationshipService.save(friendRelationship);
        return Response.ok().build();
    }

    @PostMapping("/delete")
    public Response deleteFriends(@RequestBody Long firstUserId, @RequestBody Long secondUserId){
        friendRelationshipService.deleteByUsersIds(firstUserId, secondUserId);
        return Response.ok().build();
    }

    @PostMapping("/accept")
    public Response acceptFriendsInvite(@RequestBody Long firstUserId, @RequestBody Long secondUserId){
        friendRelationshipService.acceptFriendsInvite(firstUserId, secondUserId);
        return Response.ok().build();
    }
}
