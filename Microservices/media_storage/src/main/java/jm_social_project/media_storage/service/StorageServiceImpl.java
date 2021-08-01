package jm_social_project.media_storage.service;


import jm_social_project.media_storage.exception.PhotoContentNotFoundException;
import jm_social_project.media_storage.exception.StorageException;
import jm_social_project.media_storage.model.PhotoContent;
import jm_social_project.media_storage.repository.PhotoContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

@Service
public class StorageServiceImpl implements StorageService {

    @Value("${mediastorage.path}")
    private String URI;

    @Autowired
    private Environment env;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ProfilePhotoService profilePhotoService;

    @Autowired
    private PhotoContentRepository photoContentRepository;


    public void store(MultipartFile file, String userId) {

        String contentType = file.getContentType();

        if (file.isEmpty()) {
            throw new StorageException("Failed to store empty file.");
        }

        if (contentType.startsWith("video")) {
            storeVideo(file, userId);

        } else if (contentType.startsWith("image")) {
            storePhoto(file, userId);

        }

    }

    private void storeVideo(MultipartFile file,
                            String userId) throws StorageException {

        Path rootLocation = Path.of(URI + env.getProperty("mediastorage.video") + userId);

        Path destinationFile = rootLocation
                .resolve(Paths.get(file.getOriginalFilename()));

        try {
            storeFileToFilesystem(rootLocation,
                    destinationFile, file);

        } catch (IOException e) {
            throw new StorageException("Failed to store video.", e);
        }

    }

    private void storePhoto(MultipartFile file,
                            String userId) throws StorageException {

        String NamePattern = "photo-%d-%d.%s";


        Path rootLocation = Path.of(URI + env.getProperty("mediastorage.photo") + userId);

        Account account = accountService.findById(Long.parseLong(userId));

        int photoNumber = profilePhotoService
                .findAllByProfileId(account.getProfile()).size() + 1;

        long profileId = account.getProfile().getId();

        String fileExtension = file.getOriginalFilename()
                .substring(file.getOriginalFilename().lastIndexOf(".") + 1);

        String fileName = String.format(NamePattern, profileId,
                photoNumber, fileExtension);

        Path destinationFile = rootLocation.resolve(Paths.get(fileName));

        ProfilePhoto photo = new ProfilePhoto(destinationFile.toString(),
                account.getProfile());

        try {
            storeFileToFilesystem(rootLocation,
                    destinationFile, file);
            profilePhotoService.add(photo);
            photoContentRepository.save(new PhotoContent(Integer.toString(photoNumber), userId, destinationFile.toString()));
        } catch (IOException e) {
            throw new StorageException("Failed to store photo.", e);
        }

    }

    private void storeFileToFilesystem(Path rootLocation,
                                       Path destinationFile,
                                       MultipartFile file) throws IOException {
        Files.createDirectories(rootLocation);

        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, destinationFile);
        }
    }

    public List<PhotoContent> getAllPhotoContent() {
        return (List<PhotoContent>) photoContentRepository.findAll();
    }

    public List<PhotoContent> getPhotoContentByUserId(String userId) {
        return photoContentRepository.findPhotoContentByUserId(userId);
    }

    public PhotoContent likePhoto(String photoContentId, String userId) {
        PhotoContent photoContent = photoContentRepository.findById(photoContentId)
                .orElseThrow(() -> new PhotoContentNotFoundException("Media content cannot be found"));

        Set<String> likedUsers = photoContent.getLikedUsers();

        if (likedUsers.contains(userId)) {
            likedUsers.remove(userId);
        } else {
            likedUsers.add(userId);
        }

        photoContent.setLikedUsers(likedUsers);

        return photoContentRepository.save(photoContent);
    }
}
