package jm_social_project.media_storage.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.util.HashSet;
import java.util.Set;

@RedisHash("Photo")
public class PhotoContent {
    @Id
    private Long photoId;
    @Indexed
    private String userId;
    private String photoPath;
    private Set<String> likedUserIds = new HashSet<>();

    public PhotoContent(Long photoId, String userId, String photoPath) {
        this.photoId = photoId;
        this.userId = userId;
        this.photoPath = photoPath;
    }

    public Long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Long photoId) {
        this.photoId = photoId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public Set<String> getLikedUserIds() {
        return likedUserIds;
    }

    public void setLikedUserIds(Set<String> likedUserIds) {
        this.likedUserIds = likedUserIds;
    }
}
