package com.example.findjob.domain;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "usr")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Укажите имя пользователя")
    @Length(max = 255, message = "Длина должна быть меньше 255 символов")
    private String username;

    @NotBlank(message = "Укажите пароль")
    @Length(max = 255, message = "Длина должна быть меньше 255 символов")
    private String password;

    @Transient
    @NotBlank(message = "Повторите пароль")
    @Length(max = 255, message = "Длина должна быть меньше 255 символов")
    private String password2;

    private boolean active;

    private String activationCode;

    @NotBlank(message = "Укажите email")
    @Email(message = "Некорректный email")
    @Length(max = 255, message = "Длина должна быть меньше 255 символов")
    private String email;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name="user_role", joinColumns = @JoinColumn(name="user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getUserRoles() {
        StringBuilder sb = new StringBuilder();
        for (Role role : roles) {
            if (!role.equals(Role.USER)) {
                sb.append(role.getUiName());
                sb.append(", ");
            }
        }
        return sb.toString();
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
        return isActive();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public boolean isAdmin() {
        return roles.contains(Role.ADMIN);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public void generateAndSetActivationCode() {
        setActivationCode(UUID.randomUUID().toString());
    }
}
