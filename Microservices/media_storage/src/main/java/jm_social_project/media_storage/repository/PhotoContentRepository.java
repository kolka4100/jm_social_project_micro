package jm_social_project.media_storage.repository;

import jm_social_project.media_storage.model.PhotoContent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoContentRepository extends CrudRepository<PhotoContent, String> {

    List<PhotoContent> findPhotoContentByUserId(String userId);
}

