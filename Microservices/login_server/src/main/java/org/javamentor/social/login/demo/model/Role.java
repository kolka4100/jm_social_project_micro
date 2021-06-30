package org.javamentor.social.login.demo.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "roles", schema = "public")
public class Role implements GrantedAuthority {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The role's id")
    private Long id;

    @Column(name = "role_name")
    @ApiModelProperty(notes = "The role's name")
    private String roleName;

    public Role(String roleName) {
        this.roleName = roleName;
    }

    @Override
    @ApiModelProperty(notes = "The authority's name")
    public String getAuthority() {
        return roleName;
    }
}
