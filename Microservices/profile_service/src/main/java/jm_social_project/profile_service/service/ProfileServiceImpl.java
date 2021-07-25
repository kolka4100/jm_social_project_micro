package jm_social_project.profile_service.service;

import jm_social_project.profile_service.model.Profile;
import jm_social_project.profile_service.repository.ProfileRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    @NonNull
    private ProfileRepository profileRepository;

    @Override
    public Profile saveProfile(Profile profile, String accountId) {
        profile.setAccountId(accountId);
        return profileRepository.insert(profile);
    }

    @Override
    public Profile getProfileByAccountId(String accountId) {
        return profileRepository.getProfileByAccountId(accountId);
    }

    @Override
    public Profile updateProfile(Profile profile) {
        return profileRepository.save(profile);
    }

    @Override
    public boolean deleteProfile(String id) {
        profileRepository.deleteById(id);
        return true;
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
