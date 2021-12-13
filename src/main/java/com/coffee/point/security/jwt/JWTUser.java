package com.coffee.point.security.jwt;

import com.coffee.point.model.UserRole;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
public class JWTUser implements UserDetails {

    private Long id;

    private String firstName;

    private String lastName;

    private String middleName;

    private String login;

    private String password;

    private boolean isDeleted;

    private UserRole role;

    public JWTUser(Long id, String firstName, String lastName, String middleName,
                   String login, String password, boolean isDeleted, UserRole role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.login = login;
        this.password = password;
        this.isDeleted = isDeleted;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role));
    }

    /**
     * Returns auth user(current user)
     */
    public static JWTUser getCurrentUser() {
        return (JWTUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    /**
     * Returns auth users ID(current user ID)
     */
    public static Long getCurrentUserID() {
        return JWTUser.getCurrentUser().getId();
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}