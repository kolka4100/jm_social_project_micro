package jm_social_project.profile_service.service;

import jm_social_project.profile_service.model.Profile;

import java.util.List;
import java.util.Map;

public interface ProfileService {

    Profile saveProfile(Profile profile, String accountId);

    Profile updateProfile(Profile profile);

    boolean deleteProfile(String id);

    List<Profile> getAllProfiles();

    Profile getProfileById(String id);

    Profile getProfileByAccountId(String accountId, String user_id);

    Map getNearbyProfiles(Profile profile);

    List distanceBetweenProfiles(Profile profile);

}
