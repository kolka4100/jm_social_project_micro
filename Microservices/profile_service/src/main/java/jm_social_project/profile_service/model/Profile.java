package jm_social_project.profile_service.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@NoArgsConstructor
@Data
@Document(collection = "profile")
public class Profile {

    public Profile(String id, String accountId, String firstName, String lastName, String status, String avatarUrl, Date birthDate, String description, Double latitude, Double longitude) {
        this.id = id;
        this.accountId = accountId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
        this.avatarUrl = avatarUrl;
        this.birthDate = birthDate;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Id
    private String id;

    @Indexed(unique = true)
    private String accountId;

    private String firstName;

    private String lastName;

    private String status;

    private String avatarUrl;

    private Date birthDate;

    private String description;

    private Double latitude;

    private Double longitude;

}

