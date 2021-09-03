package jm_social_project.profile_service.controller;

import jm_social_project.profile_service.config_annotation.AuthPrincipal;
import jm_social_project.profile_service.model.Profile;
import jm_social_project.profile_service.service.ProfileService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profiles")
public class ProfileRestController {

    @NonNull
    private ProfileService profileService;

    @PostMapping
    public ResponseEntity<Profile> createProfile(@RequestBody Profile profile, @AuthPrincipal String accountId) {
        return ResponseEntity.ok().body(profileService.saveProfile(profile, accountId));
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<Profile> getProfileByAccountId(@RequestHeader("user_id") String user_id, @PathVariable String accountId) {
        return ResponseEntity.ok().body(profileService.getProfileByAccountId(accountId,user_id));
    }

    @PutMapping
    public ResponseEntity<Profile> updateProfile(@RequestBody Profile profile) {
        return ResponseEntity.ok().body(profileService.updateProfile(profile));
    }

    @GetMapping
    public ResponseEntity<List<Profile>> getAllProfiles() {
        return ResponseEntity.ok().body(profileService.getAllProfiles());
    }

    // маппинг дублируется с accountId
    //@GetMapping("/{id}")
    //public ResponseEntity<Profile> getProfile(@PathVariable String id) {
    //    return ResponseEntity.ok().body(profileService.getProfileById(id));
    //}

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteProfile(@PathVariable String id) {
        return ResponseEntity.ok().body(profileService.deleteProfile(id));
    }

    @GetMapping("/nearby-profiles")
    public ResponseEntity<Map> getNearbyProfiles(@RequestBody Profile profile) {

        return ResponseEntity.ok().body(profileService.getNearbyProfiles(profile));
    }

    @GetMapping("/{id}/{isLiked}")
    public ResponseEntity<Profile> addToLikeOrDodgeList(
            @RequestHeader("user_id") String user_id,@PathVariable String id, @PathVariable Boolean isLiked) {
        return ResponseEntity.ok().body(profileService.addToLikeOrDodgeList(user_id, id, isLiked));
    }
}
