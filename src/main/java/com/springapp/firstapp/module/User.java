package com.springapp.firstapp.module;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data


public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String firstName;
    private  String lastName;
    private  String sex;
    @Column(nullable = false)
    private  String email;
    private  String address;
    @Column(unique = true)
    private  Long cin;
    @JsonIgnore
    @Column(nullable = false)
    private  String password;
    private Date birthday;
    private  Long driverLicence;
    @Column(unique = true)
    private  String phoneNumber;
    private Integer totalOrder;
    private Boolean isAccountNonExpired ;
    private Boolean isAccountNonLocked;
    private Boolean isCredentialsNonExpired ;
    private Boolean isEnabled;
    @ManyToOne
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();//list pas valeur dupliquée
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+role.getRole());
        authorities.add(authority);
        return  authorities;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public Boolean hasRole(String role){
        return getAuthorities().contains(new SimpleGrantedAuthority("ROLE_"+role));
    }
}
