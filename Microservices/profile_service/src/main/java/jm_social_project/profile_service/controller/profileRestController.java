package jm_social_project.profile_service.controller;

import jm_social_project.profile_service.model.Profile;
import jm_social_project.profile_service.service.ProfileService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profiles")
public class profileRestController {

    @NonNull
    private ProfileService profileService;

    @PostMapping
    public void createProfile(@RequestBody Profile profile, @RequestHeader HttpHeaders httpHeaders) {
        String accountId = httpHeaders.getFirst("user_id");
        profileService.saveProfile(profile,accountId);
    }

    @GetMapping("/accountId/{accountId}")
    public Profile getProfileByAccountId(@PathVariable String accountId){
        return profileService.getProfileByAccountId(accountId);
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
