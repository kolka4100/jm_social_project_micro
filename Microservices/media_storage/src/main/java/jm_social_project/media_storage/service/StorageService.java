package jm_social_project.media_storage.service;

import org.springframework.web.multipart.MultipartFile;
import java.security.Principal;

public interface StorageService {

    void store(MultipartFile file, Long id);



}
