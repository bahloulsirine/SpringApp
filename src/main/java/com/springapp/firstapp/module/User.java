package com.springapp.firstapp.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    @Column(nullable = false)
    private  String email;
    private  String address;
    @Column(unique = true)
    private  Long cin;
    @Column(nullable = false)
    private  String password;
    private  String birthday;
    @Column(unique = true)
    private  Long driverLicence;
    @Column(unique = true)
    private  String phoneNumber;
    private Integer totalOrder;
}
