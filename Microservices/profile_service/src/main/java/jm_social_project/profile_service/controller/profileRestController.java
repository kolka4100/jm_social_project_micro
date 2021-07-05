package jm_social_project.profile_service.controller;

import jm_social_project.profile_service.model.Profile;
import jm_social_project.profile_service.service.ProfileService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profiles")
public class profileRestController {
    private ProfileService profileService;

    @PostMapping
    public void createProfile(@RequestBody Profile profile) {
        profileService.saveProfile(profile);
    }

    @PutMapping
    public void updateProfile(@RequestBody Profile profile) {
        profileService.updateProfile(profile);
    }

    @GetMapping
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
