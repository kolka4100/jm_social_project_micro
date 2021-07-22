package jm_social_project.media_storage.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.util.ArrayList;
import java.util.List;

@RedisHash("account")
public class Account {
    @Id
    private Long id;
    @Indexed
    private Long accountId;
    private String mediaPath;

    public Account(Long id, Long accountId, String mediaPath) {
        this.id = id;
        this.accountId = accountId;
        this.mediaPath = mediaPath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getMediaPath() {
        return mediaPath;
    }

    public void setMediaPath(String mediaPath) {
        this.mediaPath = mediaPath;
    }
}
