package jm_social_project.media_storage.dto;

import jm_social_project.media_storage.model.PhotoContent;

import java.util.Set;

public class PhotoDTO {
    private Long photoId;
    private Integer likes;
    private Set<String> likedUserIds;

    public PhotoDTO(PhotoContent photo, Integer likes) {
        this.photoId = photo.getPhotoId();
        this.likes = likes;
        this.likedUserIds = photo.getLikedUserIds();
    }

    public Long getPhotoId() {
        return photoId;
    }

    public Integer getLikes() {
        return likes;
    }

    public Set<String> getLikedUserIds() {
        return likedUserIds;
    }

    public void setPhotoId(Long photoId) {
        this.photoId = photoId;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public void setLikedUserIds(Set<String> likedUserIds) {
        this.likedUserIds = likedUserIds;
    }
}
