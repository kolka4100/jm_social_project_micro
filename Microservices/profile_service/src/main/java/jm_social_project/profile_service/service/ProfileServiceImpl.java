package jm_social_project.profile_service.service;

import jm_social_project.profile_service.model.Profile;
import jm_social_project.profile_service.repository.ProfileRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    @NonNull
    private ProfileRepository profileRepository;

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