package org.javamentor.social.friend_service.dao;

import org.javamentor.social.friend_service.model.RelationshipStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RelationshipStatusRepository extends JpaRepository<RelationshipStatus, Long> {
}