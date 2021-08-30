package jm_social_project.profile_service.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@NoArgsConstructor
@Data
@Document(collection = "visitedProfiles")
public class VisitedProfiles {

    @Id
    private String id;

    private Profile profileId;

    private Date visitDate;

    public VisitedProfiles(Profile profileId, Date visitDate) {
        this.profileId = profileId;
        this.visitDate = visitDate;
    }
}
