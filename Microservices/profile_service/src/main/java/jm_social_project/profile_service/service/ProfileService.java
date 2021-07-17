package jm_social_project.profile_service.service;

import jm_social_project.profile_service.model.Profile;

import java.util.List;

public interface ProfileService {

    void saveProfile(Profile profile, String accountId);

    void updateProfile(Profile profile);

    void deleteProfile(String id);

    List<Profile> getAllProfiles();

    Profile getProfileById(String id);

    Profile getProfileByAccountId(String accountId);
}
