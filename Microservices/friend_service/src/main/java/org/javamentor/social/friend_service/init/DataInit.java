package org.javamentor.social.friend_service.init;

import org.javamentor.social.friend_service.model.FriendRelationship;
import org.javamentor.social.friend_service.model.PossibleStatus;
import org.javamentor.social.friend_service.model.RelationshipStatus;
import org.javamentor.social.friend_service.service.IFriendRelationshipService;
import org.javamentor.social.friend_service.service.IRelationshipStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Transactional
public class DataInit {


    @Autowired
    private IFriendRelationshipService friendRelationshipService;

    @Autowired
    private IRelationshipStatusService relationshipStatusService;


    private final RelationshipStatus wait = new RelationshipStatus(PossibleStatus.WAIT);
    private final RelationshipStatus accepted = new RelationshipStatus(PossibleStatus.ACCEPTED);

    @PostConstruct
    public void dataInit(){
        relationshipStatusService.save(wait);
        relationshipStatusService.save(accepted);


        for (int i = 1; i <= 20; i++) {
            createFriends(i);
        }
    }

    private void createFriends(int i) {

        FriendRelationship relationship = new FriendRelationship();
        relationship.setInvitingUserId((long) i);
        relationship.setInvitedUserId((long) i + 1);

        if (i % 2 == 0) relationship.setRelationshipStatus(wait);
        else relationship.setRelationshipStatus(accepted);
        friendRelationshipService.save(relationship);
    }

}
