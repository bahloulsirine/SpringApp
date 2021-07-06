package com.springapp.firstapp.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data


public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String firstName;
    private  String lastName;
    private  String sex;
    private  String email;
    private  String address;
    @Column(unique = true)
    private  Long cin;
    private  String password;
    private  String birthday;
    @Column(unique = true)
    private  Long driverLicence;
    @Column(unique = true)
    private  String phoneNumber;
}
