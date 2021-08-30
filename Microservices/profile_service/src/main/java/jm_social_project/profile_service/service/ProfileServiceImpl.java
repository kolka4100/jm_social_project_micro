package jm_social_project.profile_service.service;

import jm_social_project.profile_service.model.Profile;
import jm_social_project.profile_service.model.VisitedProfiles;
import jm_social_project.profile_service.repository.ProfileRepository;
import jm_social_project.profile_service.repository.VisitedProfileRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    @NonNull
    private ProfileRepository profileRepository;

    @NonNull
    private VisitedProfileRepository visitedProfileRepository;

    private Profile profile;

    @Override
    public Profile saveProfile(Profile profile, String accountId) {
        profile.setAccountId(accountId);
        return profileRepository.insert(profile);
    }

    @Override
    public Profile getProfileByAccountId(String accountId, String user_id) {
        Profile profile = profileRepository.getProfileByAccountId(accountId);
        Set<VisitedProfiles> oldVisitors = profile.getVisitedProfilesSet();
        for(VisitedProfiles visitor : oldVisitors) {
            if(visitor.getProfileId().equals(user_id)) {
                visitor.setVisitDate(new Date());
                profileRepository.save(profile);
                return profile;
            }
        }
        VisitedProfiles newVisitor = new VisitedProfiles(user_id, new Date());
        visitedProfileRepository.insert(newVisitor);
        profile.getVisitedProfilesSet().add(newVisitor);

        if(profile.getVisitedProfilesSet().size() > 10) {
            profile.getVisitedProfilesSet().stream().limit(1).forEach(x-> visitedProfileRepository.deleteById(x.getId()));
            profile.setVisitedProfilesSet(profile.getVisitedProfilesSet().stream().skip(1).collect(Collectors.toSet()));
        }

        profileRepository.save(profile);
        return profile;
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
}
