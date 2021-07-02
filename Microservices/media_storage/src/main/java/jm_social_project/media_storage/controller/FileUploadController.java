package jm_social_project.media_storage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;


@RestController
@RequestMapping("/api")
public class FileUploadController {

    private final StorageService storageService;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }


    @PostMapping("/upload_video/")
    public String handleVideoUpload(@RequestParam("file") MultipartFile file,
                                    Principal principal) {
        storageService.store(file, principal);
        return "You successfully uploaded video " + file.getOriginalFilename() + "!";
    }

    @PostMapping("/upload_photo/")
    public String handlePhotoUpload(@RequestParam("file") MultipartFile file,
                                    Principal principal) {
        storageService.store(file, principal);
        return "You successfully uploaded photo " + file.getOriginalFilename() + "!";
    }

}
