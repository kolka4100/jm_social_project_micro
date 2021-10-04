package jm_social_project.media_storage.controller;

import jm_social_project.media_storage.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.core.Response;

@RestController
public class RController {

    private final StorageService storageService;

    @Autowired
    public RController(StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("/upload_video/")
    public Response handleVideoUpload(@RequestParam("file") MultipartFile file,
                                      @RequestHeader HttpHeaders httpHeaders) {
        String accountId = httpHeaders.getFirst("user_id");
        storageService.store(file, accountId);
        return Response.ok().build();
    }

    @PostMapping("/upload_photo/")
    public Response handlePhotoUpload(@RequestParam("file") MultipartFile file,
                                      @RequestHeader HttpHeaders httpHeaders) {
        String accountId = httpHeaders.getFirst("user_id");
        storageService.store(file, accountId);
        return Response.ok().build();
    }

    @GetMapping()
    public Response getAllPhotoContent() {
        storageService.getAllPhotoContent();
        return Response.ok().build();
    }

    @GetMapping("/{userId}")
    public Response getPhotoContentByUserId(@PathVariable String userId){
        storageService.getPhotoContentByUserId(userId);
        return Response.ok().build();
    }

    @PostMapping("/{userId}/like/{photoId}")
    public Response like(@PathVariable("photoId") String photoId,
                         @PathVariable("userId") String userId){
        storageService.likePhoto(photoId, userId);
        return Response.ok().build();
    }

    @GetMapping("/photo/{photoId}")
    public Response getPhotoDTOByPhotoId(@PathVariable("photoId") String photoId){
        storageService.photoContentToPhotoDTO(photoId);
        return Response.ok().build();
    }
}
