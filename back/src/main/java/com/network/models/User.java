package com.network.models;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * User model
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class User implements UserDetails{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NonNull
  @Size(max = 50)
  @Email
  private String email;

  @NonNull
  @Size(max = 20)
  @Column(name = "user_name")
  private String userName;

  @NonNull
  @Column(name = "`password`")
  @Size(min = 8)
  @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$", message = "Password must contain at least one digit, one lowercase letter, one uppercase letter, and one special character.")
  private String password;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
    name = "user_themes",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "theme_id")
  )
  @Builder.Default
  @ToString.Exclude
  private List<Theme> themes = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return email;
    }

    public String getUserName() {
        return userName;
    }
}
