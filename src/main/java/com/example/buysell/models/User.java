package com.example.buysell.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.example.buysell.enums.Role;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @NotBlank(message = "Адрес электронной почты не должен быть пустым")
    @Email(message = "Адрес электронной почты должен быть действительным")
    @Column(name="email", unique = true)
    private String email;

    @Column(name="phoneNumber")
    private String phoneNumber;

    @Column(name="name")
    private String name;

    @NotBlank(message = "Пароль не должен быть пустым")
    @Size(min = 8, max = 1000, message = "Пароль должен содержать от 8 до 1000 символов")
    @Column(name="password", length = 1000)
    private String password;

    @Column(name="active")
    private boolean active;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name="user_role", joinColumns = @JoinColumn(name="user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER, mappedBy = "user")
    private List<Product> products = new ArrayList<>();

    private LocalDateTime dateOfCreated;

    @PrePersist
    private void init() {
        dateOfCreated = LocalDateTime.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return email;
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
        return active;
    }
}
