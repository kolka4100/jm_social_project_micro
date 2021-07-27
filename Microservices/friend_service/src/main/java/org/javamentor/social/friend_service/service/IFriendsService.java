package org.javamentor.social.friend_service.service;

import org.javamentor.social.friend_service.model.Friends;


public interface IFriendsService {


    void save(Friends relationship);

    Friends findFriendsByUsersIdsIfExists(long firstUserId, long secondUserId);

    Long findFriendsIdByUsersIdsIfExists(long firstUserId, long secondUserId);

    void deleteByFriendsId(long friendsId);

    void deleteByUsersIds(long firstUserId, long secondUserId);
}
