package jm_social_project.profile_service.controller;

import jm_social_project.profile_service.model.Profile;
import jm_social_project.profile_service.service.ProfileService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@NoArgsConstructor
@RequestMapping("/profiles")
public class profileRestController {
    private ProfileService profileService;

    public ProfileService getProfileService() {
        return profileService;
    }

    @Autowired
    public void setProfileService(ProfileService profileService) {
        this.profileService = profileService;
    }
    @PostMapping("/")
    public void createProfile(@RequestBody Profile profile) {
        profileService.saveProfile(profile);
    }

    @PutMapping("/")
    public void updateProfile(@RequestBody Profile profile) {
        profileService.updateProfile(profile);
    }

    @GetMapping("/")
    public List<Profile> getAllProfiles() {
        return profileService.getAllProfiles();
    }

    @GetMapping("/{id}")
    public Profile getProfile(@PathVariable String id) {
        return profileService.getProfileById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteProfile(@PathVariable String id) {
        profileService.deleteProfile(id);
    }
}
