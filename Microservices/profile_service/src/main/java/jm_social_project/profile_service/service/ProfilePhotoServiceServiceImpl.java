package jm_social_project.profile_service.service;

import jm_social_project.profile_service.model.ProfilePhoto;
import jm_social_project.profile_service.repository.ProfilePhotoRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@NoArgsConstructor
@Service
public class ProfilePhotoServiceServiceImpl implements ProfilePhotoServiceService {
    private ProfilePhotoRepository photoRepository;

    public ProfilePhotoRepository getPhotoRepository() {
        return photoRepository;
    }

    @Autowired
    public void setPhotoRepository(ProfilePhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    @Override
    public void saveProfilePhoto(ProfilePhoto profilePhoto) {
        photoRepository.insert(profilePhoto);
    }

    @Override
    public void updateProfilePhoto(ProfilePhoto profilePhoto) {
        photoRepository.save(profilePhoto);
    }

    @Override
    public void deleteProfilePhoto(ProfilePhoto profilePhoto) {
        photoRepository.delete(profilePhoto);
    }

    @Override
    public List<ProfilePhoto> getAllProfilePhoto() {
        return photoRepository.findAll();
    }

    @Override
    public ProfilePhoto getProfilePhotoById(String id) {
        return photoRepository.findById(id).orElse(null);
    }
}
