package jm_social_project.profile_service.controller;

import jm_social_project.profile_service.config_annotation.AuthPrincipal;
import jm_social_project.profile_service.model.Profile;
import jm_social_project.profile_service.service.ProfileService;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;

@RestController
public class RController {

    private ProfileService profileService;

    @PostMapping
    public Response createProfile(@RequestBody Profile profile, @AuthPrincipal String accountId) {
        profileService.saveProfile(profile, accountId);
        return Response.ok().build();
    }

    @GetMapping("/{accountId}")
    public Response getProfileByAccountId(@RequestHeader("user_id") String user_id,
                                          @PathVariable String accountId) {
        profileService.getProfileByAccountId(accountId, user_id);
        return Response.ok().build();
    }

    @PutMapping
    public Response updateProfile(@RequestBody Profile profile) {
        profileService.updateProfile(profile);
        return Response.ok().build();
    }

    @GetMapping
    public Response getAllProfiles() {
        profileService.getAllProfiles();
        return Response.ok().build();
    }

    @DeleteMapping("/{id}")
    public Response deleteProfile(@PathVariable String id) {
        profileService.deleteProfile(id);
        return Response.ok().build();
    }

    @GetMapping("/nearby-profiles")
    public Response getNearbyProfiles(@RequestBody Profile profile) {
        profileService.getNearbyProfiles(profile);
        return Response.ok().build();
    }

    @GetMapping("/{id}/{isLiked}")
    public Response addToLikeOrDodgeList(@RequestHeader("user_id") String user_id,
                                         @PathVariable String id,
                                         @PathVariable Boolean isLiked) {
        profileService.addToLikeOrDodgeList(user_id, id, isLiked);
        return Response.ok().build();
    }
}
