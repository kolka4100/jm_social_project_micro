package jm_social_project.profile_service.service;

import jm_social_project.profile_service.model.Profile;
import jm_social_project.profile_service.repository.ProfileRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private ProfileRepository profileRepository;

    public ProfileServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public ProfileRepository getProfileRepository() {
        return profileRepository;
    }
    @Autowired
    public void setProfileRepository(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public void saveProfile(Profile profile) {
        profileRepository.insert(profile);
    }

    @Override
    public void updateProfile(Profile profile) {
        profileRepository.save(profile);
    }

    @Override
    public void deleteProfile(String id) {
        profileRepository.deleteById(id);
    }

    @Override
    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }

    @Override
    public Profile getProfileById(String id) {
        return profileRepository.findById(id).orElse(null);
    }
}
