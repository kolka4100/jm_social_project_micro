package jm_social_project.profile_service.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@NoArgsConstructor
@Data
@Document (collection = "profile")
public class Profile {

    @Id
    private String id;

    private String firstName;

    private String lastName;

    private String status;

    private String avatarUrl;

    private Date birthDate;

    private String description;

    private String coordinates;

}

