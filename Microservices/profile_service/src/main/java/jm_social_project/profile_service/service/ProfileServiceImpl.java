package jm_social_project.profile_service.service;

import jm_social_project.profile_service.model.Profile;
import jm_social_project.profile_service.repository.ProfileRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    @NonNull
    private ProfileRepository profileRepository;

    private Profile profile;

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

    @Override
    public Map getNearbyProfiles(Profile profile) {

        List<Profile> allProfiles = new ArrayList(getAllProfiles());

        //получил Map с профилями входящими в радиус 1км
        Map profilesInCircle = new HashMap();
        for (int i = 0; i < allProfiles.size(); i++) {

            int distanceBetween = distanceInKilometers(allProfiles.get(i).getLatitude(), allProfiles.get(i).getLongitude(), profile.getLatitude(), profile.getLongitude());

            if (distanceBetween <= 1000) {
                profilesInCircle.put("key" + i, allProfiles.get(i));
            }
        }
        return profilesInCircle;
    }

    // получил расстояние между юзером и юзерами в радиусе 1 км
    @Override
    public List distanceBetweenProfiles(Profile profile) {
        List<Profile> allProfiles = new ArrayList(getAllProfiles());
        List profilesDistance = new ArrayList();

        for (int i = 0; i < allProfiles.size(); i++) {

            int distanceBetween = distanceInKilometers(allProfiles.get(i).getLatitude(), allProfiles.get(i).getLongitude(), profile.getLatitude(), profile.getLongitude());

            if (distanceBetween <= 1000) {
                profilesDistance.add("Растояние между Вами и " + allProfiles.get(i).getFirstName() + " " + allProfiles.get(i).getLastName() + " составляет " + distanceBetween + " метра(ов)");
            }
        }
        return profilesDistance;
    }


    //расчет расстояния между двумя точками по широте и долготе
    //    public final static double AVERAGE_RADIUS_OF_EARTH_KM = 6371;
    public int distanceInKilometers(double friendLat, double friendLon,
                                    double userLat, double userLon) {

        double latDistance = Math.toRadians(friendLat - userLat);
        double lonDistance = Math.toRadians(friendLon - userLon);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(friendLat)) * Math.cos(Math.toRadians(userLat))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return (int) (Math.round(6371000 * c));
    }

    @Override
    public Profile likeOrDodgeProfile(String user_id, String id, Boolean isLiked) {
        Profile profile = getProfileById(user_id);
        Date date = new Date();
        if (isLiked) {
            profile.getLikeList().put(id, date);
        } else {
            if (profile.getDodgeList().size() >= 70) {
                String first = profile.getDodgeList().keySet().iterator().next();
                profile.getDodgeList().remove(first);
            }
            profile.getDodgeList().put(id, date);
        }
        profileRepository.save(profile);
        return profile;
    }
}
