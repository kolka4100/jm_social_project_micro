package jm_social_project.profile_service.controller;

import io.swagger.annotations.ApiOperation;
import jm_social_project.profile_service.model.Profile;
import jm_social_project.profile_service.service.ProfileService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
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
    @ApiOperation(value = "Creates a profile by userId",
            notes = "Provide a profile and id user")
    public ResponseEntity<Profile> createProfile(@RequestBody Profile profile, @RequestHeader HttpHeaders httpHeaders) {
        String accountId = httpHeaders.getFirst("user_id");
        return ResponseEntity.ok().body(profileService.saveProfile(profile, accountId));
    }

    @GetMapping("/{accountId}")
    @ApiOperation(value = "Returns a profile by userId",
            notes = "Provide userId ")
    public ResponseEntity<Profile> getProfileByAccountId(@PathVariable String accountId) {
        return ResponseEntity.ok().body(profileService.getProfileByAccountId(accountId));
    }

    @PutMapping
    @ApiOperation(value = "Update a profile by userId",
            notes = "Provide a profile")
    public ResponseEntity<Profile> updateProfile(@RequestBody Profile profile) {
        return ResponseEntity.ok().body(profileService.updateProfile(profile));
    }

    @GetMapping
    @ApiOperation(value = "Get all profiles",
            notes = "Returns list all profiles")
    public ResponseEntity<List<Profile>> getAllProfiles() {
        return ResponseEntity.ok().body(profileService.getAllProfiles());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get profile by id",
            notes = "Provide id")
    public ResponseEntity<Profile> getProfile(@PathVariable String id) {
        return ResponseEntity.ok().body(profileService.getProfileById(id));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete profile",
            notes = "Provide id")
    public ResponseEntity<Boolean> deleteProfile(@PathVariable String id) {
        return ResponseEntity.ok().body(profileService.deleteProfile(id));
    }

    @GetMapping("/nearby-profiles")
    @ApiOperation(value = "Get users nearby",
            notes = "Returns Map users nearby")
    public ResponseEntity<Map> getNearbyProfiles(@RequestBody Profile profile) {

        return ResponseEntity.ok().body(profileService.getNearbyProfiles(profile));
    }
}
