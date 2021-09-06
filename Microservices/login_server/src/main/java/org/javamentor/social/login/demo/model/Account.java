package org.javamentor.social.login.demo.model;

import lombok.*;
import org.javamentor.social.login.demo.aspects.CheckAccount;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "account", schema = "public")
public class Account implements UserDetails {

    @NonNull
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String email;

    @NonNull
    private String password;

    private Boolean enable;

    private AccountStatus status;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    public Account(@NonNull String email,
                   @NonNull String password) {
        this.email = email;
        this.password = password;
    }

    public Account(@NonNull Long id, @NonNull String email, @NonNull String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    @Override public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(role);
    }

    @Override public String getUsername() {
        return email;
    }

    @Override public boolean isAccountNonExpired() {
        return false;
    }

    @Override public boolean isAccountNonLocked() {
        return false;
    }

    @Override public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override public boolean isEnabled() {
        return false;
    }
}
