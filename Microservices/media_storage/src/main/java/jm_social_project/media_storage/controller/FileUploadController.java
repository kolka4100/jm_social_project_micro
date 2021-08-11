package jm_social_project.media_storage.controller;

import io.swagger.annotations.ApiOperation;
import jm_social_project.media_storage.dto.PhotoDTO;
import jm_social_project.media_storage.model.PhotoContent;
import jm_social_project.media_storage.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/api")
public class FileUploadController {

    private final StorageService storageService;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }


    @PostMapping("/upload_video/")
    @ApiOperation(value = "Upload video",
            notes = "Saving videos by userId")
    public String handleVideoUpload(@RequestParam("file") MultipartFile file,
                                    @RequestHeader HttpHeaders httpHeaders) {
        String accountId = httpHeaders.getFirst("user_id");
        storageService.store(file, accountId);
        return "You successfully uploaded video " + file.getOriginalFilename() + "!";
    }

    @PostMapping("/upload_photo/")
    @ApiOperation(value = "Upload photo",
            notes = "Saving photos by userId")
    public String handlePhotoUpload(@RequestParam("file") MultipartFile file,
                                    @RequestHeader HttpHeaders httpHeaders) {
        String accountId = httpHeaders.getFirst("user_id");
        storageService.store(file, accountId);
        return "You successfully uploaded photo " + file.getOriginalFilename() + "!";
    }

    @GetMapping()
    @ApiOperation(value = "Find photos",
            notes = "Find all photos")
    public ResponseEntity<List<PhotoContent>> getAllPhotoContent() {
        List<PhotoContent> photoContents = storageService.getAllPhotoContent();
        return new ResponseEntity<>(photoContents, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    @ApiOperation(value = "Find photos by userId",
            notes = "Find all photos by userId")
    public ResponseEntity<List<PhotoContent>> getPhotoContentByUserId(@PathVariable String userId) {
        List<PhotoContent> photoContents = storageService.getPhotoContentByUserId(userId);
        return new ResponseEntity<>(photoContents, HttpStatus.OK);
    }

    @PostMapping("/{userId}/like/{photoId}")
    @ApiOperation(value = "Like a photo",
            notes = "Provide a id photo and id user")
    public ResponseEntity<PhotoContent> like(@PathVariable("photoId") String photoId,
                                             @PathVariable("userId") String userId) {
        PhotoContent photoContent = storageService.likePhoto(photoId, userId);
        return new ResponseEntity<>(photoContent, HttpStatus.OK);
    }

    @GetMapping("/photo/{photoId}")
    @ApiOperation(value = "Get information about a photo",
            notes = "Returns id photo, likes, id of users who liked")
    public ResponseEntity<PhotoDTO> getPhotoDTOByPhotoId(@PathVariable("photoId") String photoId) {
        PhotoDTO photoDTO = storageService.photoContentToPhotoDTO(photoId);
        return new ResponseEntity<>(photoDTO, HttpStatus.OK);
    }
}
