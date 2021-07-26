package org.javamentor.social.friend_service.service;

import org.javamentor.social.friend_service.dao.RelationshipStatusRepository;
import org.javamentor.social.friend_service.model.RelationshipStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelationshipStatusService implements IRelationshipStatusService {

    @Autowired
    private RelationshipStatusRepository repository;

    @Override
    public void save(final RelationshipStatus relationshipStatus) {
        repository.saveAndFlush(relationshipStatus);
    }
}
